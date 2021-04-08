/*
 Title:        watcher
 Description:  Watcher thread that prevents scripts from executing too long
 */

package script;

import java.util.Stack;


class watcher extends Thread
{
	private static final int STATE_SCRIPT_WAIT    = 0;  // waiting for a script to run
	private static final int STATE_SCRIPT_TIMEOUT = 1;  // waiting for a script to timeout
	private static final int STATE_SCRIPT_KILL    = 2;  // waiting for a script to be killed

	private int    m_warnTime;				// time a script is allowed to run before a warning is generated
	private int    m_interruptTime;			// time a script is allowed to run before it is interrupted
	private int    m_state;
	private Thread m_scriptThread;			// thread the scripts are running from
	private Stack  m_scripts;				// scripts being run
	private Stack  m_methods;				// methods being run
	private String m_lastScript = null;		// last script run
	private String m_lastMethod = null;		// last method run

	/**
	 * Class constructor.
	 *
	 * @param warnMs		time a script is allowed to run before a warning is generated
	 * @param interruptMs	time a script is allowed to run before it is interrupted
	 */
	public watcher(int warnMs, int interruptMs)
	{
		super("watcher_Thread");
		m_warnTime = warnMs;
		m_interruptTime = interruptMs;
		m_state = STATE_SCRIPT_WAIT;
		m_scriptThread = currentThread();
		m_scripts = new Stack();
		m_methods = new Stack();
		setDaemon(true);
	}	// watcher()

	/**
	 * Thread entry point.
	 */
	public void run()
	{
		boolean signalScript = false;

		synchronized (this)
		{
			for ( ;; )
			{
				m_state = STATE_SCRIPT_WAIT;
				if (signalScript)
				{
					// signal the main thread that we have switched states
					synchronized (m_scriptThread)
					{
						m_scriptThread.notifyAll();
					}
				}
				try {
					wait();
				} catch (InterruptedException e) {
					// do nothing
				}
				
				// wait for our warn time to pass
				m_state = STATE_SCRIPT_TIMEOUT;
				// signal the main thread that we have switched states
				synchronized (m_scriptThread)
				{
					m_scriptThread.notifyAll();
				}
				try {
					wait(m_warnTime);
				} catch (InterruptedException e) {
					// do nothing
				}
				
				if (m_state != STATE_SCRIPT_TIMEOUT)
				{
					signalScript = true;
					continue;
				}
				// wait for our interrupt time to pass
				if (!m_scripts.empty())
				{
					System.err.println("WARNING: script " + m_scripts.peek() + ", function " + m_methods.peek() + " running for " + m_warnTime + "ms, currentTime = " +  System.currentTimeMillis());
				}
				else
				{
					System.err.println("WARNING: script <unknown> running for " + m_warnTime + "ms, currentTime = " + System.currentTimeMillis());
				}
				m_state = STATE_SCRIPT_KILL;
				try {
					wait(m_interruptTime);
				} catch (InterruptedException e) {
					// do nothing
				}
				
				if (m_state != STATE_SCRIPT_KILL)
				{
					signalScript = true;
					continue;
				}
				else
				{
					if (!m_scripts.empty())
					{
						System.err.println("WARNING: Script watcher forcing script " + m_scripts.peek() + ", function " + m_methods.peek() + " to exit, currentTime = " +  System.currentTimeMillis());
					}
					else
					{
						System.err.println("WARNING: Script watcher forcing script <unknown> to exit, currentTime = " + System.currentTimeMillis());
					}
					m_scriptThread.interrupt();
				}
			}
		}
    }	// run()

	/**
	 * Flags that a script is starting to be run.
	 */
	public void scriptStart(String script, String method)
	{
		m_lastScript = null;
		m_lastMethod = null;
		resetToWait();

		m_scripts.push(script);
		m_methods.push(method);
		startTimeout();
	}	// scriptStart()

	/**
	 * Flags that a script has finished being run.
	 */
	public void scriptStop()
	{
		if (!m_scripts.empty())
		{
			m_lastScript = (String)m_scripts.pop();
			m_lastMethod = (String)m_methods.pop();
		}

		resetToWait();

		// if we are still running scripts, we need to start the timer again
		if (!m_scripts.empty())
			startTimeout();
	}	// scriptStop()

	/**
	 * Forces the watcher into the starting wait state.
	 */
	private void resetToWait()
	{
		if (m_state != STATE_SCRIPT_WAIT)
		{
			synchronized (m_scriptThread)
			{
				m_state = STATE_SCRIPT_WAIT;
				synchronized (this)
				{
					notifyAll();
				}
				// wait until the watcher has changed states
				try {
					m_scriptThread.wait();
				} catch (InterruptedException e) {
					// do nothing
				}
			}
		}
	}	// resetToWait()

	/**
	 * Starts the watcher timing out the current script.
	 */
	private void startTimeout()
	{
		if (m_state != STATE_SCRIPT_WAIT)
			return;

		synchronized (m_scriptThread)
		{
			synchronized (this)
			{
				notifyAll();
			}
			// wait until the watcher has changed states
			try {
				m_scriptThread.wait();
			} catch (InterruptedException e) {
				// do nothing
			}
		}
	}	// startTimeout()

}	// class watcher
