package script.creature_spawner;

public class corellia_easy extends base_newbie_creature_spawner
{
    public corellia_easy()
    {
    }
    public int maxPop = 6;
    public boolean newbie = true;

    public String pickCreature()
    {
        switch (rand(1, 4))
        {
            case 1:
                return "corellian_butterfly_drone";
            case 2:
                return "corellian_butterfly";
            case 3:
                return "durni";
            case 4:
                return "gubbur";
        }
        return "gubbur";
    }
}
