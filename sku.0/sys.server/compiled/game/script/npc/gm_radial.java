package script.npc;

import script.*;

import script.library.utils;
import script.library.ai_lib;
import script.library.sui;
import script.library.colors;

public class gm_radial extends script.base_script
{
    public static final String STF = "city/city";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if(isGod(player))
        {    
            mi.getMenuItemByType(menu_info_types.SERVER_MENU1);
            int menu = mi.addRootMenu(menu_info_types.SERVER_MENU1, new string_id("gm_radial", "event_staff"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU2, new string_id("gm_radial", "make_invulnerable"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU3, new string_id("gm_radial", "make_vulnerable"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU4, new string_id("gm_radial", "destroy"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU5, new string_id("gm_radial", "namenpc"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU6, new string_id("gm_radial", "persist"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU7, new string_id("gm_radial", "attachscript"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU8, new string_id("gm_radial", "detachscript"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU9, new string_id("gm_radial", "setposture"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU10, new string_id("gm_radial", "setscale"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU15, new string_id("gm_radial", "animstitle"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU11, new string_id("gm_radial", "setinteresting"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU12, new string_id("gm_radial", "setspaceinteresting"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU13, new string_id("gm_radial", "clearallinteresting"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU14, new string_id("gm_radial", "freeze"));
            mi.addSubMenu(menu, menu_info_types.SERVER_MENU16, new string_id("gm_radial", "about"));
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.SERVER_MENU2)
        {
            attachScript(self, "npe.make_invulnerable");
        }
        if (item == menu_info_types.SERVER_MENU3)
        {
            detachScript(self, "npe.make_invulnerable");
            clearCondition(self, CONDITION_INVULNERABLE);
        }
        if (item == menu_info_types.SERVER_MENU4)
        {
            destroyObject(self);
        }
        if (item == menu_info_types.SERVER_MENU5)
        {
            String message = "";
            sui.filteredInputbox(self, player, "@gm_radial:namenpc", "@gm_radial:event_tool_base", "handleNameRequest", message);
            showFlyText(self, new string_id("gm_radial", "flytext1"), 10.0f, colors.ORANGERED);
        }
        if (item == menu_info_types.SERVER_MENU6)
        {
            persistObject(self);
        }
        if (item == menu_info_types.SERVER_MENU7)
        {
            String message = "";
            sui.filteredInputbox(self, player, "@gm_radial:scriptmenu", "@gm_radial:event_tool_base", "handleScriptAttachRequest", message);
        }
        if (item == menu_info_types.SERVER_MENU8)
        {
            String message = "";
            sui.filteredInputbox(self, player, "@gm_radial:scriptdetachmenu", "@gm_radial:event_tool_base", "handleScriptDetachRequest", message);
        }
        if (item == menu_info_types.SERVER_MENU9)
        {
            String message = "";
            sui.filteredInputbox(self, player, "@gm_radial:posturemenu", "@gm_radial:event_tool_base", "handlePostureRequest", message);
        }
        if (item == menu_info_types.SERVER_MENU15)
        {
            String message = "";
            sui.filteredInputbox(self, player, "@gm_radial:animsprompt", "@gm_radial:event_tool_base", "handleAnimsRequest", message);
        }
        if (item == menu_info_types.SERVER_MENU11)
        {
        setCondition(self, CONDITION_INTERESTING);
        }
        if (item == menu_info_types.SERVER_MENU12)
        {
        setCondition(self, CONDITION_SPACE_INTERESTING);
        }
        if (item == menu_info_types.SERVER_MENU13)
        {
        clearCondition(self, CONDITION_INTERESTING);
        clearCondition(self, CONDITION_SPACE_INTERESTING);
        }
        if (item == menu_info_types.SERVER_MENU10)
        {
            String message = "";
            sui.filteredInputbox(self, player, "@gm_radial:scalemenu", "@gm_radial:event_tool_base", "handleScaleRequest", message);
        }
        if (item == menu_info_types.SERVER_MENU14)
        {
            ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
            sendSystemMessageTestingOnly(player, "Frozen");
        }
        if (item == menu_info_types.SERVER_MENU16)
        {
        sui.msgbox(self, player, "@gm_radial:aboutmenu", sui.OK_ONLY, "@gm_radial:event_tool_base", "handleNothing");
        }
        return SCRIPT_CONTINUE;
        
    }
    public int handleNameRequest(obj_id self, dictionary params)
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
        String message = sui.getInputBoxText(params);
        if (message == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        setName(self, message);
        return SCRIPT_CONTINUE;
    }
    public int handleAnimsRequest(obj_id self, dictionary params)
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
        String message = sui.getInputBoxText(params);
        if (message == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        doAnimationAction(self, message);
        return SCRIPT_CONTINUE;
    }
    public int handleScriptAttachRequest(obj_id self, dictionary params)
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
        String message = sui.getInputBoxText(params);
        if (message == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        attachScript(self, message);
        return SCRIPT_CONTINUE;
    }
    public int handlePostureRequest(obj_id self, dictionary params)
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
        String message = sui.getInputBoxText(params);
        if (message == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        int posture = utils.stringToInt(message);
        setPosture(self, posture);
        return SCRIPT_CONTINUE;
    }
    public int handleScaleRequest(obj_id self, dictionary params)
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
        String message = sui.getInputBoxText(params);
        if (message == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        float scale = utils.stringToFloat(message);
        setScale(self, scale);
        return SCRIPT_CONTINUE;
    }
    public int handleScriptDetachRequest(obj_id self, dictionary params)
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
        String message = sui.getInputBoxText(params);
        if (message == null)
        {
            sendSystemMessage(self, new string_id(STF, "not_valid_message"));
            return SCRIPT_CONTINUE;
        }
        detachScript(self, message);
        return SCRIPT_CONTINUE;
    }
}