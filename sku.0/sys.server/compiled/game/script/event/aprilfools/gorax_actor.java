package script.event.aprilfools;

import script.library.utils;
import script.obj_id;
import script.string_id;

public class gorax_actor extends script.base_script {
    public int OnDeath(obj_id self, obj_id killer, obj_id corpseId) {
        obj_id[] attackerList = utils.getObjIdBatchScriptVar(self, "creditForKills.attackerList.attackers");
        if (attackerList != null && attackerList.length > 0) {
            for (obj_id anAttackerList : attackerList) {
                if (utils.getDistance2D(self, anAttackerList) < 200.0 && !hasCompletedCollectionSlot(anAttackerList, "april_fools_reward")) {
                    modifyCollectionSlotValue(anAttackerList, "april_fools_reward", 1);
                    sendSystemMessage(anAttackerList, new string_id("spam", "you_got_april_fools_11_title"));
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
