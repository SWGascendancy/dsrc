package script.systems.crafting.droid.crafted_items;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_droid_wound_repair extends script.systems.crafting.droid.crafting_base_droid_repair
{
    public crafting_droid_wound_repair()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_droidengineer_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "droid_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "droid_experimentation"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("power", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_MALLEABILITY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 4),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
        }),
        new resource_weight("charges", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
        }),
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 3),
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("power", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_MALLEABILITY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 4),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
        }),
        new resource_weight("charges", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 2),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4)
        }),
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 3),
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 3)
        })
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
    public resource_weight[] getResourceMaxResourceWeights()
    {
        return OBJ_MAX_ATTRIBUTE_RESOURCES;
    }
    public resource_weight[] getAssemblyResourceWeights()
    {
        return OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;
    }
}
