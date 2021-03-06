package script.systems.crafting.structure.specific_item;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;

public class crafting_garden_exotic_dathomir extends script.systems.crafting.structure.crafting_base_structure
{
    public crafting_garden_exotic_dathomir()
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
    public static final String[] APPEARANCES = 
    {
        "object/tangible/deed/city_deed/shared_garden_dathomir_med_01_deed.iff",
        "object/tangible/deed/city_deed/shared_garden_dathomir_sml_01_deed.iff"
    };
    public static final String[] ALT_TEMPLATES = 
    {
        "object/building/player/city/garden_dathomir_med_01.iff",
        "object/building/player/city/garden_dathomir_sml_01.iff"
    };
    public static final resource_weight[] OBJ_ASSEMBLY_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
        })
    };
    public static final resource_weight[] OBJ_MAX_ATTRIBUTE_RESOURCES = 
    {
        new resource_weight("hitPoints", new resource_weight.weight[]
        {
            new resource_weight.weight(craftinglib.RESOURCE_DECAY_RESIST, 1)
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
    public String[] getAppearances(obj_id player, draft_schematic.slot[] slots)
    {
        return APPEARANCES;
    }
    public void modifyForCustomAppearance(obj_id self, obj_id newObject)
    {
        String customAppearance = getStringObjVar(self, "customAppearance");
        for (int i = 0; i < APPEARANCES.length; i++)
        {
            if (customAppearance.equals(APPEARANCES[i]))
            {
                setObjVar(newObject, "player_structure.deed.template", ALT_TEMPLATES[i]);
                return;
            }
        }
    }
}
