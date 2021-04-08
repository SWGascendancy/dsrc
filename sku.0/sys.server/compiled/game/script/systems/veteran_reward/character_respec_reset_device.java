package script.systems.veteran_reward;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.sui;
import script.library.utils;

public class character_respec_reset_device extends script.base_script {
    public static final String STF_FILE = "reset_respec";
    public static final string_id RESET_RESPEC = new string_id(STF_FILE, "reset_respec");
    public static final string_id SID_RESET_RESPEC_TITLE = new string_id(STF_FILE, "sui_title");
    public static final string_id SID_RESET_RESPEC_PROMPT = new string_id(STF_FILE, "sui_prompt");

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) {
        mi.addRootMenu(menu_info_types.ITEM_USE, RESET_RESPEC);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) {
        if (item == menu_info_types.ITEM_USE) {
            sui.msgbox(self, player, utils.packStringId(SID_RESET_RESPEC_PROMPT), sui.YES_NO, utils.packStringId(SID_RESET_RESPEC_TITLE), "handleResetRespecChoice");
        }
        return SCRIPT_CONTINUE;
    }

    public int handleResetRespecChoice(obj_id self, dictionary params) {
        if (sui.getIntButtonPressed(params) == sui.BP_OK) {
            obj_id player = sui.getPlayerId(params);
            setObjVar(player, "respecsBought", 0);
            sendSystemMessage(player, new string_id(STF_FILE, "msg_reset_used"));
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
}
