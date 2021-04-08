package script.systems.fs_quest;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.trace;
import script.library.fs_dyn_village;

public class fs_village_npc extends script.base_script
{
    public fs_village_npc()
    {
    }
    public int OnDestroy(obj_id self)
    {
        fs_dyn_village.notifySpawnerOfDeath(self);
        return SCRIPT_CONTINUE;
    }
    public int OnDeath(obj_id self, obj_id killer, obj_id corpseId)
    {
        fs_dyn_village.notifySpawnerOfDeath(self);
        return SCRIPT_CONTINUE;
    }
    public int msgSelfDestruct(obj_id self, dictionary params)
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
}
