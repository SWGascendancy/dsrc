package script.systems.crafting.armor;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_armor_customization_kit extends script.systems.crafting.armor.crafting_customization_base
{
    public crafting_armor_customization_kit()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_artisan_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "general_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "general_experimentation"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 1)
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
