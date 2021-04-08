package script.conversation;

import script.*;
import script.base_class.*;
import script.base_script;

import script.library.ai_lib;
import script.library.chat;
import script.library.conversation;
import script.library.factions;
import script.library.utils;

public class halloween_special_vendor extends script.base_script
{
    public static final String c_stringFile = "conversation/halloween_special_vendor";

    public boolean halloween_special_vendor_condition_defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }

    public void halloween_special_vendor_action_showTokenVendorUI(obj_id player, obj_id npc)
    {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }

    public int OnInitialize(obj_id self)
    {
        if (!isTangible(self) || isPlayer(self))
        {
            detachScript(self, "conversation.halloween_special_vendor");
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
        detachScript(self, "conversation.halloween_special_vendor");
        return SCRIPT_CONTINUE;
    }

    public int OnDetach(obj_id self)
    {
        clearCondition(self, CONDITION_CONVERSABLE);
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
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (halloween_special_vendor_condition_defaultCondition(player, self))
        {
            halloween_special_vendor_action_showTokenVendorUI(player, self);
            string_id message = new string_id(c_stringFile, "s_1");
            chat.chat(self, player, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }

    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("halloween_special_vendor")) {
            return SCRIPT_CONTINUE;
        }
        int branchId = utils.getIntScriptVar(player, "conversation.halloween_special_vendor.branchId");
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.halloween_special_vendor.branchId");
        return SCRIPT_CONTINUE;
    }
}
