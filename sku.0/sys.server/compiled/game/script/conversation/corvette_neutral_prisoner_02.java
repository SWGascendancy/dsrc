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
import script.library.factions;
import script.library.group;

public class corvette_neutral_prisoner_02 extends script.base_script
{
    public corvette_neutral_prisoner_02()
    {
    }
    public static String c_stringFile = "conversation/corvette_neutral_prisoner_02";
    public boolean corvette_neutral_prisoner_02_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean corvette_neutral_prisoner_02_condition_rescued(obj_id player, obj_id npc)
    {
        return hasObjVar(npc, "corl_corvette.alreadyRescued");
    }
    public void corvette_neutral_prisoner_02_action__defaultAction(obj_id player, obj_id npc)
    {
    }
    public void corvette_neutral_prisoner_02_action_factionReward(obj_id player, obj_id npc)
    {
        faceTo(npc, player);
        if (!hasObjVar(npc, "corl_corvette.alreadyRescued"))
        {
            factions.addFactionStanding(player, "jabba", 10);
            setObjVar(npc, "corl_corvette.alreadyRescued", 1);
            messageTo(npc, "escapeNow", null, 2, false);
            if (group.isGrouped(player))
            {
                obj_id groupObj = getGroupObject(player);
                obj_id[] groupMembers = getGroupMemberIds(groupObj);
                int numGroupMembers = groupMembers.length;
                for (int f = 0; f < numGroupMembers; f++)
                {
                    obj_id groupie = groupMembers[f];
                    if (isIdValid(groupie))
                    {
                        if (groupie != player)
                        {
                            factions.addFactionStanding(groupie, "jabba", 7);
                        }
                    }
                }
            }
        }
        return;
    }
    public void corvette_neutral_prisoner_02_action_facePlayer(obj_id player, obj_id npc)
    {
        faceTo(npc, player);
        return;
    }
    public int OnInitialize(obj_id self)
    {
        if ((!isMob(self)) || (isPlayer(self)))
        {
            detachScript(self, "npc.conversation.corvette_neutral_prisoner_02");
        }
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setInvulnerable(self, true);
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
        detachScript(self, "npc.conversation.corvette_neutral_prisoner_02");
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id player)
    {
        if (ai_lib.isInCombat(self) || ai_lib.isInCombat(player))
        {
            return SCRIPT_OVERRIDE;
        }
        if (corvette_neutral_prisoner_02_condition_rescued(player, self))
        {
            string_id message = new string_id(c_stringFile, "s_169c1160");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
        if (corvette_neutral_prisoner_02_condition__defaultCondition(player, self))
        {
            corvette_neutral_prisoner_02_action_factionReward(player, self);
            string_id message = new string_id(c_stringFile, "s_941cfab3");
            chat.chat(self, message);
            return SCRIPT_CONTINUE;
        }
        chat.chat(self, "Error:  All conditions for OnStartNpcConversation were false.");
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String conversationId, obj_id player, string_id response)
    {
        if (!conversationId.equals("corvette_neutral_prisoner_02"))
        {
            return SCRIPT_CONTINUE;
        }
        int branchId = getIntObjVar(player, "conversation.corvette_neutral_prisoner_02.branchId");
        chat.chat(self, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        removeObjVar(player, "conversation.corvette_neutral_prisoner_02.branchId");
        return SCRIPT_CONTINUE;
    }
}
