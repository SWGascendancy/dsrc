package script.ai;

import script.*;


public class casay extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Casay");
        sendSystemMessagePlanetTestingOnly("ATTENTION DANTOOINE CIVILIANS: The World Boss Casay has been located west of the Janta Stronghold");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DANTOOINE CIVILIANS: The World Boss Casay has been located west of the Janta Stronghold");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DANTOOINE CIVILIANS: The World Boss Casay has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
