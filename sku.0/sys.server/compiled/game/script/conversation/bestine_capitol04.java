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

public class bestine_capitol04 extends script.base_script
{
    public bestine_capitol04()
    {
    }
    public static String c_stringFile = "conversation/bestine_capitol04";
    public boolean bestine_capitol04_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean bestine_capitol04_condition_nonoffice(obj_id player, obj_id npc)
    {
        return hasObjVar(npc, "bestine.electionEnded");
    }
    public void bestine_capitol04_action__defaultAction(obj_id player, obj_id npc)
    {
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "npc.conversation.bestine_capitol04");
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
        detachScript(self, "npc.conversation.bestine_capitol04");
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id player)
    {
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (!bestine_capitol04_condition_nonoffice(player, self))
        {
            string_id message = new string_id(c_stringFile, "s_754d87ee");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
        if (bestine_capitol04_condition_nonoffice(player, self))
        {
            string_id message = new string_id(c_stringFile, "s_2fcca84c");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("bestine_capitol04"))
        {
            return SCRIPT_CONTINUE;
        }
        int branchId = getIntObjVar(player, "conversation.bestine_capitol04.branchId");
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        removeObjVar(player, "conversation.bestine_capitol04.branchId");
        return SCRIPT_CONTINUE;
    }
}
