package script.item.tool;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.hue;
import script.library.metrics; // finch might remove
import script.library.sui;
import script.library.static_item;
import script.library.utils;

public class crafted_armor_customization_kit extends script.base_script
{
    public crafted_armor_customization_kit()
    {
    }
    public static final String VAR_PREFIX = "armor_colorize";
    public static final String PID_NAME = VAR_PREFIX + ".pid";
    public static final String ARMOR_OBJ_LIST = VAR_PREFIX + ".armor_obj_list";
    public static final String ARMOR_NAME_LIST = VAR_PREFIX + ".armor_name_list";
    public static final String PLAYER_ID = VAR_PREFIX + ".player_oid";
    public static final String TOOL_ID = VAR_PREFIX + ".tool_oid";
    public static final String CUSTOMER_SVC_CATEGORY = "crafted_armor_color_kit";
    public static final string_id NO_ARMOR = new string_id("tool/customizer", "no_armor");
    public static final String TITLE = "@tool/customizer:armor_customize_title";
    public static final String PROMPT = "@tool/customizer:armor_customize_prompt";
    public static final int OBJECT_COLOR_MAX = 4;
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (!isValidId(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_CONTINUE;
        }
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null)
        {
            mid.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (!isValidId(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!utils.isNestedWithin(self, player))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            beginArmorColorization(self, player);
        }
        return SCRIPT_CONTINUE;
    }
    public boolean beginArmorColorization(obj_id self, obj_id player)
    {
        if (!isValidId(self) || !exists(self))
        {
            return false;
        }
        else if (!isValidId(player) || !exists(player))
        {
            return false;
        }
        closeOldWindow(player);
        Vector items = new Vector();
        obj_id[] invItems = getInventoryAndEquipment(player);
        if (invItems == null || invItems.length < 0)
        {
            return false;
        }
        if (invItems != null && invItems.length > 0)
        {
            for (int i = 0; i < invItems.length; i++)
            {
                if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/"))
                {
                    if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/assault_trooper"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/rebel_"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/scout_"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/stormtrooper"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/marine"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/clone_trooper/armor_clone_trooper_imperial"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/clone_trooper/armor_clone_trooper_rebel"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_crafted_belt"))
                    {
                        continue;
                    }
                    else if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/bounty_hunter/armor_bounty_hunter_belt"))
                    {
                        continue;
                    }
                    items.addElement(invItems[i]);
                }
            }
        }
        if (items.isEmpty())
        {
            sendSystemMessage(player, NO_ARMOR);
            return false;
        }
        Vector armor = new Vector();
        Vector armorNames = new Vector();
        if (!items.isEmpty())
        {
            for (int i = 0; i < items.size(); i++)
            {
                obj_id piece = (obj_id)items.get(i);
                if (!isCrafted(piece))
                {
                    continue;
                }
                else if (static_item.isStaticItem(piece))
                {
                    continue;
                }
                armor.addElement(piece);
                String name = getName(piece);
                armorNames.addElement(name);
            }
        }
        int armorListSize = armor.size();
        int nameSize = armorNames.size();
        if (!armor.isEmpty() && !armorNames.isEmpty() && armorListSize == nameSize)
        {
            utils.setScriptVar(player, ARMOR_OBJ_LIST, armor);
            int pid = sui.listbox(self, player, PROMPT, sui.OK_CANCEL, TITLE, armorNames, "handleArmorSelection", true, false);
            dictionary params = new dictionary();
            setSUIAssociatedLocation(pid, self);
            setSUIMaxRangeToObject(pid, 8);
            params.put("callingPid", pid);
            sui.setPid(player, pid, PID_NAME);
            if (pid < 0)
            {
                removePlayerVars(player);
                return false;
            }
            return true;
        }
        return false;
    }
    public int handleArmorSelection(obj_id self, dictionary params)
    {
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        int idx = sui.getListboxSelectedRow(params);
        if (idx < 0)
        {
            return SCRIPT_CONTINUE;
        }
        obj_id player = sui.getPlayerId(params);
        if (!isIdValid(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        utils.setScriptVar(self, PLAYER_ID, player);
        utils.setScriptVar(player, TOOL_ID, self);
        obj_id[] armor = utils.getObjIdArrayScriptVar(player, ARMOR_OBJ_LIST);
        if (armor == null || armor.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        if (idx >= armor.length)
        {
            return SCRIPT_CONTINUE;
        }
        ranged_int_custom_var[] palColors = hue.getPalcolorVars(armor[idx]);
        if ((palColors == null) || (palColors.length == 0))
        {
            return SCRIPT_CONTINUE;
        }
        int palColorsLength = palColors.length;
        String[] indexName = new String[OBJECT_COLOR_MAX];
        int loop = OBJECT_COLOR_MAX;
        if (palColorsLength < OBJECT_COLOR_MAX)
        {
            for (int i = 0; i < OBJECT_COLOR_MAX; i++)
            {
                indexName[i] = "";
            }
            loop = palColorsLength;
        }
        for (int i = 0; i < loop; i++)
        {
            ranged_int_custom_var ri = palColors[i];
            if (ri != null)
            {
                String customizationVar = ri.getVarName();
                if (customizationVar.startsWith("/"))
                {
                    customizationVar = customizationVar.substring(1);
                }
                indexName[i] = customizationVar;
            }
        }
        if (indexName[0].equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        openCustomizationWindow(player, armor[idx], indexName[0], -1, -1, indexName[1], -1, -1, indexName[2], -1, -1, indexName[3], -1, -1);
        return SCRIPT_CONTINUE;
    }
    public int decrementTool(obj_id self, dictionary params)
    {
        obj_id player = utils.getObjIdScriptVar(self, PLAYER_ID);
        if (!isValidId(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        static_item.decrementStaticItem(self);
        removePlayerVars(player);
        return SCRIPT_CONTINUE;
    }
    public int cancelTool(obj_id self, dictionary params)
    {
        obj_id player = utils.getObjIdScriptVar(self, PLAYER_ID);
        if (!isValidId(player) || !exists(player))
        {
            return SCRIPT_CONTINUE;
        }
        removePlayerVars(player);
        return SCRIPT_CONTINUE;
    }
    public void closeOldWindow(obj_id player)
    {
        int pid = sui.getPid(player, PID_NAME);
        if (pid > -1)
        {
            forceCloseSUIPage(pid);
            sui.removePid(player, PID_NAME);
        }
    }
    public void removePlayerVars(obj_id player)
    {
        obj_id self = getSelf();
        if (!isValidId(self) || !exists(self) || !isValidId(player) || !exists(player))
        {
            return;
        }
        utils.removeScriptVarTree(player, VAR_PREFIX);
        utils.removeScriptVarTree(self, VAR_PREFIX);
        utils.removeObjVar(player, VAR_PREFIX);
    }
}
