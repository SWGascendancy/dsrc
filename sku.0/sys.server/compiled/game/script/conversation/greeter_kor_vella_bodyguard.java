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

public class greeter_kor_vella_bodyguard extends script.base_script
{
    public greeter_kor_vella_bodyguard()
    {
    }
    public static String c_stringFile = "conversation/greeter_kor_vella_bodyguard";
    public boolean greeter_kor_vella_bodyguard_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean greeter_kor_vella_bodyguard_condition_remembersPlayer(obj_id player, obj_id npc)
    {
        return (utils.hasScriptVar(player, "metNewbiePilot"));
    }
    public boolean greeter_kor_vella_bodyguard_condition_isAnImperialPilot(obj_id player, obj_id npc)
    {
        return space_flags.isImperialPilot(player);
    }
    public boolean greeter_kor_vella_bodyguard_condition_isARebelPilot(obj_id player, obj_id npc)
    {
        return space_flags.isRebelPilot(player);
    }
    public boolean greeter_kor_vella_bodyguard_condition_isPrivateerPilot(obj_id player, obj_id npc)
    {
        return space_flags.isNeutralPilot(player);
    }
    public boolean greeter_kor_vella_bodyguard_condition_hasSpaceExpansion(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean greeter_kor_vella_bodyguard_condition_hasSpaceShip(obj_id player, obj_id npc)
    {
        return (space_quest.hasShip(player));
    }
    public void greeter_kor_vella_bodyguard_action_rememberPlayer(obj_id player, obj_id npc)
    {
        utils.setScriptVar(player, "metNewbiePilot", true);
    }
    public void greeter_kor_vella_bodyguard_action_grantQuestOne(obj_id player, obj_id npc)
    {
        space_quest.grantQuest(player, "delivery", "tatooine_newbie_1");
        space_quest.grantNewbieShip(player, "rebel");
    }
    public int greeter_kor_vella_bodyguard_handleBranch1(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_e9337a35"))
        {
            if (greeter_kor_vella_bodyguard_condition__defaultCondition(player, npc))
            {
                doAnimationAction(npc, "point_forward");
                string_id message = new string_id(c_stringFile, "s_8f1601d8");
                removeObjVar(player, "conversation.greeter_kor_vella_bodyguard.branchId");
                npcSpeak(player, message);
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
            detachScript(self, "conversation.greeter_kor_vella_bodyguard");
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
        detachScript(self, "conversation.greeter_kor_vella_bodyguard");
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
        if (greeter_kor_vella_bodyguard_condition__defaultCondition(player, npc))
        {
            doAnimationAction(npc, "standing_placate");
            string_id message = new string_id(c_stringFile, "s_2fd6a00b");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (greeter_kor_vella_bodyguard_condition__defaultCondition(player, npc))
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
                    responses[responseIndex++] = new string_id(c_stringFile, "s_e9337a35");
                }
                setObjVar(player, "conversation.greeter_kor_vella_bodyguard.branchId", 1);
                npcStartConversation(player, npc, "greeter_kor_vella_bodyguard", message, responses);
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
        if (!conversationId.equals("greeter_kor_vella_bodyguard"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = getIntObjVar(player, "conversation.greeter_kor_vella_bodyguard.branchId");
        if (branchId == 1 && greeter_kor_vella_bodyguard_handleBranch1(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        removeObjVar(player, "conversation.greeter_kor_vella_bodyguard.branchId");
        return SCRIPT_CONTINUE;
    }
}