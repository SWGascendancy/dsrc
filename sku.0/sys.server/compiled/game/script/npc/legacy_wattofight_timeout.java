package script.npc;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;

public class legacy_wattofight_timeout extends script.base_script
{
    public legacy_wattofight_timeout()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "msgCheckCombat", null, 30, false);
        return SCRIPT_CONTINUE;
    }
    public int msgCheckCombat(obj_id self, dictionary params)
    {
        if (!isIncapacitated(self) || !isDead(self) || !ai_lib.isInCombat(self))
        {
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
