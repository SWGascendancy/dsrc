package script.item.container.got_specific;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class lunchbox extends script.item.container.got_specific.base
{
    public lunchbox()
    {
    }
    public static final int MY_TYPE = GOT_misc_food;
    public int OnAttach(obj_id self)
    {
        setObjVar(self, VAR_GOT, MY_TYPE);
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self)
    {
        removeObjVar(self, VAR_GOT);
        return SCRIPT_CONTINUE;
    }
}
