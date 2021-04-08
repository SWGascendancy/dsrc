package script.npc.vendor;

import script.*;
import script.base_script;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;

public class tcg_vendor extends script.base_script {
    public static final String c_stringFile = "conversation/tcg_vendor";

    public boolean vendor_condition__defaultCondition(obj_id player, obj_id npc) {
        return true;
    }

    public boolean vendor_condition_canPurchase(obj_id player, obj_id npc) {
        return true;
    }

    public void vendor_action_showResourceVendorUI(obj_id player, obj_id npc) {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }

    public int OnInitialize(obj_id self){
        if (!isTangible(self) || isPlayer(self)){
            detachScript(self, "tcg.vendor");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        return SCRIPT_CONTINUE;
    }

    public int OnAttach(obj_id self) {
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo) {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) {
        clearCondition(self, CONDITION_CONVERSABLE);
        clearCondition(self, CONDITION_INTERESTING);
        detachScript(self, "tcg.vendor");
        return SCRIPT_CONTINUE;
    }

    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

    public int OnStartNpcConversation(obj_id self, obj_id player) {
        play2dNonLoopingSound(self, "sound/music_acq_miner.snd");

        if (ai_lib.isInCombat(player)) {
            return SCRIPT_OVERRIDE;
        } else if (vendor_condition_canPurchase(player, self)) {
            vendor_action_showResourceVendorUI(player, self);
            string_id message = new string_id(c_stringFile, "s_1");
            chat.chat(self, player, message);
            return SCRIPT_CONTINUE;
        } else if (vendor_condition__defaultCondition(player, self)) {
            string_id message = new string_id(c_stringFile, "s_2");
            chat.chat(self, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("vendor")) {
            return SCRIPT_CONTINUE;
        }
        int branchId = utils.getIntScriptVar(player, "tcg.vendor.branchId");
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "tcg.vendor.branchId");
        return SCRIPT_CONTINUE;
    }
}
