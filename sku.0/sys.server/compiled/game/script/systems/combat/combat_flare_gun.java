package script.systems.combat;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.sui;
import script.library.combat;

public class combat_flare_gun extends script.systems.combat.combat_base
{
    public combat_flare_gun()
    {
    }
    public int OnAttach(obj_id self)
    {
        setCount(self, 5);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (hasObjVar(self, "intUsed"))
        {
            return SCRIPT_CONTINUE;
        }
        menu_info_data mid = mi.getMenuItemByType(menu_info_types.EXAMINE);
        if (mid != null)
        {
            mid.setServerNotify(true);
        }
        mid = mi.getMenuItemByType(menu_info_types.ITEM_USE);
        if (mid != null)
        {
            mid.setServerNotify(true);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (item == menu_info_types.ITEM_USE)
        {
            if (hasObjVar(self, "intUsed"))
            {
                return SCRIPT_CONTINUE;
            }
            String strParams = self.toString();
            obj_id objTarget = getCombatTarget(player);
            queueCommand(player, (-1412312015), objTarget, strParams, COMMAND_PRIORITY_FRONT);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAddedToWorld(obj_id self)
    {
        removeObjVar(self, "intUsed");
        return SCRIPT_CONTINUE;
    }
    public int flareUsed(obj_id self, dictionary params)
    {
        int intUses = getCount(self);
        intUses = intUses - 1;
        if (intUses == 0)
        {
            setObjVar(self, "intUsed", 1);
            destroyObject(self);
        }
        else 
        {
            setCount(self, intUses);
        }
        return SCRIPT_CONTINUE;
    }
}
