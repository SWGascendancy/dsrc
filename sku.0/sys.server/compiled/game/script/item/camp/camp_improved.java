package script.item.camp;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class camp_improved extends script.item.camp.camp_base
{
    public camp_improved()
    {
    }
    public int OnAttach(obj_id self)
    {
        setObjVar(self, "campPower", 3);
        setObjVar(self, "skillReq", 30);
        return SCRIPT_CONTINUE;
    }
}
