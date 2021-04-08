package script.conversation;

import script.*;

import script.library.ai_lib;
import script.library.chat;
import script.library.factions;
import script.library.utils;

public class imperial_space_gcw_vendor extends script.base_script
{
    public imperial_space_gcw_vendor()
    {
    }
    public static final String c_stringFile = "conversation/imperial_gcw2_vendor";
    private boolean defaultCondition()
    {
        return true;
    }
    private boolean isRebel(obj_id player)
    {
        return factions.isRebel(player);
    }
    private boolean isNeutral(obj_id player)
    {
        return factions.isNeutral(player);
    }
    private void showTokenVendorUI(obj_id player, obj_id npc)
    {
        dictionary d = new dictionary();
        d.put("player", player);
        messageTo(npc, "showInventorySUI", d, 0, false);
    }
    public int OnInitialize(obj_id self)
    {
        if (!isTangible(self) || isPlayer(self))
        {
            detachScript(self, "conversation.imperial_space_gcw_vendor");
        }
        setName(self, "Imperial Space Vendor");
        setCondition(self, CONDITION_CONVERSABLE);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setName(self, "Imperial Space Vendor");
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
        detachScript(self, "conversation.imperial_space_gcw_vendor");
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
        faceTo(self, player);
        if (isRebel(player))
        {
            doAnimationAction(self, "dismiss");
            string_id message = new string_id(c_stringFile, "s_4");
            chat.chat(self, player, message);
            return SCRIPT_CONTINUE;
        }
        if (isNeutral(player))
        {
            showTokenVendorUI(player, self);
            string_id message = new string_id(c_stringFile, "s_7");
            chat.chat(self, player, message);
            return SCRIPT_CONTINUE;
        }
        if (defaultCondition())
        {
        doAnimationAction(self, "salute1");
        showTokenVendorUI(player, self);
        string_id message = new string_id(c_stringFile, "s_8");
        chat.chat(self, player, message);
        return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("imperial_space_gcw_vendor"))
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.imperial_space_gcw_vendor.branchId");
        return SCRIPT_CONTINUE;
    }
}
