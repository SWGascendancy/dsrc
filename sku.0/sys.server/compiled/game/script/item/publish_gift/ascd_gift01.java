package script.item.publish_gift;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.static_item;
import script.library.utils;

public class ascd_gift01 extends script.base_script
{
    public ascd_gift01()
    {
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null)
        {
            mid.setLabel(new string_id("spam", "unroll"));
            mid.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        obj_id containingPlayer = utils.getContainingPlayer(self);
        if (!isIdValid(containingPlayer))
        {
            sendSystemMessage(player, new string_id("spam", "cannot_use_not_in_inv"));
            return SCRIPT_CONTINUE;
        }
        if (containingPlayer != player)
        {
            sendSystemMessage(player, new string_id("spam", "cannot_use_not_in_inv"));
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            unrollMe(self, player);
        }
        return SCRIPT_CONTINUE;
    }
    public void unrollMe(obj_id self, obj_id player)
    {
        int which_one = rand(1, 15);
        String poster = "";
        switch (which_one)
        {
            case 1:
            poster = "item_tcg_loot_reward_series5_rain_storm";
            break;
            case 2:
            poster = "item_cs_decor_stuffed_rancor_02_01";
            break;
            case 3:
            poster = "item_cs_reactive_critical_heal_e_04_01";
            break;
            case 4:
            poster = "item_storage_increase_05_03";
            break;
            case 5:
            poster = "item_tcg_loot_reward_series4_video_game_table_02_01";
            break;
            case 6:
            poster = "item_tcg_loot_reward_series5_creature_pet_deed_scurrier_02_01";
            break;
            case 7:
            poster = "item_tcg_loot_reward_series1_diner_package";
            break;
            case 8:
            poster = "object/tangible/deed/player_house_deed/mustafar_house_lg.iff";
            break;
            case 9:
            poster = "item_color_crystal_02_28";
            break;
            case 10:
            poster = "item_ascd_token_box_01_03";
            break;
            case 11:
            poster = "item_lifeday_gift_self_02_11";
            break;
            case 12:
            poster = "item_tcg_loot_reward_series7_deed_commando_bunker";
            break;
            case 13:
            poster = "item_auto_level_50_buddy_conversion";
            break;
            case 14:
            poster = "vet_reward_resource_crate";
            break;
            case 15:
            poster = "space_rare_crate_tier_10";
            break;
        }
        obj_id item = static_item.createNewItemFunction(poster, player);
        if (isIdValid(item))
        {
            sendSystemMessage(player, new string_id("spam", "open_package"));
            destroyObject(self);
        }
    }
}
