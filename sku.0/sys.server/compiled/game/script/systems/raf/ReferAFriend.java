package script.systems.raf;

import script.*;

import script.library.static_item;
import script.library.sui;

public class ReferAFriend extends script.base_script {
    public int setReferrer(obj_id self, obj_id target, String params, float defaultTime) {
        if (!isPlayer(target)) {
            return SCRIPT_CONTINUE;
        }
        int stationIdSelf = getPlayerStationId(self);
        int stationIdTarget = getPlayerStationId(target);
        if (stationIdSelf == stationIdTarget) {
            sendSystemMessageTestingOnly(self, "You cannot refer yourself.");
            return SCRIPT_CONTINUE;
        }
        obj_id tatooine = getPlanetByName("tatooine");
        if (hasObjVar(tatooine, "raf.referred_" + stationIdSelf)) {
            sendSystemMessageTestingOnly(self, "You have already set another player as your referrer.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(tatooine, "raf.referred_" + stationIdTarget) && getIntObjVar(tatooine, "raf.referred_" + stationIdTarget) == stationIdSelf) {
            sendSystemMessageTestingOnly(self, "You cannot refer this friend because he is referred to you.");
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(tatooine, "raf.last_referred_" + stationIdTarget) && getIntObjVar(tatooine, "raf.last_referred_" + stationIdTarget) >= getCurrentBirthDate()) {
            sendSystemMessageTestingOnly(self, "You cannot refer this friend because he has referred another friend within the last 24 hours.");
            return SCRIPT_CONTINUE;
        }
        obj_id[] friend_items = new obj_id[2];
        friend_items[0] = static_item.createNewItemFunction("item_reward_buddy_xp_chip_06_01", self);
        friend_items[1] = static_item.createNewItemFunction("item_auto_level_50_buddy_conversion", self);
        showLootBox(self, friend_items);
        setObjVar(tatooine, "raf.referred_" + stationIdSelf, stationIdTarget);
        sui.msgbox(self, self, "Congratulations! You have received a Cybernetic Experience Chip and a Holocron of Knowledge as rewards for the Refer a Friend program! Player " + getPlayerName(target) + " is now your referrer.", "Refer a Friend Rewards");

        obj_id[] referrer_items = new obj_id[1];
        referrer_items[0] = static_item.createNewItemFunction("col_buddy_" + (rand(0, 1) == 0 ? "02_" : "") + "token", target);
        showLootBox(target, referrer_items);
        setObjVar(tatooine, "raf.last_referred_" + stationIdTarget, getCurrentBirthDate());
        sui.msgbox(target, target, "Congratulations! You have received a Buddy Token for referring player " + getPlayerName(self) + " to the game!", "Refer a Friend Rewards");
        return SCRIPT_CONTINUE;
    }
}
