package script.conversation;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class sean_contact_quest extends script.base_script
{
    public sean_contact_quest()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "attachMissingScript", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int attachMissingScript(obj_id self, dictionary params)
    {
        attachScript(self, "conversation.sean_contact");
        return SCRIPT_CONTINUE;
    }
}
