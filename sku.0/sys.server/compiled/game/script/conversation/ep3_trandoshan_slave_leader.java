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
import script.library.utils;

public class ep3_trandoshan_slave_leader extends script.base_script
{
    public ep3_trandoshan_slave_leader()
    {
    }
    public static String c_stringFile = "conversation/ep3_trandoshan_slave_leader";
    public boolean ep3_trandoshan_slave_leader_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean ep3_trandoshan_slave_leader_condition_isOnTask01(obj_id player, obj_id npc)
    {
        return groundquests.isTaskActive(player, "ep3_trando_ssiksik", 4);
    }
    public boolean ep3_trandoshan_slave_leader_condition_hasCompletedQuest01(obj_id player, obj_id npc)
    {
        return groundquests.hasCompletedQuest(player, "ep3_trando_ssiksik");
    }
    public boolean ep3_trandoshan_slave_leader_condition_isOnTask02(obj_id player, obj_id npc)
    {
        return groundquests.isTaskActive(player, "ep3_trando_ssiksik", 7);
    }
    public boolean ep3_trandoshan_slave_leader_condition_hasCompletedTask01(obj_id player, obj_id npc)
    {
        return groundquests.hasCompletedTask(player, "ep3_trando_ssiksik", 4);
    }
    public void ep3_trandoshan_slave_leader_action_doSignal01(obj_id player, obj_id npc)
    {
        groundquests.sendSignal(player, "startWookieeAttack");
    }
    public void ep3_trandoshan_slave_leader_action_doSignal02(obj_id player, obj_id npc)
    {
        groundquests.sendSignal(player, "rewardSlaverLeader");
    }
    public int ep3_trandoshan_slave_leader_handleBranch4(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_89"))
        {
            if (ep3_trandoshan_slave_leader_condition__defaultCondition(player, npc))
            {
                ep3_trandoshan_slave_leader_action_doSignal01(player, npc);
                string_id message = new string_id(c_stringFile, "s_91");
                utils.removeScriptVar(player, "conversation.ep3_trandoshan_slave_leader.branchId");
                chat.chat(npc, player, message);
                npcEndConversation(player);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.ep3_trandoshan_slave_leader");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        setName(self, "Lssiss (a slaver leader)");
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_INTERESTING);
        setName(self, "Lssiss (a slaver leader)");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo)
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.ep3_trandoshan_slave_leader");
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
        if (ep3_trandoshan_slave_leader_condition_hasCompletedQuest01(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_81");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (ep3_trandoshan_slave_leader_condition_isOnTask02(player, npc))
        {
            ep3_trandoshan_slave_leader_action_doSignal02(player, npc);
            string_id message = new string_id(c_stringFile, "s_83");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (ep3_trandoshan_slave_leader_condition_hasCompletedTask01(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_85");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (ep3_trandoshan_slave_leader_condition_isOnTask01(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_87");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (ep3_trandoshan_slave_leader_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_89");
                }
                utils.setScriptVar(player, "conversation.ep3_trandoshan_slave_leader.branchId", 4);
                npcStartConversation(player, npc, "ep3_trandoshan_slave_leader", message, responses);
            }
            else 
            {
                chat.chat(npc, player, message);
            }
            return SCRIPT_CONTINUE;
        }
        if (ep3_trandoshan_slave_leader_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_93");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("ep3_trandoshan_slave_leader"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.ep3_trandoshan_slave_leader.branchId");
        if (branchId == 4 && ep3_trandoshan_slave_leader_handleBranch4(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.ep3_trandoshan_slave_leader.branchId");
        return SCRIPT_CONTINUE;
    }
}
