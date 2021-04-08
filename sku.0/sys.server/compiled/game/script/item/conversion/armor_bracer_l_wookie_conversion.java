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

public class armor_bracer_l_wookie_conversion extends script.item.conversion.armor_base_conversion
{
    public armor_bracer_l_wookie_conversion()
    {
    }
    public static final String[] ARMOR_SET_ASSAULT = 
    {
        "kashyyykian_hunting/armor_kashyyykian_hunting_bracer_l.iff"
    };
    public static final String[] ARMOR_SET_BATTLE = 
    {
        "kashyyykian_black_mtn/armor_kashyyykian_black_mtn_bracer_l.iff"
    };
    public static final String[] ARMOR_SET_RECON = 
    {
        "kashyyykian_ceremonial/armor_kashyyykian_ceremonial_bracer_l.iff"
    };
    public static final String[] ASSAULT_TYPE = 
    {
        "Kashyykian Hunting"
    };
    public static final String[] BATTLE_TYPE = 
    {
        "Kashyykian Black Mountain"
    };
    public static final String[] RECON_TYPE = 
    {
        "Kashyykian Ceremonial"
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
