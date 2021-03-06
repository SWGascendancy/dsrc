package script.theme_park.dungeon.corvette;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.permissions;

public class disk_loot extends script.base_script
{
    public disk_loot()
    {
    }
    public int OnAttach(obj_id self)
    {
        String newLoot = pickNewLoot();
        obj_id cargo = createObject(newLoot, self, "");
        return SCRIPT_CONTINUE;
    }
    public int makeMoreLoot(obj_id self, dictionary params)
    {
        removeObjVar(self, "gaveLoot");
        String newLoot = pickNewLoot();
        obj_id cargo = createObject(newLoot, self, "");
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        String classTemplate = getSkillTemplate(player);
        if (classTemplate == null || classTemplate.startsWith("entertainer") || classTemplate.startsWith("trader"))
        {
            sendSystemMessage(player, new string_id("dungeon/corvette", "no_trader_farming_allowed"));
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_OPEN)
        {
            setOwner(self, player);
            if (!hasObjVar(self, permissions.VAR_PERMISSION_BASE))
            {
                switch (getContainerType(self))
                {
                    case 0:
                    detachScript(self, "item.container.base.base_container");
                    break;
                    case 1:
                    utils.requestContainerOpen(player, self);
                    break;
                    default:
                    break;
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToLoseItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item)
    {
        setObjVar(self, "gaveLoot", 1);
        return SCRIPT_CONTINUE;
    }
    public String pickNewLoot()
    {
        String newLoot = "object/tangible/loot/dungeon/corellian_corvette/bootdisk.iff";
        return newLoot;
    }
}
