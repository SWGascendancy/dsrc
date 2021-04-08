package script.conversation;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.groundquests;
import script.library.utils;

public class collections_ice_cream_part_nine extends script.base_script
{
    public collections_ice_cream_part_nine()
    {
    }
    public static String c_stringFile = "conversation/collections_ice_cream_part_nine";
    public boolean collections_ice_cream_part_nine_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean collections_ice_cream_part_nine_condition_hasActiveTask(obj_id player, obj_id npc)
    {
        if (groundquests.isQuestActive(player, "chapter9_ice_cream"))
        {
            return groundquests.isTaskActive(player, "chapter9_ice_cream", "meet_otonon_tracks");
        }
        return false;
    }
    public void collections_ice_cream_part_nine_action_signalNextTask(obj_id player, obj_id npc)
    {
        modifyCollectionSlotValue(player, "col_fried_icecream_part_09", 1);
        groundquests.sendSignal(player, "meet_otonon_tracks");
    }
    public int collections_ice_cream_part_nine_handleBranch1(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_9"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_10");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_12");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 2);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch2(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_12"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_14");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_16");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 3);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch3(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_16"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_18");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_19");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 4);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch4(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_19"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_20");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_24");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 5);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch5(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_24"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_25");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_27");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 6);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch6(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_27"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_28");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_31");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 7);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch7(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_31"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_32");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
                {
                    ++numberOfResponses;
                    hasResponse = true;
                    hasResponse0 = true;
                }
                if (hasResponse)
                {
                    int responseIndex = 0;
                    string_id responses[] = new string_id[numberOfResponses];
                    if (hasResponse0)
                    {
                        responses[responseIndex++] = new string_id(c_stringFile, "s_34");
                    }
                    utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 8);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int collections_ice_cream_part_nine_handleBranch8(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_34"))
        {
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                collections_ice_cream_part_nine_action_signalNextTask(player, npc);
                string_id message = new string_id(c_stringFile, "s_36");
                utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isTangible(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.collections_ice_cream_part_nine");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo)
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.collections_ice_cream_part_nine");
        return SCRIPT_CONTINUE;
    }
    public boolean npcStartConversation(obj_id player, obj_id npc, String convoName, string_id greetingId, prose_package greetingProse, string_id[] responses)
    {
        Object[] objects = new Object[responses.length];
        System.arraycopy(responses, 0, objects, 0, responses.length);
        return npcStartConversation(player, npc, convoName, greetingId, greetingProse, objects);
    }
    public int OnStartNpcConversation(obj_id self, obj_id player)
    {
        obj_id npc = self;
        if (ai_lib.isInCombat(npc) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (collections_ice_cream_part_nine_condition_hasActiveTask(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_4");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_9");
                }
                utils.setScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId", 1);
                npcStartConversation(player, npc, "collections_ice_cream_part_nine", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (collections_ice_cream_part_nine_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_38");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("collections_ice_cream_part_nine"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
        if (branchId == 1 && collections_ice_cream_part_nine_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && collections_ice_cream_part_nine_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 3 && collections_ice_cream_part_nine_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 4 && collections_ice_cream_part_nine_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 5 && collections_ice_cream_part_nine_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 6 && collections_ice_cream_part_nine_handleBranch6(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 7 && collections_ice_cream_part_nine_handleBranch7(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 8 && collections_ice_cream_part_nine_handleBranch8(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.collections_ice_cream_part_nine.branchId");
        return SCRIPT_CONTINUE;
    }
}
