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

public class crafting_chef_food extends script.systems.crafting.food.crafted_items.crafting_food
{
    public crafting_chef_food()
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
}
