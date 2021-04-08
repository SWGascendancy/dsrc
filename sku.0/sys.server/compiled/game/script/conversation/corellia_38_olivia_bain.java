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
import script.library.groundquests;
import script.library.space_quest;
import script.library.utils;

public class corellia_38_olivia_bain extends script.base_script
{
    public corellia_38_olivia_bain()
    {
    }
    public static String c_stringFile = "conversation/corellia_38_olivia_bain";
    public boolean corellia_38_olivia_bain_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean corellia_38_olivia_bain_condition_questOneActive(obj_id player, obj_id npc)
    {
        return groundquests.isQuestActive(player, "corellia_38_sidequest_05");
    }
    public boolean corellia_38_olivia_bain_condition_taskOneComplete(obj_id player, obj_id npc)
    {
        return groundquests.isTaskActive(player, "corellia_38_sidequest_05", "corellia_38_sidequest_05_04") || groundquests.hasCompletedQuest(player, "corellia_38_sidequest_05");
    }
    public void corellia_38_olivia_bain_action_questGranted(obj_id player, obj_id npc)
    {
        groundquests.grantQuest(player, "corellia_38_sidequest_05");
    }
    public void corellia_38_olivia_bain_action_taskOneCompletedSignal(obj_id player, obj_id npc)
    {
        groundquests.sendSignal(player, "corellia_38_sidequest_05_04");
    }
    public int corellia_38_olivia_bain_handleBranch3(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_46"))
        {
            if (corellia_38_olivia_bain_condition__defaultCondition(player, npc))
            {
                corellia_38_olivia_bain_action_questGranted(player, npc);
                string_id message = new string_id(c_stringFile, "s_48");
                utils.removeScriptVar(player, "conversation.corellia_38_olivia_bain.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_47"))
        {
            if (corellia_38_olivia_bain_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_49");
                utils.removeScriptVar(player, "conversation.corellia_38_olivia_bain.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.corellia_38_olivia_bain");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo)
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.corellia_38_olivia_bain");
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
        if (corellia_38_olivia_bain_condition_taskOneComplete(player, npc))
        {
            corellia_38_olivia_bain_action_taskOneCompletedSignal(player, npc);
            string_id message = new string_id(c_stringFile, "s_43");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_38_olivia_bain_condition_questOneActive(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_44");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (corellia_38_olivia_bain_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_45");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (corellia_38_olivia_bain_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (corellia_38_olivia_bain_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_46");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_47");
                }
                utils.setScriptVar(player, "conversation.corellia_38_olivia_bain.branchId", 3);
                npcStartConversation(player, npc, "corellia_38_olivia_bain", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("corellia_38_olivia_bain"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.corellia_38_olivia_bain.branchId");
        if (branchId == 3 && corellia_38_olivia_bain_handleBranch3(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.corellia_38_olivia_bain.branchId");
        return SCRIPT_CONTINUE;
    }
}
