package script.space.special_loot;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.smuggler;
import script.library.static_item;
import script.library.utils;

public class space_rare_ticket_crate_1 extends script.base_script
{
    public space_rare_ticket_crate_1()
    {
    }
    public static final string_id SID_OPEN_CRATE = new string_id("spam", "open_crate");
    public static final String LOOT_LIST = "datatables/space_loot/rare/tier1.iff";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        mi.addRootMenu(menu_info_types.ITEM_USE, new string_id(SID_OPEN_CRATE));
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (!isIdValid(player) || !isIdValid(self))
        {
            return SCRIPT_CONTINUE;
        }
        if (item == menu_info_types.ITEM_USE)
        {
            obj_id inv = utils.getInventoryContainer(player);
            int freeSpace = getVolumeFree(inv);
            if (freeSpace < 3)
            {
                sendSystemMessage(player, "You must have room for at least 3 items in your inventory to open this container.", null);
                return SCRIPT_CONTINUE;
            }
            createLoot(self, player);
        }
        return SCRIPT_CONTINUE;
    }
    public void createLoot(obj_id self, obj_id player)
    {
        obj_id inventory = utils.getInventoryContainer(player);
        int itemCount = 3;
        for (int i = 0; i < itemCount; i++)
        {
            int lootRoll = rand(1, 100);
            if (lootRoll > 0 && lootRoll <= 75)
            {
                String regItem = generateRegularItem(player);
                if (regItem.length() > 0)
                {
                    createObject(regItem, inventory, "");
                }
            }
            if (lootRoll > 75)
            {
                String rareItem = generateRareItem(player, "rareItems");
                if (rareItem.length() > 0)
                {
                    static_item.createNewItemFunction(rareItem, inventory);
                }
            }
        }
        destroyObject(self);
    }
    public String generateRegularItem(obj_id player)
    {
        String[] compType = dataTableGetStringColumnNoDefaults(LOOT_LIST, "componentType");
        if (compType == null)
        {
            return null;
        }
        int realCompTypeLength = 0;
        for (int i = 0; i < compType.length; i++)
        {
            if (compType[i] != null && compType[i].length() > 0)
            {
                realCompTypeLength++;
            }
        }
        String component = compType[rand(0, realCompTypeLength - 1)];
        int level = 1;
        String col = "level" + level;
        String table = "datatables/item/vendor/space_rare_ticket_crate/" + component + ".iff";
        String[] compList = dataTableGetStringColumnNoDefaults(table, col);
        if (compList == null)
        {
            return null;
        }
        String item = compList[rand(0, (compList.length - 1))];
        return item;
    }
    public String generateRareItem(obj_id player, String type)
    {
        String[] compList = dataTableGetStringColumnNoDefaults(LOOT_LIST, type);
        if (compList == null)
        {
            return null;
        }
        String component = compList[rand(0, (compList.length - 1))];
        return component;
    }
}
