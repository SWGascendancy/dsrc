package script.creature_spawner;

public class rori_medium extends script.creature_spawner.base_newbie_creature_spawner
{
    public int maxPop = 4;

    public rori_medium()
    {
    }
    public String pickCreature()
    {
        switch (rand(1, 3))
        {
            case 1:
                return "vrobalet";
            case 2:
                return "timid_vir_vur";
            case 3:
                return "flewt";
        }
        return "timid_vir_vur";
    }
}
