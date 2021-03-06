package script.item.conversion;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.sui;

public class armor_gloves_conversion extends script.item.conversion.armor_base_conversion
{
    public armor_gloves_conversion()
    {
    }
    public static final String[] ARMOR_SET_ASSAULT = 
    {
        "composite/armor_composite_gloves.iff",
        "chitin/armor_chitin_s01_gloves.iff"
    };
    public static final String[] ARMOR_SET_BATTLE = 
    {
        "bone/armor_bone_s01_gloves.iff",
        "padded/armor_padded_s01_gloves.iff"
    };
    public static final String[] ARMOR_SET_RECON = 
    {
        "tantel/armor_tantel_skreej_gloves.iff",
        "ubese/armor_ubese_gloves.iff"
    };
    public static final String[] ASSAULT_TYPE = 
    {
        "Composite",
        "Chitin"
    };
    public static final String[] BATTLE_TYPE = 
    {
        "Bone",
        "Padded"
    };
    public static final String[] RECON_TYPE = 
    {
        "Tantel",
        "Ubese"
    };
    public String[] getAssaultTemplates()
    {
        return ARMOR_SET_ASSAULT;
    }
    public String[] getBattleTemplates()
    {
        return ARMOR_SET_BATTLE;
    }
    public String[] getReconTemplates()
    {
        return ARMOR_SET_RECON;
    }
    public String[] getAssaultTypes()
    {
        return ASSAULT_TYPE;
    }
    public String[] getBattleTypes()
    {
        return BATTLE_TYPE;
    }
    public String[] getReconTypes()
    {
        return RECON_TYPE;
    }
}
