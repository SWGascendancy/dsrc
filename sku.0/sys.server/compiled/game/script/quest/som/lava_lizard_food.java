package script.quest.som;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.groundquests;

public class lava_lizard_food extends script.base_script
{
    public lava_lizard_food()
    {
    }
    public static final String STF = "som/som_quest";
    public static final string_id EXAMINE = new string_id(STF, "lava_lizard_food_examine");
    public static final string_id ALREADY = new string_id(STF, "lava_lizard_food_already");
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, EXAMINE);
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid == null)
        {
            return SCRIPT_CONTINUE;
        }
        mid.setServerNotify(true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (!groundquests.isQuestActive(player, "som_tanray_hunt_20"))
            {
                groundquests.grantQuest(player, "som_tanray_hunt_20");
                return SCRIPT_CONTINUE;
            }
            else 
            {
                sendSystemMessage(player, ALREADY);
            }
        }
        return SCRIPT_CONTINUE;
    }
}