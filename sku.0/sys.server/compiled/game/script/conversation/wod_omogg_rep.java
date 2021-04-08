// finch need to rewrite this script
/*
package script.conversation;

import script.base_script;
import script.menu_info;
import script.menu_info_data;
import script.menu_info_types;
import script.obj_id;
import script.prose_package;
import script.string_id;

import script.library.ai_lib;
import script.library.chat;

import script.library.groundquests;

public class wod_omogg_rep extends script.conversation.base.conversation_base {
    private static final String SCRIPT = "wod_omogg_rep";
    private static final String c_questFile_01 = "quest/wod_prologue_walkabout_01";
    private static final String c_questFile_02 = "quest/wod_prologue_walkabout_02";

    public boolean wod_omogg_rep_condition_questIsComplete(obj_id player, String quest) {
        int questId1 = questGetQuestId(quest);
        return questIsQuestComplete(questId1, player);
    }

    public boolean wod_omogg_rep_condition_questActiveOrComplete(obj_id player, String quest) {
        return groundquests.isQuestActiveOrComplete(player, quest);
    }

    public boolean wod_omogg_rep_condition_playerOnReturnToRep01(obj_id player, obj_id npc) {
        int questId1 = questGetQuestId(c_questFile_01);
        int laststep = groundquests.getTaskId(questId1, "returnToRep01");
        return questIsTaskActive(questId1, laststep, player);
    }

    public boolean wod_omogg_rep_condition_playerOnReturnToRep02(obj_id player, obj_id npc) {
        int questId1 = questGetQuestId(c_questFile_02);
        int laststep = groundquests.getTaskId(questId1, "returnToRep02");
        return questIsTaskActive(questId1, laststep, player);
    }

    public int OnStartNpcConversation(obj_id self, obj_id player) {
        if (getLevel(player) < 50 || !isGod(player)) {
            chat.chat(self, player, new string_id(SCRIPT, "s_52"));
        } else if (wod_omogg_rep_condition_questIsComplete(player, c_questFile_01) && wod_omogg_rep_condition_questIsComplete(player, c_questFile_02)) {
            chat.chat(self, player, new string_id(SCRIPT, "s_4"));
        } else if (wod_omogg_rep_condition_questIsComplete(player, c_questFile_01) && !wod_omogg_rep_condition_questActiveOrComplete(player, c_questFile_02)) {
            OnStartNpcConversation(SCRIPT, "s_36", "s_44", player, self);
        } else if (wod_omogg_rep_condition_playerOnReturnToRep02(player, self)) {
            OnStartNpcConversation(SCRIPT, "s_23", "s_24", player, self);
        } else if (wod_omogg_rep_condition_playerOnReturnToRep01(player, self)) {
            OnStartNpcConversation(SCRIPT, "s_6", "s_12", player, self);
        } else if (wod_omogg_rep_condition_questActiveOrComplete(player, c_questFile_01)) {
            chat.chat(self, player, new string_id(SCRIPT, "s_17"));
            npcEndConversation(player);
        } else {
            OnStartNpcConversation(SCRIPT, "s_19", "s_21", player, self);
        }
        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response) {
        switch (response.getAsciiId()) {
            case "s_24":
                npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_25"));
                groundquests.sendSignal(player, "speakWithRubina");
                //groundquests.completeQuest(player, c_questFile_02);
                break;
            case "s_44":
                npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_46"));
                groundquests.grantQuest(player, c_questFile_02);
                break;
            case "s_35":
                groundquests.completeQuest(player, c_questFile_01);
                npcEndConversation(player);
                break;
            case "s_12":
                setupResponses(SCRIPT, "s_13", "s_35", player);
                break;
            case "s_32":
                npcEndConversationWithMessage(player, new string_id(SCRIPT, "s_34"));
                groundquests.grantQuest(player, c_questFile_01);
                break;
            case "s_28":
                setupResponses(SCRIPT, "s_30", "s_32", player);
                break;
            case "s_21":
                setupResponses(SCRIPT, "s_26", "s_28", player);
                break;
        }
    }
}
*/
