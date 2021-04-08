package script.item.medicine;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.buff;
import script.library.sui;
import script.library.utils;
import script.library.healing;

public class stimpack_crafted extends script.base_script
{
    public stimpack_crafted()
    {
    }
    public static final string_id SID_ITEM_LEVEL_TOO_LOW = new string_id("healing", "item_level_too_low");
    public static final string_id SID_STIMPACK_TOO_SOON = new string_id("healing", "channel_heal_stimpack_too_soon");
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs)
    {
        int idx = utils.getValidAttributeIndex(names);
        if (idx == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (hasObjVar(self, "healing.power"))
        {
            names[idx] = "healing_power";
            int value = getIntObjVar(self, "healing.power");
            attribs[idx] = "" + value;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        if (hasObjVar(self, "healing.combat_level_required"))
        {
            names[idx] = "healing_combat_level_required";
            int value = getIntObjVar(self, "healing.combat_level_required");
            attribs[idx] = "" + value;
            idx++;
            if (idx >= names.length)
            {
                return SCRIPT_CONTINUE;
            }
        }
        names[idx] = "count";
        int value = getCount(self);
        attribs[idx] = "" + value;
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (canManipulate(player, self, true, true, 15, true))
        {
            menu_info_data mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
            if (mid != null)
            {
                mid.setServerNotify(true);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (isDead(player) || isIncapacitated(player))
        {
            return SCRIPT_CONTINUE;
        }
        if (buff.hasBuff(player, "feign_death"))
        {
            return SCRIPT_CONTINUE;
        }
        if (buff.hasBuff(player, "recent_heal"))
        {
            sendSystemMessage(player, SID_STIMPACK_TOO_SOON);
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            int level = getLevel(player);
            int requiredLevel = getIntObjVar(self, "healing.combat_level_required");
            if (level < requiredLevel)
            {
                sendSystemMessage(player, SID_ITEM_LEVEL_TOO_LOW);
                return SCRIPT_OVERRIDE;
            }
            else 
            {
                boolean worked = healing.useChannelHealItem(player, self);
            }
        }
        return SCRIPT_CONTINUE;
    }
}
