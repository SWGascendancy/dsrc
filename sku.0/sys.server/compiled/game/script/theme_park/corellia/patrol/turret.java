package script.theme_park.corellia.patrol;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class turret extends script.base_script
{
    public turret()
    {
    }
    public int OnDestroy(obj_id self)
    {
        obj_id hideout = getObjIdObjVar(self, "hideout");
        messageTo(hideout, "turretDied", null, 30, false);
        return SCRIPT_CONTINUE;
    }
}
