package script.systems.crafting.chemistry.enzyme;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_struct_enzyme_units extends script.systems.crafting.chemistry.enzyme.crafting_base_enzyme_units
{
    public crafting_struct_enzyme_units()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_architect_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "structure_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "structure_experimentation"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("enzyme_purity", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 4)
        }),
        new resource_weight("enzyme_mutagen", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4),
            new resource_weight.weight(craftinglib.RESOURCE_SHOCK_RESIST, 2)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("enzyme_purity", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_TOUGHNESS, 4)
        }),
        new resource_weight("enzyme_mutagen", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 4),
            new resource_weight.weight(craftinglib.RESOURCE_SHOCK_RESIST, 2)
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
