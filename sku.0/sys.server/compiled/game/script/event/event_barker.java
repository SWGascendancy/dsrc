package script.event;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.groundquests;
import script.library.chat;
import script.library.attrib;
import script.library.utils;

public class event_barker extends script.base_script
{
    public event_barker()
    {
    }
    public int OnAttach(obj_id self)
    {
        setBarkAttributes(self);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        setBarkAttributes(self);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public void setBarkAttributes(obj_id self)
    {
        createTriggerVolume("chat_range", 5, true);
        setAttributeInterested(self, attrib.ALL);
        return;
    }
    public void beginChatting(obj_id self, obj_id player)
    {
        String barkstring = getStringObjVar(self, "barker.string");
        chat.chat(self, barkstring);
        return;
    }
    public int handleReset(obj_id self, dictionary params)
    {
        utils.removeScriptVar(self, "already_chatting");
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id who)
    {
        if (utils.hasScriptVar(self, "already_chatting"))
        {
            return SCRIPT_CONTINUE;
        }
        if (volumeName.equals("chat_range"))
        {
                beginChatting(self, who);
                utils.setScriptVar(self, "already_chatting", 1);
                messageTo(self, "handleReset", null, 60f, false);
        }
        return SCRIPT_CONTINUE;
    }
}
