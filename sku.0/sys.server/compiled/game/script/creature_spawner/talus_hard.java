package script.creature_spawner;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class talus_hard extends script.creature_spawner.base_newbie_creature_spawner
{
    public talus_hard()
    {
    }
    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int creatureDied(obj_id self, dictionary params)
    {
        return SCRIPT_CONTINUE;
    }
    public String pickCreature()
    {
        return null;
    }
    public int getMaxPop()
    {
        int maxPop = 0;
        return maxPop;
    }
}
