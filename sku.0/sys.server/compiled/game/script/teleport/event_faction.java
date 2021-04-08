package script.teleport;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;
import java.util.StringTokenizer;

import script.library.ai_lib;
import script.library.armor;
import script.library.beast_lib;
import script.library.buff;
import script.library.callable;
import script.library.chat;
import script.library.consumable;
import script.library.craftinglib;
import script.library.create;
import script.library.expertise;
import script.library.factions;
import script.library.gm;
import script.library.groundquests;
import script.library.healing;
import script.library.incubator;
import script.library.instance;
import script.library.jedi;
import script.library.money;
import script.library.pet_lib;
import script.library.player_stomach;

import script.library.resource;
import script.library.respec;
import script.library.skill;
import script.library.skill_template;
import script.library.space_crafting;
import script.library.space_flags;
import script.library.space_skill;
import script.library.space_transition;
import script.library.space_utils;
import script.library.static_item;
import script.library.stealth;
import script.library.sui;
import script.library.utils;
import script.library.weapons;
import script.library.performance;

public class event_faction extends script.base_script
{
    public event_faction()
    {
    }
    public static final String STF = "city/city";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
        {
            menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
            int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("gm_teleport", "teleport"));
            int menu2 = mi.addRootMenu(menu_info_types.SERVER_MENU8, new string_id("gm_teleport","read_about"));
            if(isGod(player))
            {
                int menu1 = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("gm_teleport", "setup"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU2, new string_id("gm_teleport", "scene"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU3, new string_id("gm_teleport", "x"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU5, new string_id("gm_teleport", "z"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU4, new string_id("gm_teleport", "y"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU6, new string_id("gm_teleport", "faction"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU9, new string_id("gm_teleport", "add_sound"));
                mi.addSubMenu(menu1, menu_info_types.SERVER_MENU7, new string_id("gm_teleport", "set_about"));
            }
            return SCRIPT_CONTINUE;
        }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.ITEM_USE)
        {
            String planet = getStringObjVar(self, "teleport.scene");
            String faction = getStringObjVar(self, "teleport.faction");
            String xcoord = getStringObjVar(self, "teleport.x");
            String ycoord = getStringObjVar(self, "teleport.y");
            String zcoord = getStringObjVar(self, "teleport.z");
            String sound = getStringObjVar(self, "teleport.sound_template");
            float x = utils.stringToFloat(xcoord);
            float y = utils.stringToFloat(ycoord);
            float z = utils.stringToFloat(zcoord);
            String factionName = factions.getFaction(player);
            
            if (factionName.equals(faction))
            {
                playMusic(player, sound);
                warpPlayer(player, planet, x, z, y, null, 0.0f, 0.0f, 0.0f);
                //warpPlayer(player, planet, 100.0f, 10.0f, 0.0f, null, 0.0f, 0.0f, 0.0f);
            }
            else if (!factionName.equals(faction))
            {
                sendSystemMessageTestingOnly(player, "You do not meet the requirements to participate in this event.");
            }
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            String planet = getStringObjVar(self, "teleport.scene");
            String faction = getStringObjVar(self, "teleport.faction");
            String xcoord = getStringObjVar(self, "teleport.x");
            String ycoord = getStringObjVar(self, "teleport.y");
            String zcoord = getStringObjVar(self, "teleport.z");
            sendSystemMessageTestingOnly(player, "How to: Use radial submenus to set Scene and XZY Coordinates and the Skill to check in order to teleport.");
            setName(self, "[" + planet + "] X: " + xcoord + " | Z: "  + zcoord + " | Y:" + ycoord + " [" + faction + "]");
            sendSystemMessageTestingOnly(player, "Debug name set. Use /setname to customize it.");
            //note, the coords go in order of the game coordinates: X left right , Z height, Y up down
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            String message1 = "";
            String buffer = "Enter a Planet name for this teleporter to teleport to:";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleSceneRequest", message1);
        }
        if (item == menu_info_types.SERVER_MENU6)
        {
            String message2 = "";
            String buffer = "Enter a faction to check on the player:";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleFactionCheckRequest", message2);
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            String message3 = "";
            String buffer = "Enter the X coordinate";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleXRequest", message3);
        }
        if (item == menu_info_types.SERVER_MENU5)
        {
            String message4 = "";
            String buffer = "Enter the Z coordinate";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleZRequest", message4);
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            String message5 = "";
            String buffer = "Enter the Y coordinate";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleYRequest", message5);
        }
        if (item == menu_info_types.SERVER_MENU7)
        {
            String message6 = "";
            String buffer = "Enter an about message that players can see.";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleMSGRequest", message6);
        }
        if (item == menu_info_types.SERVER_MENU8)
        {
            String title = "Event Information";
            String buffer = getStringObjVar(self, "teleport.msg");
            sui.msgbox(self, player, buffer, title);
        }
        if (item == menu_info_types.SERVER_MENU9)
        {
            String message7 = "";
            String buffer = "Enter a sound that the player(s) will hear when they teleport.";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleSoundRequest", message7);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleSceneRequest(obj_id self, dictionary params)
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
        String message1 = sui.getInputBoxText(params);
        if (message1 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "teleport.scene", message1);
        return SCRIPT_CONTINUE;
    }
    public int handleSoundRequest(obj_id self, dictionary params)
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
        String message7 = sui.getInputBoxText(params);
        if (message7 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "teleport.sound_template", message7);
        //not sure if this works or not. kek
        return SCRIPT_CONTINUE;
    }
    public int handleMSGRequest(obj_id self, dictionary params)
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
        String message6 = sui.getInputBoxText(params);
        if (message6 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "teleport.msg", message6);
        return SCRIPT_CONTINUE;
    }
    public int handleFactionCheckRequest(obj_id self, dictionary params)
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
        String message2 = sui.getInputBoxText(params);
        if (message2 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        if (!message2.equals ("Imperial") || !message2.equals ("Rebel") || !message2.equals ("Neutral"))
        {
            sendSystemMessageTestingOnly(player, "Invalid Faction, All factions need an uppercase character. Also: If you are trying to do neutral, just put neutral.");
            setObjVar(player, "teleport.error", 1);
        }
        setObjVar(self, "teleport.faction", message2);
        return SCRIPT_CONTINUE;
    }
    public int handleXRequest(obj_id self, dictionary params)
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
        String message3 = sui.getInputBoxText(params);
        if (message3 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "teleport.x", message3);
        return SCRIPT_CONTINUE;
    }
    public int handleYRequest(obj_id self, dictionary params)
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
        String message4 = sui.getInputBoxText(params);
        if (message4 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "teleport.y", message4);
        return SCRIPT_CONTINUE;
    }
    public int handleZRequest(obj_id self, dictionary params)
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
        String message5 = sui.getInputBoxText(params);
        if (message5 == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "teleport.z", message5);
        return SCRIPT_CONTINUE;
    }
}