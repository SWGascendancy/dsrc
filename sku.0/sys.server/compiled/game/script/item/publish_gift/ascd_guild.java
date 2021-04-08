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

public class ascd_guild extends script.base_script
{
    public ascd_guild()
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
        int which_one = rand(1, 16);
        String poster = "";
        switch (which_one)
        {
            case 1:
            poster = "item_tcg_loot_reward_series3_sith_meditation_room_deed";
            break;
            case 2:
            poster = "item_tcg_loot_reward_series3_jedi_meditation_room_deed";
            break;
            case 3:
            poster = "item_tcg_loot_reward_series7_deed_vip_bunker";
            break;
            case 4:
            poster = "item_tcg_loot_reward_series8_yoda_house_deed";
            break;
            case 5:
            poster = "item_tcg_loot_reward_series5__player_house_atat";
            break;
            case 6:
            poster = "item_pgc_sandcrawler_house_deed";
            break;
            case 7:
            poster = "item_tcg_loot_reward_series8_bespin_house_deed";
            break;
            case 8:
            poster = "object/tangible/deed/player_house_deed/mustafar_house_lg.iff";
            break;
            case 9:
            poster = "item_player_house_deed_yt1300";
            break;
            case 10:
            poster = "item_tcg_loot_reward_series7_deed_vehicle_garage";
            break;
            case 11:
            poster = "item_tcg_loot_reward_series1_barn";
            break;
            case 12:
            poster = "item_tcg_loot_reward_series7_deed_commando_bunker";
            break;
            case 13:
            poster = "item_tcg_loot_reward_series4_relaxation_pool_deed_02_01";
            break;
            case 14:
            poster = "item_tcg_loot_reward_series6_deed_rebel_spire";
            break;
            case 15:
            poster = "item_tcg_loot_reward_series6_deed_emperor_spire";
            break;
            case 16:
            poster = "object/tangible/deed/player_house_deed/tree_house_01_deed.iff";
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
