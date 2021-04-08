package script.item.wearable;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;

public class sarlacc_coverall extends script.base_script
{
    public sarlacc_coverall()
    {
    }
    public int OnAttach(obj_id self)
    {
        setSkillModBonus(self, "resistance_disease", 25);
        messageTo(self, "destroyCoverall", null, 86400, true);
        return SCRIPT_CONTINUE;
    }
    public int destroyCoverall(obj_id self, dictionary params)
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
