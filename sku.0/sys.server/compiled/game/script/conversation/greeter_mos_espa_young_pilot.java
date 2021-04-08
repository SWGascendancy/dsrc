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

import script.library.skill;
import script.library.space_flags;
import script.library.space_quest;
import script.library.utils;

public class greeter_mos_espa_young_pilot extends script.base_script
{
    public greeter_mos_espa_young_pilot()
    {
    }
    public static String c_stringFile = "conversation/greeter_mos_espa_young_pilot";
    public boolean greeter_mos_espa_young_pilot_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean greeter_mos_espa_young_pilot_condition_remembersPlayer(obj_id player, obj_id npc)
    {
        return (utils.hasScriptVar(player, "metNewbiePilot"));
    }
    public boolean greeter_mos_espa_young_pilot_condition_isAnImperialPilot(obj_id player, obj_id npc)
    {
        return space_flags.isImperialPilot(player);
    }
    public boolean greeter_mos_espa_young_pilot_condition_isARebelPilot(obj_id player, obj_id npc)
    {
        return space_flags.isRebelPilot(player);
    }
    public boolean greeter_mos_espa_young_pilot_condition_isPrivateerPilot(obj_id player, obj_id npc)
    {
        return space_flags.isNeutralPilot(player);
    }
    public boolean greeter_mos_espa_young_pilot_condition_hasSpaceExpansion(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean greeter_mos_espa_young_pilot_condition_hasSpaceShip(obj_id player, obj_id npc)
    {
        return (space_quest.hasShip(player));
    }
    public void greeter_mos_espa_young_pilot_action_rememberPlayer(obj_id player, obj_id npc)
    {
        utils.setScriptVar(player, "metNewbiePilot", true);
    }
    public void greeter_mos_espa_young_pilot_action_grantQuestOne(obj_id player, obj_id npc)
    {
        space_quest.grantQuest(player, "delivery", "tatooine_newbie_1");
        space_quest.grantNewbieShip(player, "rebel");
    }
    public int greeter_mos_espa_young_pilot_handleBranch1(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_c088465d"))
        {
            if (greeter_mos_espa_young_pilot_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "explain");
                string_id message = new string_id(c_stringFile, "s_43eb0d5");
                int numberOfResponses = 0;
                boolean hasResponse = false;
                boolean hasResponse0 = false;
                if (greeter_mos_espa_young_pilot_condition__defaultCondition(player, npc))
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
                        responses[responseIndex++] = new string_id(c_stringFile, "s_58da73fe");
                    }
                    utils.setScriptVar(player, "conversation.greeter_mos_espa_young_pilot.branchId", 2);
                    npcSpeak(player, message);
                    npcSetConversationResponses(player, responses);
                }
                else 
                {
                    utils.removeScriptVar(player, "conversation.greeter_mos_espa_young_pilot.branchId");
                    npcEndConversationWithMessage(player, message);
                }
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int greeter_mos_espa_young_pilot_handleBranch2(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_58da73fe"))
        {
            if (greeter_mos_espa_young_pilot_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "nod_head_multiple");
                string_id message = new string_id(c_stringFile, "s_5ce080f5");
                utils.removeScriptVar(player, "conversation.greeter_mos_espa_young_pilot.branchId");
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
            detachScript(self, "conversation.greeter_mos_espa_young_pilot");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setCondition(self, CONDITION_SPACE_INTERESTING);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        setCondition(self, CONDITION_SPACE_INTERESTING);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info menuInfo)
    {
        int menu = menuInfo.addRootMenu(menu_info_types.CONVERSE_START, null);
        menu_info_data menuInfoData = menuInfo.getMenuItemById(menu);
        menuInfoData.setServerNotify(false);
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        setCondition(self, CONDITION_SPACE_INTERESTING);
        detachScript(self, "conversation.greeter_mos_espa_young_pilot");
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
        if (greeter_mos_espa_young_pilot_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "shrug_hands");
            string_id message = new string_id(c_stringFile, "s_16e2abb");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (greeter_mos_espa_young_pilot_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_c088465d");
                }
                utils.setScriptVar(player, "conversation.greeter_mos_espa_young_pilot.branchId", 1);
                npcStartConversation(player, npc, "greeter_mos_espa_young_pilot", message, responses);
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
        if (!conversationId.equals("greeter_mos_espa_young_pilot"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.greeter_mos_espa_young_pilot.branchId");
        if (branchId == 1 && greeter_mos_espa_young_pilot_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        if (branchId == 2 && greeter_mos_espa_young_pilot_handleBranch2(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.greeter_mos_espa_young_pilot.branchId");
        return SCRIPT_CONTINUE;
    }
}