package script.npc.vendor;

import script.base_script;
import script.dictionary;
import script.menu_info;
import script.menu_info_data;
import script.menu_info_types;
import script.obj_id;
import script.prose_package;
import script.string_id;

import script.library.ai_lib;
import script.library.chat;
import script.library.utils;

public class wod_ns_vendor extends script.base_script {
    private static final String c_stringFile = "conversation/wod_ns_vendor";
    private static final String branchObjVar = "conversation.wod_ns_vendor.branchId";

    public void vendor_action_showResourceVendorUI(obj_id player, obj_id npc) {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }

    private void npcConversationPhase(obj_id npc, obj_id player, String message, String[] string_responses, int phase)
    {
        string_id[] responses = new string_id[string_responses.length];
        for (int i = 0; i < responses.length; i++)
        {
            responses[i] = new string_id(c_stringFile, string_responses[i]);
        }
        setObjVar(player, branchObjVar, phase);
        npcSpeak(player, new string_id(c_stringFile, message));
        npcSetConversationResponses(player, responses);
    }

    private void npcConversationPhase(obj_id npc, obj_id player, String message, String response, int phase)
    {
        npcConversationPhase(npc, player, message, new String[]{ response }, phase);
    }

    public int OnInitialize(obj_id self){
        if (!isTangible(self) || isPlayer(self)){
            detachScript(self, "wod.vendor");
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
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }

    public int OnIncapacitated(obj_id self, obj_id killer) {
        clearCondition(self, CONDITION_CONVERSABLE);
        clearCondition(self, CONDITION_INTERESTING);
        detachScript(self, "wod.vendor");
        return SCRIPT_CONTINUE;
    }

    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses) {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }

    public int OnStartNpcConversation(obj_id self, obj_id player) {
        if (ai_lib.isInCombat(player)) {
            return SCRIPT_OVERRIDE;
        }
        setObjVar(player, branchObjVar, 1);
        string_id[] responses = new string_id[2];
        responses[0] = new string_id(c_stringFile, "s_8");
        responses[1] = new string_id(c_stringFile, "s_12");
        npcStartConversation(player, self, "wod_ns_vendor", new string_id(c_stringFile, "s_6"), responses);
        return SCRIPT_CONTINUE;
    }

    private int wod_ns_vendor_handleBranch1(obj_id player, obj_id self, string_id response)
    {
        switch (response.getAsciiId())
        {
            case "s_8":
                vendor_action_showResourceVendorUI(player, self);
                chat.chat(self, player, new string_id(c_stringFile, "s_10"));
                break;
            case "s_12":
                chat.chat(self, player, new string_id(c_stringFile, "s_16"));
                break;
        }
        npcEndConversation(player);
        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("wod_ns_vendor")) {
            return SCRIPT_CONTINUE;
        }
        int branchId = getIntObjVar(player, branchObjVar);
        switch (branchId)
        {
            case 1:
                return wod_ns_vendor_handleBranch1(player, self, response);
        }
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        removeObjVar(player, branchObjVar);
        return SCRIPT_CONTINUE;
    }
}
