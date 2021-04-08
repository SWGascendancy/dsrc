package script.theme_park.heroic.tusken;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class lockout extends script.base_script
{
    public lockout()
    {
    }
    public int OnAttach(obj_id self)
    {
        permissionsMakePrivate(self);
        return SCRIPT_CONTINUE;
    }
    public int unlock(obj_id self, dictionary params)
    {
        permissionsMakePublic(self);
        return SCRIPT_CONTINUE;
    }
    public int lock(obj_id self, dictionary params)
    {
        permissionsMakePrivate(self);
        return SCRIPT_CONTINUE;
    }
}
