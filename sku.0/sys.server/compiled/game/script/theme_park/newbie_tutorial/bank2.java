package script.theme_park.newbie_tutorial;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class bank2 extends script.theme_park.newbie_tutorial.tutorial_base
{
    public bank2()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        sysMessage(player, "wrong_bank");
        return SCRIPT_OVERRIDE;
    }
}
