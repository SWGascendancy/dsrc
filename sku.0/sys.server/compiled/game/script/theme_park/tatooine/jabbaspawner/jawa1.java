package script.theme_park.tatooine.jabbaspawner;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class jawa1 extends script.base_script
{
    public jawa1()
    {
    }
    public int OnDestroy(obj_id self)
    {
        obj_id palace = getObjIdObjVar(self, "palace");
        messageTo(palace, "jawa1Died", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
