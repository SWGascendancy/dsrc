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
import script.library.space_battlefield;
import script.library.space_combat;
import script.library.space_transition;
import script.library.utils;

public class battlefield_entry_station_imperial extends script.base_script
{
    public battlefield_entry_station_imperial()
    {
    }
    public static String c_stringFile = "conversation/battlefield_entry_station_imperial";
    public boolean battlefield_entry_station_imperial_condition__defaultCondition(obj_id player, obj_id npc)
    {
        return true;
    }
    public boolean battlefield_entry_station_imperial_condition_isPlayerRebel(obj_id player, obj_id npc)
    {
        return space_battlefield.isInRebelShip(player);
    }
    public boolean battlefield_entry_station_imperial_condition_isPlayerImperial(obj_id player, obj_id npc)
    {
        return space_battlefield.isInImperialShip(player);
    }
    public boolean battlefield_entry_station_imperial_condition_canAffordEntry(obj_id player, obj_id npc)
    {
        int intCost = space_battlefield.canAffordPrestigePointCost(space_transition.getContainingShip(player), npc);
        if (intCost == 0)
        {
            return false;
        }
        return true;
    }
    public boolean battlefield_entry_station_imperial_condition_isFactionCorrectForEntry(obj_id player, obj_id npc)
    {
        obj_id objShip = space_transition.getContainingShip(player);
        obj_id objOwner = getOwner(objShip);
        return space_battlefield.isInImperialShip(objOwner);
    }
    public boolean battlefield_entry_station_imperial_condition_isPlayerNeutral(obj_id player, obj_id npc)
    {
        if (space_battlefield.isInNeutralShip(player))
        {
            return true;
        }
        return false;
    }
    public boolean battlefield_entry_station_imperial_condition_isTooFar(obj_id player, obj_id npc)
    {
        space_combat.playCombatTauntSound(player);
        obj_id containingShip = space_transition.getContainingShip(player);
        return (getDistance(npc, containingShip) > space_transition.STATION_COMM_MAX_DISTANCE);
    }
    public boolean battlefield_entry_station_imperial_condition_isInYacht(obj_id player, obj_id npc)
    {
        obj_id ship = space_transition.getContainingShip(player);
        if (isIdValid(ship))
        {
            String template = getTemplateName(ship);
            if (template != null && template.endsWith("_yacht.iff"))
            {
                return true;
            }
        }
        return false;
    }
    public void battlefield_entry_station_imperial_action_gotToImperialBattlefield(obj_id player, obj_id npc)
    {
        obj_id ship = space_transition.getContainingShip(player);
        setObjVar(ship, "spaceFaction.FactionOverride", (-615855020));
        space_battlefield.doBattleFieldTransition(space_transition.getContainingShip(player), npc, "imperial_entry");
    }
    public void battlefield_entry_station_imperial_action_goToKessel(obj_id player, obj_id npc)
    {
        space_battlefield.doKesselTransition(space_transition.getContainingShip(player), npc);
    }
    public void battlefield_entry_station_imperial_action_goToRebelBattlefield(obj_id player, obj_id npc)
    {
        obj_id ship = space_transition.getContainingShip(player);
        setObjVar(ship, "spaceFaction.FactionOverride", (370444368));
        space_battlefield.doBattleFieldTransition(space_transition.getContainingShip(player), npc, "rebel_entry");
    }
    public int battlefield_entry_station_imperial_tokenDI_prestigeCost(obj_id player, obj_id npc)
    {
        return space_battlefield.getPrestigeCostForTransition(player, npc);
    }
    public int battlefield_entry_station_imperial_handleBranch5(obj_id player, obj_id npc, string_id response)
    {
        if (response.equals("s_fa22e207"))
        {
            if (!battlefield_entry_station_imperial_condition_isFactionCorrectForEntry(player, npc))
            {
                string_id message = new string_id(c_stringFile, "s_33f71018");
                utils.removeScriptVar(player, "conversation.battlefield_entry_station_imperial.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
            if (battlefield_entry_station_imperial_condition__defaultCondition(player, npc))
            {
                battlefield_entry_station_imperial_action_gotToImperialBattlefield(player, npc);
                string_id message = new string_id(c_stringFile, "s_19");
                utils.removeScriptVar(player, "conversation.battlefield_entry_station_imperial.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        if (response.equals("s_597d7f95"))
        {
            if (battlefield_entry_station_imperial_condition__defaultCondition(player, npc))
            {
                battlefield_entry_station_imperial_action_goToKessel(player, npc);
                string_id message = new string_id(c_stringFile, "s_17");
                utils.removeScriptVar(player, "conversation.battlefield_entry_station_imperial.branchId");
                npcEndConversationWithMessage(player, message);
                return SCRIPT_CONTINUE;
            }
        }
        return SCRIPT_DEFAULT;
    }
    public int OnInitialize(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setObjVar(self, "convo.appearance", "object/mobile/space_comm_imperial_officer_02.iff");
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setCondition(self, CONDITION_CONVERSABLE);
        setObjVar(self, "convo.appearance", "object/mobile/space_comm_imperial_officer_02.iff");
        detachScript(self, "space.content_tools.spacestation");
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
        detachScript(self, "conversation.battlefield_entry_station_imperial");
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
        if (battlefield_entry_station_imperial_condition_isInYacht(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_358253b2");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (battlefield_entry_station_imperial_condition_isPlayerRebel(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_33077605");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (battlefield_entry_station_imperial_condition_isPlayerNeutral(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_2e491159");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (battlefield_entry_station_imperial_condition_isTooFar(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_48f82131");
            chat.chat(npc, player, message);
            return SCRIPT_CONTINUE;
        }
        if (battlefield_entry_station_imperial_condition__defaultCondition(player, npc))
        {
            string_id message = new string_id(c_stringFile, "s_a136b7d3");
            int numberOfResponses = 0;
            boolean hasResponse = false;
            boolean hasResponse0 = false;
            if (battlefield_entry_station_imperial_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse0 = true;
            }
            boolean hasResponse1 = false;
            if (battlefield_entry_station_imperial_condition__defaultCondition(player, npc))
            {
                ++numberOfResponses;
                hasResponse = true;
                hasResponse1 = true;
            }
            if (hasResponse)
            {
                int responseIndex = 0;
                string_id responses[] = new string_id[numberOfResponses];
                if (hasResponse0)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_fa22e207");
                }
                if (hasResponse1)
                {
                    responses[responseIndex++] = new string_id(c_stringFile, "s_597d7f95");
                }
                utils.setScriptVar(player, "conversation.battlefield_entry_station_imperial.branchId", 5);
                npcStartConversation(player, npc, "battlefield_entry_station_imperial", message, responses);
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
        if (!conversationId.equals("battlefield_entry_station_imperial"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id npc = self;
        int branchId = utils.getIntScriptVar(player, "conversation.battlefield_entry_station_imperial.branchId");
        if (branchId == 5 && battlefield_entry_station_imperial_handleBranch5(player, npc, response) == SCRIPT_CONTINUE)
        {
            return SCRIPT_CONTINUE;
        }
        chat.chat(npc, "Error:  Fell through all branches and responses for OnNpcConversationResponse.");
        utils.removeScriptVar(player, "conversation.battlefield_entry_station_imperial.branchId");
        return SCRIPT_CONTINUE;
    }
}