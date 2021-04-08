package script.systems.veteran_reward;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class instant_travel_terminal_deed extends script.base_script
{
    public instant_travel_terminal_deed()
    {
    }
    public static final string_id LEARN_ABILITY = new string_id("item_n", "instant_travel_terminal_learn");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, LEARN_ABILITY);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (hasObjVar(self, "privateerShip"))
            {
                grantCommand(player, "callforprivateerpickup");
            }
            else if (hasObjVar(self, "royalShip"))
            {
                grantCommand(player, "callforroyalpickup");
            }
            else if (hasObjVar(self, "junk"))
            {
                grantCommand(player, "callforrattletrappickup");
            }
            else if (hasObjVar(self, "tcg_itv_home"))
            {
                grantCommand(player, "callforsolarsailerpickup");
            }
            else if (hasObjVar(self, "tcg_itv_location"))
            {
                grantCommand(player, "callforg9riggerpickup");
            }
            else if (hasObjVar(self, "snowspeeder"))
            {
                grantCommand(player, "callforsnowspeeder");
            }
            else if (hasObjVar(self, "slave1pickup"))
            {
                grantCommand(player, "callforslave1pickup");
            }
            else
            {
                grantCommand(player, "callforpickup");
            }
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
