package script.theme_park.tatooine.jabbaspawner;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class gamguard11 extends script.base_script
{
    public gamguard11()
    {
    }
    public int OnDestroy(obj_id self)
    {
        obj_id palace = getObjIdObjVar(self, "palace");
        messageTo(palace, "gamGuard11Died", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
