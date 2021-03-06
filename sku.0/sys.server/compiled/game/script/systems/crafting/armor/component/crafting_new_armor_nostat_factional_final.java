package script.systems.crafting.armor.component;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.faction_perk;
import script.library.utils;
import script.library.craftinglib;

public class crafting_new_armor_nostat_factional_final extends script.systems.crafting.armor.crafting_new_armor_clothing_nostats
{
    public crafting_new_armor_nostat_factional_final()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_armorsmith_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "armor_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "armor_experimentation"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
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
    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes)
    {
        super.calcAndSetPrototypeProperties(prototype, itemAttributes);
    }
}
