package script.systems.tcg;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;
import script.library.anims;
import script.library.utils;
import script.library.tcg;

public class tcg_series8_vader_pod extends script.base_script
{
    public tcg_series8_vader_pod()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("item_name", "vader_pod_controls"));
        mi.addSubMenu(menu, menu_info_types.SERVER_MENU1, new string_id("item_name", "open"));
        mi.addSubMenu(menu, menu_info_types.SERVER_MENU2, new string_id("item_name", "close"));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.SERVER_MENU1)
        {
            doAnimationAction(self, anims.VADER_POD_OPEN);
            playClientEffectObj(self, "clienteffect/vader_pod.cef", self, "");
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            doAnimationAction(self, anims.VADER_POD_CLOSE);
            playClientEffectObj(self, "clienteffect/vader_pod_close.cef", self, "");
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
