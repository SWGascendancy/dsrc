package script.faction_perk.hq;

import script.dictionary;
import script.library.hq;
import script.obj_id;

public class theater_manager extends script.base_script
{
    public theater_manager()
    {
    }
    public int OnDestroy(obj_id self)
    {
        hq.cleanupHqTheater(self);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        messageTo(self, "handleTheaterSpawn", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int handleTheaterSpawn(obj_id self, dictionary params)
    {
        LOG("hq", "(" + self + ") received handleTheaterSpawn...");
        LOG("hq", "handleTheaterSpawn: attempting to load theater...");
        hq.loadHqTheater(self);
        return SCRIPT_CONTINUE;
    }
}
