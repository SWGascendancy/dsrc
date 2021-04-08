package script.event;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.trial;
import script.library.chat;
import script.library.regions;
import script.library.factions;
import script.library.sui;
import script.library.utils;
import script.library.gcw;

public class battlefield extends script.base_script
{
    public battlefield()
    {
    }
    public static final String STF = "city/city";
    public static final String REGION_NAME = "event_battle_field";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        int menu = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("event", "capture"));
        if(isGod(player))
        {
            int god_menu = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("event", "setup"));
            mi.addSubMenu(god_menu, menu_info_types.SERVER_MENU2, new string_id("event", "x"));
            mi.addSubMenu(god_menu, menu_info_types.SERVER_MENU3, new string_id("event", "z"));
            mi.addSubMenu(god_menu, menu_info_types.SERVER_MENU4, new string_id("event", "y"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.ITEM_USE)
        {
            int rebelPointValues = getIntObjVar(self, "battlefield.rebel");
            int imperialPointValues = getIntObjVar(self, "battlefield.imperial");
            if (rebelPointValues == 20)
            {
                obj_id[] rebelPlayers = getPlayerCreaturesInRange(getLocation(self), 128.0f);
                if (rebelPlayers == null || rebelPlayers.length == 0)
                {
                    return SCRIPT_CONTINUE;
                }
                for (int i = 0; i < rebelPlayers.length; i++)
                {
                    sendSystemMessageTestingOnly(rebelPlayers[i], "Battlefield Alert: Rebels have won this battlefield!");
                    if (factions.isRebel(rebelPlayers[i]))
                    {
                        factions.grantFactionBonus(rebelPlayers[i], "Rebel", 500);
                        setObjVar(self, "battlefield.rebel.victory", 1);
                        endBattlefield(self);
                    }
                }
            }
            if (imperialPointValues == 20)
            {
                obj_id[] imperialPlayers = getPlayerCreaturesInRange(getLocation(self), 128.0f);
                if (imperialPlayers == null || imperialPlayers.length == 0)
                {
                    return SCRIPT_CONTINUE;
                }
                for (int i = 0; i < imperialPlayers.length; i++)
                {
                    sendSystemMessageTestingOnly(imperialPlayers[i], "Battlefield Alert: Imperials won this battlefield!");
                    if (factions.isImperial(imperialPlayers[i]))
                    {
                        factions.grantFactionBonus(imperialPlayers[i], "Imperial", 500);
                        setObjVar(self, "battlefield.imperial.victory", 1);
                        endBattlefield(self);
                    }
                }
            }
            if (factions.isRebel(player))
            {
                if (hasObjVar(player, "battlefield.rebel.capture"))
                {
                    sendSystemMessageTestingOnly(player, "You have already gave a point!");
                    //TODO: add in timer
                }
                sendSystemMessageTestingOnly(player, "The Rebels have gained a battlefield point!");
                playMusic(player, "sound/off_exp_charge.snd");
                setObjVar(player, "battlefield.rebel.capture", 1);
                if (!hasObjVar(self, "battlefield.rebel"))
                {
                    setObjVar(self, "battlefield.rebel", 1);
                }
                else if (hasObjVar(self, "battlefield.rebel"))
                {
                    int baseRebelPointValue = getIntObjVar(self, "battlefield.rebel");
                    int rebelPointValue = baseRebelPointValue + 1;
                    setObjVar(self, "battlefield.rebel", rebelPointValue);
                }

            }
            else if (factions.isImperial(player))
            {
                if (hasObjVar(player, "battlefield.imperial.capture"))
                {
                    sendSystemMessageTestingOnly(player, "You have already gave a point!");
                    //TODO: add in timer
                }
                sendSystemMessageTestingOnly(player, "The Imperials have gained a battlefield point!");
                playMusic(player, "sound/off_exp_charge.snd");
                setObjVar(player, "battlefield.imperial.capture", 1);
                if (!hasObjVar(self, "battlefield.rebel"))
                {
                    setObjVar(self, "battlefield.rebel", 1);
                }
                else if (hasObjVar(self, "battlefield.rebel"))
                {
                    int baseImperialPointValue = getIntObjVar(self, "battlefield.imperial");
                    int imperialPointValue = baseImperialPointValue + 1;
                    setObjVar(self, "battlefield.imperial", imperialPointValue);
                    
                }
            }
        }
        if (item == menu_info_types.SERVER_MENU1)
        {
            String planet = getStringObjVar(self, "teleport.scene");
            String xcoord = getStringObjVar(self, "teleport.x");
            String ycoord = getStringObjVar(self, "teleport.y");
            String zcoord = getStringObjVar(self, "teleport.z");
            sendSystemMessageTestingOnly(player, "This is to set the location to teleport the players[i] that are not declared.");
        }
        if (item == menu_info_types.SERVER_MENU2)
        {
            String message3 = "";
            String buffer = "Enter the X coordinate";
            String title = "Event Teleporter";
            sui.filteredInputbox(self, player, buffer, title, "handleXRequest", message3);
        }
        if (item == menu_info_types.SERVER_MENU3)
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
    public int OnAttach(obj_id self)
    {
        chat.chat(self, "Zone is hot!");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        createCircleRegion(getLocation(self), getIntObjVar(self, "battlefields.radius"), REGION_NAME, regions.PVP_REGION_TYPE_NORMAL, regions.BUILD_TRUE, regions.MUNI_TRUE, regions.GEO_CITY, 0, 0, regions.SPAWN_FALSE, regions.MISSION_NONE, false, true);
        obj_id[] players = getPlayerCreaturesInRange(getLocation(self), 128.0f);
        if (players == null || players.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        for (int i = 0; i < players.length; i++)
        {
            sendSystemMessageTestingOnly(players[i], "[Event] Battlefield is now active. Prepare your weapons!");
            if(!factions.isDeclared(players[i]))
            {
                sendSystemMessageTestingOnly(players[i], "You are not allowed in the battlefield. You are being transported out.");
                String planet = getCurrentSceneName();
                String xcoord = getStringObjVar(self, "teleport.x");
                String ycoord = getStringObjVar(self, "teleport.y");
                String zcoord = getStringObjVar(self, "teleport.z");
                float x = utils.stringToFloat(xcoord);
                float y = utils.stringToFloat(ycoord);
                float z = utils.stringToFloat(zcoord);
                warpPlayer(players[i], planet, x, z, y, null, 0.0f, 0.0f, 0.0f);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int endBattlefield(obj_id self)
    {
        int rebelvictory = getIntObjVar(self, "battlefield.rebel.victory");
        int imperialvictory = getIntObjVar(self, "battlefield.imperial.victory");
        if (rebelvictory == 1)
        {
            CustomerServiceLog("Event", "Battlefield started by [" + getStringObjVar(self, "battlefield.creator") + " ] ended with a victory for the Rebels and the winning team has been awarded faction points.");
        }
        if (imperialvictory == 1)
        {
            CustomerServiceLog("Event", "Battlefield started by [" + getStringObjVar(self, "battlefield.creator") + " ] ended with a victory for the Imperials and the winning team has been awarded faction points.");
        }
        //obj_id[] mainBattlefield = getAllObjectsWithTemplate(getLocation(self), 256, "object/battlefield_marker/battlefield_marker_" + getIntObjVar(self, "battlefield.radius") + "m.iff");
        //destroyObject(mainBattlefield);
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self)
    {
        String scene = getCurrentSceneName();
        region pvpRegion = getRegion(scene, REGION_NAME);
        deleteRegion(pvpRegion);
        return SCRIPT_CONTINUE;
    }
}
