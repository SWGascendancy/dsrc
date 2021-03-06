package script.space.quest_logic;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.space_quest;
import script.library.space_utils;
import script.library.utils;

public class dynamic_ship extends script.base_script
{
    public dynamic_ship()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "pendingWarp", null, 600.f, false);
        return SCRIPT_CONTINUE;
    }
    public int pendingWarp(obj_id self, dictionary params)
    {
        obj_id quest = getObjIdObjVar(self, "quest");
        if (isIdValid(quest) && exists(quest))
        {
            setObjVar(quest, "critical_warped", 1);
        }
        destroyObjectHyperspace(self);
        return SCRIPT_CONTINUE;
    }
}
