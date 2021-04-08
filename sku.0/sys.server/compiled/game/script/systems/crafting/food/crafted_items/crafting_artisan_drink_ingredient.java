package script.systems.crafting.food.crafted_items;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;
import script.library.consumable;

public class crafting_artisan_drink_ingredient extends script.systems.crafting.food.crafted_items.crafting_artisan_ingredient
{
    public crafting_artisan_drink_ingredient()
    {
    }
    public void fillStomach(obj_id prototype, int filling)
    {
        int[] stomach = 
        {
            0,
            filling,
            0
        };
        setObjVar(prototype, consumable.VAR_CONSUMABLE_STOMACH_VALUES, stomach);
    }
}
