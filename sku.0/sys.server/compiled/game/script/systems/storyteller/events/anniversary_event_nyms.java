package script.systems.storyteller.events;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.create;
import script.library.ai_lib;

public class anniversary_event_nyms extends script.base_script
{
    public anniversary_event_nyms()
    {
    }
    public int OnInitialize(obj_id self)
    {
        if (!isObjectPersisted(self))
        {
            messageTo(self, "handlePersistEventProp", null, 1, false);
        }
        String deleteEventProps = getConfigSetting("GameServer", "deleteEventProps");
        if (deleteEventProps != null && deleteEventProps.length() > 0)
        {
            if (deleteEventProps.equals("true") || deleteEventProps.equals("1"))
            {
                messageTo(self, "handleDeleteEventProps", null, 2, false);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "handlePersistEventProp", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int handlePersistEventProp(obj_id self, dictionary params)
    {
        persistObject(self);
        return SCRIPT_CONTINUE;
    }
    public int handleDeleteEventProps(obj_id self, dictionary params)
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
