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

public class crafting_food_organic_water_chef extends script.systems.crafting.food.crafting_base_food_new
{
    public crafting_food_organic_water_chef()
    {
    }
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_chef_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "food_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "food_experimentation"
    };
    public String[] getRequiredSkills()
    {
        return REQUIRED_SKILLS;
    }
    public String[] getAssemblySkillMods()
    {
        return ASSEMBLY_SKILL_MODS;
    }
    public String[] getExperimentSkillMods()
    {
        return EXPERIMENT_SKILL_MODS;
    }
    public void fillStomach(obj_id prototype, int filling)
    {
        int[] stomach = 
        {
            filling,
            0,
            0
        };
        setObjVar(prototype, consumable.VAR_CONSUMABLE_STOMACH_VALUES, stomach);
    }
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("nutrition", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("flavor", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 1),
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
        }),
        new resource_weight("quantity", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
        }),
        new resource_weight("filling", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("nutrition", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("flavor", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_FLAVOR, 2),
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
        }),
        new resource_weight("quantity", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 3),
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
        }),
        new resource_weight("filling", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 3),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        })
    };
    public resource_weight[] getResourceMaxResourceWeights()
    {
        return OBJ_MAX_ATTRIBUTE_RESOURCES;
    }
    public resource_weight[] getAssemblyResourceWeights()
    {
        return OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;
    }
}
