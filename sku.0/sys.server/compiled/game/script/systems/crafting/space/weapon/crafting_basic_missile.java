package script.systems.crafting.space.weapon;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_basic_missile extends script.systems.crafting.space.weapon.crafting_base_missile
{
    public crafting_basic_missile()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_shipwright_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "weapon_systems"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "weapon_systems_experimentation"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("fltMinDamage", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltMaxDamage", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltRefireRate", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltShieldEffectiveness", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltArmorEffectiveness", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltMaxAmmo", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("fltMinDamage", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltMaxDamage", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltRefireRate", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltShieldEffectiveness", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltArmorEffectiveness", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
        }),
        new resource_weight("fltMaxAmmo", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_POTENTIAL_ENERGY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1)
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
