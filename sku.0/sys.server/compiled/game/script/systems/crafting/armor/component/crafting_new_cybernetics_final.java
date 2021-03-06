package script.systems.crafting.armor.component;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_new_cybernetics_final extends script.systems.crafting.armor.crafting_new_cybernetics_final
{
    public crafting_new_cybernetics_final()
    {
    }
    public static final String VERSION = "v0.00.00";
    public static final String[] REQUIRED_SKILLS = 
    {
        "crafting_cybernetic_novice"
    };
    public static final String[] ASSEMBLY_SKILL_MODS = 
    {
        "cybernetic_assembly"
    };
    public static final String[] EXPERIMENT_SKILL_MODS = 
    {
        "cybernetic_experimentation"
    };
    public static final String[] CUSTOMIZATION_SKILL_MODS = 
    {
        "cybernetic_customization"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("condition", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1),
            new resource_weight.weight(craftinglib.RESOURCE_SHOCK_RESIST, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("condition", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_QUALITY, 1),
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1),
            new resource_weight.weight(craftinglib.RESOURCE_SHOCK_RESIST, 1)
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
    public String[] getCustomizationSkillMods()
    {
        return CUSTOMIZATION_SKILL_MODS;
    }
    public resource_weight[] getResourceMaxResourceWeights()
    {
        return OBJ_MAX_ATTRIBUTE_RESOURCES;
    }
    public draft_schematic.custom[] getCustomizations(obj_id player, draft_schematic.custom[] customizations)
    {
        if (customizations == null || customizations.length == 0)
        {
            return null;
        }
        String[] skills = getCustomizationSkillMods();
        float playerCustomizationMod = 0;
        if (skills != null)
        {
            int[] mods = getEnhancedSkillStatisticModifiers(player, skills);
            for (int i = 0; i < mods.length; ++i)
            {
                playerCustomizationMod += mods[i];
            }
        }
        if (playerCustomizationMod > 255)
        {
            playerCustomizationMod = 255;
        }
        int max_colors = 4;
        int threshold = 20;
        for (int i = 0; i < customizations.length; ++i)
        {
            if (i >= max_colors)
            {
                customizations[i] = null;
            }
            else if (playerCustomizationMod < threshold)
            {
                customizations[i] = null;
            }
            else if ((customizations[i].name).indexOf("index_color_0") >= 0 && customizations[i].maxValue == 1)
            {
                customizations[i] = null;
            }
            else 
            {
                int max_maxValue = customizations[i].maxValue;
                customizations[i].maxValue = (int)(playerCustomizationMod * ((customizations[i].maxValue + 1) / 255.0f));
                if (customizations[i].maxValue > max_maxValue)
                {
                    customizations[i].maxValue = max_maxValue;
                }
                if (customizations[i].maxValue < customizations[i].minValue)
                {
                    customizations[i].maxValue = customizations[i].minValue;
                }
                threshold += 20;
            }
        }
        return customizations;
    }
    public resource_weight[] getAssemblyResourceWeights()
    {
        return OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES;
    }
}
