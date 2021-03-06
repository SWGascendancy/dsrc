package script.theme_park.dungeon.mustafar_trials.old_republic_facility;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.sui;

public class door_terminal_05 extends script.base_script
{
    public door_terminal_05()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        int id = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("mustafar/old_republic_facility", "door_terminal_use"));
        menu_info_data mid = mi.getMenuItemById(id);
        mid.setServerNotify(true);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            readTerminal(self, player);
        }
        return SCRIPT_CONTINUE;
    }
    public void readTerminal(obj_id self, obj_id player)
    {
        obj_id building = getTopMostContainer(self);
        String title = "@mustafar/old_republic_facility:door_terminal_sui_title";
        String prompt = "@mustafar/old_republic_facility:door_terminal_05_sui_prompt";
        int status = 0;
        if (hasObjVar(building, "status"))
        {
            status = getIntObjVar(building, "status");
        }
        if (status < 8)
        {
            prompt += "_a";
        }
        int pid = sui.msgbox(player, player, prompt, sui.OK_ONLY, title, "noHandler");
        return;
    }
}
