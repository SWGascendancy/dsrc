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
import script.library.utils;

public class stormtrooper_leader_mos_eisley extends script.base_script
{
    public stormtrooper_leader_mos_eisley()
    {
    }
    public static String c_stringFile = "conversation/stormtrooper_leader_mos_eisley";
    public boolean stormtrooper_leader_mos_eisley_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public void stormtrooper_leader_mos_eisley_action_moveAlongSound(obj_id player, obj_id npc)
    {
        faceTo(npc, player);
        play2dNonLoopingMusic(player, "sound/voice_stormtrooper_move_along.snd");
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isTangible(self)) || (isPlayer(self)))
        {
            detachScript(self, "conversation.stormtrooper_leader_mos_eisley");
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
        detachScript(self, "conversation.stormtrooper_leader_mos_eisley");
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
        if (stormtrooper_leader_mos_eisley_condition__defaultCondition(player, npc))
        {
            stormtrooper_leader_mos_eisley_action_moveAlongSound(player, npc);
            string_id message = new string_id(c_stringFile, "s_3");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("stormtrooper_leader_mos_eisley"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.stormtrooper_leader_mos_eisley.branchId");
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.stormtrooper_leader_mos_eisley.branchId");
        return SCRIPT_CONTINUE;
    }
}
