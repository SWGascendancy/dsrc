package script.systems.rls;

import script.library.badge;
import script.library.collection;
import script.library.loot;
import script.library.static_item;
import script.library.utils;
import script.menu_info;
import script.menu_info_types;
import script.obj_id;
import script.string_id;

public class RareLootSystem extends script.base_script {
    public static final String RARITY_OBJVAR = "rls.rarity";

    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi) {
        if (utils.getContainingPlayer(self) == player) {
            int mnu2 = mi.addRootMenu(menu_info_types.ITEM_USE, new string_id("npe", "crate_use"));
        }
        return SCRIPT_CONTINUE;
    }

    public int OnObjectMenuSelect(obj_id self, obj_id player, int item) {
        sendDirtyObjectMenuNotification(self);
        if (item == menu_info_types.ITEM_USE) {
            String template = getTemplateName(self);
            int rarity = 0;
            for (int i = 1; i < 7; i++) {
                if (template.equals("object/tangible/item/rare_loot_chest_" + i + ".iff")) {
                    rarity = i;
                    break;
                }
            }
            if (rarity >= 1 && rarity <= 3)
            {
                obj_id[] items = new obj_id[rand(2, 3)];
                for (int i = 0; i < items.length; i++)
                {
                    items[i] = loot.makeRlsItem(utils.getInventoryContainer(player), "rls/" + loot.CHEST_TYPES[rarity - 1] + "_loot");
                }
                showLootBox(player, items);
                destroyObject(self);
                handleRareLootCollection(player, rarity);
                LOG("rare_loot", "Player " + getName(player) + " (" + player + ") opened RLS chest with rarity of " + loot.CHEST_TYPES[rarity - 1]);
            }
            if (rarity >= 4 && rarity <= 6)
            {
                obj_id[] items = new obj_id[1];
                for (int i = 0; i < items.length; i++)
                {
                    items[i] = loot.makeRlsItem(utils.getInventoryContainer(player), "rls/" + loot.CHEST_TYPES[rarity - 1] + "_loot");
                }
                showLootBox(player, items);
                destroyObject(self);
                handleRareLootCollection(player, rarity);
                LOG("rare_loot", "Player " + getName(player) + " (" + player + ") opened RLS chest with rarity of " + loot.CHEST_TYPES[rarity - 1]);
            }
        }
        return SCRIPT_CONTINUE;
    }

    private void handleRareLootCollection(obj_id player, int lootType) {
        if (getCollectionSlotValue(player, "rare_loot_opened_one_" + lootType) == 0) {
            modifyCollectionSlotValue(player, "rare_loot_opened_one_" + lootType, 1);
        }

        if (!badge.hasBadge(player, "bdg_rare_loot")) {
            for (int i = 1; i < 5; i++) {
                if (!hasCompletedCollection(player, "rare_loot_opened_one_" + i)) {
                    break;
                } else if (i == 4) {
                    badge.grantBadge(player, "bdg_rare_loot");
                }
            }
        }

        long numberOfChests = getCollectionSlotValue(player, "rare_loot_opened_five_" + lootType);
        modifyCollectionSlotValue(player, "rare_loot_opened_five_" + lootType, ++numberOfChests);

        if (hasCompletedCollection(player, "rare_loot_opened_five_" + lootType)) {
            showLootBox(player, new obj_id[]{ static_item.createNewItemFunction(loot.CHEST_BASE + lootType, player) });
            collection.removeCompletedCollection(player, "rare_loot_opened_five_" + lootType);
        }
    }
}
