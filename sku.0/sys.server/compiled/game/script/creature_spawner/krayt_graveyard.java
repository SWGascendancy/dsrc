package script.creature_spawner;

import script.dictionary;
import script.library.create;
import script.library.locations;
import script.obj_id;

public class krayt_graveyard extends script.base_script
{
    public krayt_graveyard()
    {
    }
    public int OnAttach(obj_id self)
    {
        doKraytSpawn();
        return SCRIPT_CONTINUE;
    }
    public void doKraytSpawn()
    {
        obj_id self = getSelf();
        if (!hasObjVar(self, "Krayt"))
        {
            obj_id kd = create.object("canyon_krayt_dragon", locations.getGoodLocationAroundLocation(getLocation(self), 100f, 100f, 100f, 100f));
            setObjVar(kd, "creater", self);
            setObjVar(self, "Krayt", kd);
            attachScript(kd, "creature_spawner.death_msg");
        }
    }
    public int creatureDied(obj_id self, dictionary params)
    {
        messageTo(self, "spawnNew", null, 30, true);
        return SCRIPT_CONTINUE;
    }
    public int spawnNew(obj_id self, dictionary params)
    {
        removeObjVar(self, "Krayt");
        doKraytSpawn();
        return SCRIPT_CONTINUE;
    }
}
