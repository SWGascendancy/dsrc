package script.systems.crafting.repair;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.craftinglib;
import script.library.utils;

public class droid_repair extends script.base_script
{
    public droid_repair()
    {
    }
    public int OnInitialize(obj_id self)
    {
        setObjVar(self, "crafting.type", GOT_creature_droid);
        return SCRIPT_CONTINUE;
    }
}
