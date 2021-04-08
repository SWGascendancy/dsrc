package script.quest.force_sensitive;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;

public class fs_crafting1_calibrator extends script.base_script
{
    public fs_crafting1_calibrator()
    {
    }
    public static final string_id SID_MENU_USE_CALIBRATOR = new string_id("quest/force_sensitive/fs_crafting", "menu_use_calibrator");
    public static final string_id SID_MSG_CANT_USE_YET = new string_id("quest/force_sensitive/fs_crafting", "msg_cant_use_yet");
    public static final String QUEST_OBJVAR = "quest.fs_crafting1";
    public static final String CALIBRATOR_OBJVAR = QUEST_OBJVAR + ".calibrator";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        mi.addRootMenu(menu_info_types.ITEM_USE, SID_MENU_USE_CALIBRATOR);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            if (!hasScript(player, "quest.force_sensitive.fs_crafting1_player"))
            {
                sendSystemMessage(player, SID_MSG_CANT_USE_YET);
                return SCRIPT_CONTINUE;
            }
            utils.setScriptVar(player, CALIBRATOR_OBJVAR, self);
            dictionary d = new dictionary();
            messageTo(player, "handleUseCalibrator", d, 1.0f, false);
        }
        return SCRIPT_CONTINUE;
    }
}
