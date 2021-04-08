package script.city;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.create;
import script.library.utils;
import script.library.locations;

public class rebel_ship_spawner extends script.base_script
{
    public rebel_ship_spawner()
    {
    }
    public int OnInitialize(obj_id self)
    {
        location here = getLocation(self);
        obj_id npc = createObject("object/static/structure/general/distant_ship_controller_rebel.iff", here);
        return SCRIPT_CONTINUE;
    }
}
