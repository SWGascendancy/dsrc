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
import script.library.utils;

public class npe_jolka_falcon extends script.base_script
{
    public npe_jolka_falcon()
    {
    }
    public static String c_stringFile = "conversation/npe_jolka_falcon";
    public boolean npe_jolka_falcon_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public void npe_jolka_falcon_action_talkedToHan(obj_id player, obj_id npc)
    {
        obj_id building = getTopMostContainer(npc);
        if (isIdValid(building))
        {
            messageTo(building, "playerTalkedToHan", null, 0, false);
        }
    }
    public int npe_jolka_falcon_handleBranch1(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_6"))
        {
            if (npe_jolka_falcon_condition__defaultCondition(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_13");
                utils.removeScriptVar(player, "conversation.npe_jolka_falcon.branchId");
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
            detachScript(self, "conversation.npe_jolka_falcon");
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
        detachScript(self, "conversation.npe_jolka_falcon");
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
        if (npe_jolka_falcon_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_4");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (npe_jolka_falcon_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_6");
                }
                utils.setScriptVar(player, "conversation.npe_jolka_falcon.branchId", 1);
                npcStartConversation(player, npc, "npe_jolka_falcon", message, responses);
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
        if (!conversationId.equals("npe_jolka_falcon"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.npe_jolka_falcon.branchId");
        if (branchId == 1 && npe_jolka_falcon_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.npe_jolka_falcon.branchId");
        return SCRIPT_CONTINUE;
    }
}
