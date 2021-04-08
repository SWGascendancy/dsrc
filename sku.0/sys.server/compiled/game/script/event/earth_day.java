package script.event;

import script.*;
import script.base_class.*;
import script.base_script;

import script.library.*;

public class earth_day extends script.base_script
{
    public earth_day()
    {
    }
    public static final int CASH_AMOUNT = 5000;
    public static final String STF = "city/city";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("content_tyrone", "turn_in_item"));
        int menu2 = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("content_tyrone","check_points"));
        if(isGod(player))
        {
            int menu3 = mi.addRootMenu(menu_info_types.SERVER_MENU2, new string_id("content_tyrone", "god_mode"));
            mi.addSubMenu(menu3, menu_info_types.SERVER_MENU3, new string_id("content_tyrone", "clear_rewards"));
            mi.addSubMenu(menu3, menu_info_types.SERVER_MENU4, new string_id("content_tyrone", "clear_points"));
            mi.addSubMenu(menu3, menu_info_types.SERVER_MENU5, new string_id("content_tyrone", "set_item"));
            int tiersettings = mi.addRootMenu(menu_info_types.SERVER_MENU6, new string_id("content_tyrone", "reward_setup"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU7, new string_id("content_tyrone", "t1"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU8, new string_id("content_tyrone", "t2"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU9, new string_id("content_tyrone", "t3"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU10, new string_id("content_tyrone", "t4"));
            mi.addSubMenu(tiersettings, menu_info_types.SERVER_MENU11, new string_id("content_tyrone", "t5"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        setName(self, "Galactic Recycling Association Terminal");
        //persistObject(self);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.ITEM_USE)
        {
            String ITEM = getStringObjVar(self, "item_to_use");
            if (utils.playerHasItemByTemplateInInventoryOrEquipped(player, ITEM))
            {
                int stamps = getIntObjVar(player, "item_turn_in.stamps");
                String strTemplate = ITEM;
                obj_id objPlayer = player;
                obj_id item_to_delete = utils.getItemPlayerHasByTemplate(objPlayer, strTemplate); // This will be the first item. Add stack support and index them.
                destroyObject(item_to_delete);
                if (stamps > 50)
                {
                    sendSystemMessageTestingOnly(player, "Thank you!");
                }
                else if (stamps < 50)
                {
                    sendSystemMessageTestingOnly(player, "You have successfully recycled an item and have earned one (1) point toward a reward. You now have a total of " + stamps + " points.");
                }
                if (!hasObjVar(player, "item_turn_in.stamps"))
                {
                    setObjVar(player, "item_turn_in.stamps", 1);
                }
                else if (hasObjVar(player, "item_turn_in.stamps"))
                {
                    int currentStamps = getIntObjVar(player, "item_turn_in.stamps");
                    setObjVar(player, "item_turn_in.stamps", currentStamps + 1);
                    checkForRewards(self, player);
                }
            }
            else if (!utils.playerHasItemByTemplate(player, getStringObjVar(self, "item_to_use")))
            {
                sendSystemMessageTestingOnly(player, "You do not have anything to recycle at this time.");
            }
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            String title = "Congratulations!";
            String buffer = "You current have " + getIntObjVar(player, "item_turn_in.stamps") + " recycle points!";
            sui.msgbox(self, player, buffer, title);
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            sendSystemMessageTestingOnly(player, "This menu will reset rewards, or clear points. choose wisely my dudes");
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            removeObjVar(player, "stamps.t1_reward_given");
            removeObjVar(player, "stamps.t2_reward_given");
            removeObjVar(player, "stamps.t3_reward_given");
            removeObjVar(player, "stamps.t4_reward_given");
            removeObjVar(player, "stamps.t5_reward_given");
            sendSystemMessageTestingOnly(player, "Rewards reset!");
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            setObjVar(player, "item_turn_in.stamps", 0);
            sendSystemMessageTestingOnly(player, "Recycle Points reset to zero!");
        }
        if (item == menu_info_types.SERVER_MENU5)
        {
            String objectToCheck = "";
            String buffer = "Set the item to use and delete here.";
            String title = "Item Check";
            sui.filteredInputbox(self, player, buffer, title, "handleCheckRequest", objectToCheck);
        }
        if (item == menu_info_types.SERVER_MENU7)
        {
            String t1ToCheck = "";
            String buffer = "Set the item to reward for Tier 1";
            String title = "Reward Setup";
            sui.filteredInputbox(self, player, buffer, title, "handleT1Request", t1ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU8)
        {
            String t2ToCheck = "";
            String buffer = "Set the item to reward for Tier 2";
            String title = "Reward Setup";
            sui.filteredInputbox(self, player, buffer, title, "handleT2Request", t2ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU9)
        {
            String t3ToCheck = "";
            String buffer = "Set the item to reward for Tier 3";
            String title = "Reward Setup";
            sui.filteredInputbox(self, player, buffer, title, "handleT3Request", t3ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU10)
        {
            String t4ToCheck = "";
            String buffer = "Set the item to reward for Tier 4";
            String title = "Reward Setup";
            sui.filteredInputbox(self, player, buffer, title, "handleT4Request", t4ToCheck);
        }
        if (item == menu_info_types.SERVER_MENU11)
        {
            String t5ToCheck = "";
            String buffer = "Set the item to reward for Tier 5";
            String title = "Reward Setup";
            sui.filteredInputbox(self, player, buffer, title, "handleT5Request", t5ToCheck);
        }
        return SCRIPT_CONTINUE;
    }
    public int checkForRewards(obj_id self, obj_id player)
    {
       int stamps = getIntObjVar(player, "item_turn_in.stamps");
       if (stamps > 50)
       {
           payCredits(player);
       }
       if (stamps == 10 && !hasObjVar(player, "stamps.t1_reward_given"))
       {
            String title = "Congratulations!";
            String buffer = "You have earned enough points to recieve a GRA Tier 1 reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t1_reward_given", true);
            grantT1Reward(player);
       }
       if (stamps == 20 && !hasObjVar(player, "stamps.t2_reward_given"))
       {
            String title = "Congratulations!";
            String buffer = "You have earned enough points to recieve a GRA Tier 2 reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t2_reward_given", true);
            grantT2Reward(player);
       }
       if (stamps == 35 && !hasObjVar(player, "stamps.t3_reward_given"))
       {
            String title = "Congratulations!";
            String buffer = "You have earned enough points to recieve a GRA Tier 3 reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t3_reward_given", true);
            grantT3Reward(player);
       }
       if (stamps == 45 && !hasObjVar(player, "stamps.t4_reward_given"))
       {
            String title = "Congratulations!";
            String buffer = "You have earned enough points to recieve a GRA Tier 4 reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t4_reward_given", true);
            grantT4Reward(player);
       }
       if (stamps == 50 && !hasObjVar(player, "stamps.t5_reward_given"))
       {
            String title = "Congratulations!";
            String buffer = "You have earned enough points to recieve a GRA Tier 5 reward!";
            sui.msgbox(self, player, buffer, title);
            setObjVar(player, "stamps.t5_reward_given", true);
            grantT5Reward(player);
       }
       return SCRIPT_CONTINUE;
    }
    public int grantT1Reward(obj_id player)
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t1_reward = getStringObjVar(player, "event.earth_day_t1_reward");
        static_item.createNewItemFunction(t1_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the Tier 1 reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT2Reward(obj_id player)
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t2_reward = getStringObjVar(player, "event.earth_day_t2_reward");
        static_item.createNewItemFunction(t2_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the Tier 2 reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT3Reward(obj_id player)
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t3_reward = getStringObjVar(player, "event.earth_day_t3_reward");
        static_item.createNewItemFunction(t3_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the Tier 3 reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT4Reward(obj_id player)
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t4_reward = getStringObjVar(player, "event.earth_day_t4_reward");
        static_item.createNewItemFunction(t4_reward, pInv);
        sendSystemMessageTestingOnly(player, "You have recieved the Tier 4 reward!");
        return SCRIPT_CONTINUE;
    }
    public int grantT5Reward(obj_id player)
    {
        obj_id pInv = utils.getInventoryContainer(player);
        String t5_reward = getStringObjVar(player, "event.earth_day_t5_reward");
        static_item.createNewItemFunction(t5_reward, pInv);
        //badge
        //title (skill rather)
        sendSystemMessageTestingOnly(player, "You have recieved the Tier 5 reward!");// and the Galactic Recycling Association badge and title!");
        return SCRIPT_CONTINUE;
    }
    public void payCredits(obj_id player)
    {
        setObjVar(player, "event.starting_earth_day_credits", getCashBalance(player));
        int moneyz = getIntObjVar(player, "event.starting_earth_day_credits");
        if (moneyz <= 250000)
        {
            dictionary d = new dictionary();
            d.put("payoutTarget", player);
            money.systemPayout(money.ACCT_BETA_TEST, player, CASH_AMOUNT, "handlePayoutToPlayer", d);
        }
        else 
        {
            sendSystemMessageTestingOnly(player, "The Galactic Recycling Association thanks you for all your work, but you currently exceed the maximum amount we can give you. Have you considered donating to a charity?");
        }
    }
    public int handlePayoutToPlayer(obj_id self, dictionary params)
    {
        obj_id player = params.getObjId("payoutTarget");
        if (!isIdValid(player))
        {
            return SCRIPT_CONTINUE;
        }
        int retCode = params.getInt(money.DICT_CODE);
        if (retCode == money.RET_SUCCESS)
        {
            String terminal = "Galactic Recycling Association";
            sendSystemMessageTestingOnly(player, "You receive " + CASH_AMOUNT + " credits from the " + terminal + " for your hard work keeping the galaxy clean.");
        }
        else if (retCode == money.RET_FAIL)
        {
            sendSystemMessageTestingOnly(player, "The system is unable to complete the transaction.");
        }
        return SCRIPT_CONTINUE;
    }
    public int handleCheckRequest(obj_id self, dictionary params)
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String objectToCheck = sui.getInputBoxText(params);
        if (objectToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (!objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "You cannot use static items for this. It must be single tangible items to avoid duplication and exploits");
        }
        setObjVar(self, "item_to_use", objectToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT1Request(obj_id self, dictionary params)
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String t1ToCheck = sui.getInputBoxText(params);
        if (t1ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        /*if (objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }*/
        setObjVar(self, "event.earth_day_t1_reward", t1ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT2Request(obj_id self, dictionary params)
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String t2ToCheck = sui.getInputBoxText(params);
        if (t2ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        /*if (objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }*/
        setObjVar(self, "event.earth_day_t2_reward", t2ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT3Request(obj_id self, dictionary params)
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String t3ToCheck = sui.getInputBoxText(params);
        if (t3ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }/*
        if (objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }*/
        setObjVar(self, "event.earth_day_t3_reward", t3ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT4Request(obj_id self, dictionary params)
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String t4ToCheck = sui.getInputBoxText(params);
        if (t4ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }/*
        if (objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }*/
        setObjVar(self, "event.earth_day_t4_reward", t4ToCheck);
        return SCRIPT_CONTINUE;
    }
    public int handleT5Request(obj_id self, dictionary params)
    {
        if (params == null || params.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        String t5ToCheck = sui.getInputBoxText(params);
        if (t5ToCheck == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }/*
        if (objectToCheck.endsWith(".iff"))
        {
            sendSystemMessageTestingOnly(player, "Not allowed.");
        }*/
        setObjVar(self, "event.earth_day_t5_reward", t5ToCheck);
        return SCRIPT_CONTINUE;
    }
}