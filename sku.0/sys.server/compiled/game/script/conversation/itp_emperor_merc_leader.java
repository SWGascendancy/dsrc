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

public class itp_emperor_merc_leader extends script.base_script
{
    public itp_emperor_merc_leader()
    {
    }
    public static String c_stringFile = "conversation/itp_emperor_merc_leader";
    public boolean itp_emperor_merc_leader_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean itp_emperor_merc_leader_condition_emperorTaskActive(obj_id player, obj_id npc)
    {
        return groundquests.isTaskActive(player, "itp_emperor_01", "itp_emperor_01_01");
    }
    public boolean itp_emperor_merc_leader_condition_emperorTask02Active(obj_id player, obj_id npc)
    {
        return groundquests.isTaskActive(player, "itp_emperor_01", "itp_emperor_01_04");
    }
    public void itp_emperor_merc_leader_action_emperorSignal(obj_id player, obj_id npc)
    {
        groundquests.sendSignal(player, "itp_emperor_01_01");
    }
    public void itp_emperor_merc_leader_action_emperor02Signal(obj_id player, obj_id npc)
    {
        groundquests.sendSignal(player, "itp_emperor_01_04");
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.itp_emperor_merc_leader");
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
        faceTo(self, player);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        clearCondition(self, CONDITION_CONVERSABLE);
        detachScript(self, "conversation.itp_emperor_merc_leader");
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
        if (itp_emperor_merc_leader_condition_emperorTask02Active(player, npc))
        {
            itp_emperor_merc_leader_action_emperor02Signal(player, npc);
            string_id message = new string_id(c_stringFile, "s_5");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (itp_emperor_merc_leader_condition_emperorTaskActive(player, npc))
        {
            itp_emperor_merc_leader_action_emperorSignal(player, npc);
            string_id message = new string_id(c_stringFile, "s_32");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (itp_emperor_merc_leader_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_34");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("itp_emperor_merc_leader"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.itp_emperor_merc_leader.branchId");
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.itp_emperor_merc_leader.branchId");
        return SCRIPT_CONTINUE;
    }
}
