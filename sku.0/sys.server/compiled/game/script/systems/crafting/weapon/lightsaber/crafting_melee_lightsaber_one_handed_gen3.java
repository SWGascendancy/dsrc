package script.systems.crafting.weapon.lightsaber;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_melee_lightsaber_one_handed_gen3 extends script.systems.crafting.weapon.crafting_base_lightsaber
{
    public crafting_melee_lightsaber_one_handed_gen3()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "class_forcesensitive_phase1_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "jedi_saber_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "jedi_saber_experimentation"
    };
    public static final String[] APPEARANCES = 
    {
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s1_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s2_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s3_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s4_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s5_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s6_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s7_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s8_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s9_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s10_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s11_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s12_gen3.iff",
        "object/weapon/melee/sword/crafted_saber/shared_sword_lightsaber_one_handed_s13_gen3.iff"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("minDamage", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1)
        }),
        new resource_weight("maxDamage", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 2),
            new resource_weight.weight(craftinglib.RESOURCE_CONDUCTIVITY, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;
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
    public String[] getAppearances(obj_id player, draft_schematic.slot[] slots)
    {
        return APPEARANCES;
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
