package script.item.tool;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.hue;
import script.library.metrics;
import script.library.static_item;
import script.library.sui;
import script.library.utils;

public class crafted_armor_recolor_kit extends script.base_script
{
    public crafted_armor_recolor_kit()
    {
    }
    public static final String PROP_SELECTEDINDEX = "SelectedIndex";
    public static final String PROP_TARGETRANGEMAX = "TargetRangeMax";
    public static final String PROP_TARGETVAR = "TargetVariable";
    public static final String PROP_TARGETID = "TargetNetworkId";
    public static final String PROP_TEXT = "Text";
    public static final String DEFAULT_TITLE = "Jaskell's Color Picker";
    public static final String SUI_COLORPICKER = "Script.ColorPicker";
    public static final String COLORPICKER_COLORPICKER = "ColorPicker";
    public static final String COLORPICKER_PAGE_CAPTION = "bg.caption";
    public static final String COLORPICKER_TITLE = COLORPICKER_PAGE_CAPTION + ".lblTitle";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final String VAR_PREFIX = "armor_colorize";
    public static final String PID_NAME = VAR_PREFIX + ".pid";
    public static final String COLOR_INDEX_LIST = VAR_PREFIX + ".color_index_list";
    public static final String COLOR_INDEX_NUMBER = VAR_PREFIX + ".color_index_number";
    public static final String COLOR_OBJECT = VAR_PREFIX + ".color_object";
    public static final String PREVIOUS_BUTTON_VISIBILITIY = VAR_PREFIX + ".previous_button";
    public static final String NEXT_BUTTON_VISIBILITIY = VAR_PREFIX + ".next_button";
    public int OnAttach(obj_id self)
    {
        setCount(self, 10);
        return SCRIPT_CONTINUE;
    }
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
    public void beginArmorColorization(obj_id self, obj_id player)
    {
        if (!isValidId(self) || !exists(self))
        {
            return;
        }
        else if (!isValidId(player) || !exists(player))
        {
            return;
        }
        Vector wornItems = new Vector();
        Vector items = new Vector();
        obj_id[] equippedItems = metrics.getWornItems(player);
        if (equippedItems != null && equippedItems.length > 0)
        {
            for (int i = 0; i < equippedItems.length; i++)
            {
                if ((getTemplateName(equippedItems[i])).startsWith("object/tangible/wearables/armor/"))
                {
                    wornItems.addElement(equippedItems[i]);
                }
            }
        }
        obj_id[] invItems = utils.getContents(utils.getInventoryContainer(player), true);
        if (invItems != null && invItems.length > 0)
        {
            for (int i = 0; i < invItems.length; i++)
            {
                if ((getTemplateName(invItems[i])).startsWith("object/tangible/wearables/armor/"))
                {
                    items.addElement(invItems[i]);
                }
            }
        }
        if (wornItems.isEmpty() && items.isEmpty())
        {
            sendSystemMessage(player, new string_id("tool/customizer", "cadk_no_crusader_armor"));
            return;
        }
        String title = "@tool/customizer:cadk_sui_title";
        String prompt = "@tool/customizer:cadk_prompt1";
        Vector armor = new Vector();
        Vector armorNames = new Vector();
        if (!wornItems.isEmpty())
        {
            for (int i = 0; i < wornItems.size(); i++)
            {
                obj_id piece = (obj_id)wornItems.get(i);
                armor.addElement(piece);
                String name = getName(piece) + "  ( currently worn )";
                armorNames.addElement(name);
            }
        }
        if (!items.isEmpty())
        {
            for (int i = 0; i < items.size(); i++)
            {
                obj_id piece = (obj_id)items.get(i);
                armor.addElement(piece);
                String name = getName(piece);
                armorNames.addElement(name);
            }
        }
        int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, armorNames, "handleArmorSelection", true, false);
        if (pid < 0)
        {
            cleanup(self, player);
        }
        else 
        {
            utils.setScriptVar(self, "cadk_armor", armor);
        }
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
        if (!isIdValid(player))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id[] armor = utils.getObjIdArrayScriptVar(self, "cadk_armor");
        if (armor == null || armor.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        if (idx >= armor.length)
        {
            return SCRIPT_CONTINUE;
        }
        ranged_int_custom_var[] palColors = hue.getPalcolorVars(armor[idx]);
        if (palColors == null || palColors.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        Vector indexName = new Vector();
        indexName.setSize(0);
        for (int i = 0; i < palColors.length; i++)
        {
            ranged_int_custom_var ri = palColors[i];
            String customizationVar = ri.getVarName();
            if (customizationVar.startsWith("/"))
            {
                customizationVar = customizationVar.substring(1);
            }
            utils.addElement(indexName, customizationVar);
        }
                if (indexName == null || indexName.size() < 0)
        {
            return SCRIPT_CONTINUE;
        }
        utils.setScriptVar(player, COLOR_INDEX_LIST, indexName);
        utils.setScriptVar(player, COLOR_INDEX_NUMBER, 0);
        utils.setScriptVar(player, COLOR_OBJECT, armor[idx]);
        if (indexName.size() > 1)
        {
            utils.setScriptVar(player, NEXT_BUTTON_VISIBILITIY, TRUE);
        }
        utils.setScriptVar(player, PREVIOUS_BUTTON_VISIBILITIY, FALSE);
        createColorSui(self, player);
        openCustomizationWindow(player, armor[idx], ((String)indexName.get(0)), -1, -1, ((String)indexName.get(1)), -1, -1, ((String)indexName.get(2)), -1, -1, ((String)indexName.get(3)), -1, -1);
        return SCRIPT_CONTINUE;
    }
    public boolean createColorSui(obj_id self, obj_id player)
    {
        if (!isValidId(player) || !exists(player))
        {
            return false;
        }
        closeOldWindow(player);
        obj_id armorObj = utils.getObjIdScriptVar(player, COLOR_OBJECT);
        if (!isValidId(armorObj) || !exists(armorObj))
        {
            return false;
        }
        String[] indexName = utils.getStringArrayScriptVar(player, COLOR_INDEX_LIST);
        if (indexName == null || indexName.length < 0)
        {
            return false;
        }
        int indexNumber = utils.getIntScriptVar(player, COLOR_INDEX_NUMBER);
        if (indexNumber < 0)
        {
            return false;
        }
        int pid = createSUIPage("/Script.colorPicker", self, player);
        if (pid < 0)
        {
            return false;
        }
        dictionary params = new dictionary();
        setSUIAssociatedLocation(pid, armorObj);
        setSUIMaxRangeToObject(pid, 8);
        params.put("callingPid", pid);
        sui.setPid(player, pid, PID_NAME);
        setSUIProperty(pid, "ViewerPage.viewer", "SetObject", armorObj.toString());
        setSUIProperty(pid, COLORPICKER_COLORPICKER, PROP_TARGETID, armorObj.toString());
        setSUIProperty(pid, COLORPICKER_COLORPICKER, PROP_TARGETVAR, indexName[indexNumber]);
        setSUIProperty(pid, COLORPICKER_COLORPICKER, PROP_TARGETRANGEMAX, "500");
        setSUIProperty(pid, COLORPICKER_TITLE, PROP_TEXT, DEFAULT_TITLE);
        subscribeToSUIProperty(pid, COLORPICKER_COLORPICKER, PROP_SELECTEDINDEX);
        setSUIProperty(pid, "previous_color", "Visible", utils.getStringScriptVar(player, PREVIOUS_BUTTON_VISIBILITIY));
        setSUIProperty(pid, "next_color", "Visible", utils.getStringScriptVar(player, NEXT_BUTTON_VISIBILITIY));
        subscribeToSUIPropertyForEvent(pid, sui_event_type.SET_onButton, "next_color", "next_color", "LocalText");
        subscribeToSUIEvent(pid, sui_event_type.SET_onButton, "next_color", "handlePaletteUpdate");
        subscribeToSUIPropertyForEvent(pid, sui_event_type.SET_onButton, "previous_color", "previous_color", "LocalText");
        subscribeToSUIEvent(pid, sui_event_type.SET_onButton, "previous_color", "handlePaletteUpdate");
        showSUIPage(pid);
        flushSUIPage(pid);
        return true;
    }
    public void cleanup(obj_id self, obj_id player)
    {
        utils.removeScriptVar(self, "cadk_armor");
        utils.removeScriptVar(self, "cadk_piece");
    }
    public int handleCommit(obj_id self, dictionary params)
    {
        return SCRIPT_CONTINUE;
    }
    public int handlePaletteUpdate(obj_id self, dictionary params)
    {
        obj_id player = sui.getPlayerId(params);
        if (!isValidId(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (!sui.hasPid(player, PID_NAME))
        {
            return SCRIPT_CONTINUE;
        }
        int currentPid = sui.getPid(player, PID_NAME);
        String[] indexName = utils.getStringArrayScriptVar(player, COLOR_INDEX_LIST);
        if (indexName == null || indexName.length < 0)
        {
            return SCRIPT_CONTINUE;
        }
        int indexNumber = utils.getIntScriptVar(player, COLOR_INDEX_NUMBER);
        if (indexNumber < 0)
        {
            return SCRIPT_CONTINUE;
        }
        if (params.getString("previous_color.LocalText") != null && indexNumber > 0)
        {
            indexNumber--;
            utils.setScriptVar(player, COLOR_INDEX_NUMBER, indexNumber);
            if (indexNumber == 0)
            {
                utils.setScriptVar(player, PREVIOUS_BUTTON_VISIBILITIY, FALSE);
            }
            utils.setScriptVar(player, NEXT_BUTTON_VISIBILITIY, TRUE);
            createColorSui(self, player);
        }
        else if (params.getString("next_color.LocalText") != null && indexNumber < indexName.length)
        {
            indexNumber++;
            utils.setScriptVar(player, COLOR_INDEX_NUMBER, indexNumber);
            if (indexNumber == indexName.length - 1)
            {
                utils.setScriptVar(player, NEXT_BUTTON_VISIBILITIY, FALSE);
            }
            utils.setScriptVar(player, PREVIOUS_BUTTON_VISIBILITIY, TRUE);
            createColorSui(self, player);
        }
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
}
