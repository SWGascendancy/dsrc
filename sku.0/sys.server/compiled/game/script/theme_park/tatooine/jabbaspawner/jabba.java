package script.theme_park.tatooine.jabbaspawner;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class jabba extends script.base_script
{
    public jabba()
    {
    }
    public int OnDestroy(obj_id self)
    {
        obj_id palace = getObjIdObjVar(self, "palace");
        messageTo(palace, "jabbaDied", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
