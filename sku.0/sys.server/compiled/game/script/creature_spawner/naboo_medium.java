package script.creature_spawner;

import script.obj_id;

public class naboo_medium extends script.creature_spawner.base_newbie_creature_spawner
{
    public int maxPop = 4;

    public naboo_medium()
    {
    }
    public int OnInitialize(obj_id self)
    {
        spawnCreatures(self);
        return SCRIPT_CONTINUE;
    }
    public String pickCreature()
    {
        switch (rand(1, 3))
        {
            case 1:
                return "fanned_rawl";
            case 2:
                return "nightspider";
            case 3:
                return "flewt";
        }
        return "flewt";
    }
}
