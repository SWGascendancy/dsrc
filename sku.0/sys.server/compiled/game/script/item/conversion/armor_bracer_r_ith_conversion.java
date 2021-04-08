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

public class armor_bracer_r_ith_conversion extends script.item.conversion.armor_base_conversion
{
    public armor_bracer_r_ith_conversion()
    {
    }
    public static final String[] ARMOR_SET_ASSAULT = 
    {
        "ithorian_sentinel/ith_armor_s03_bracer_r.iff"
    };
    public static final String[] ARMOR_SET_BATTLE = 
    {
        "ithorian_defender/ith_armor_s01_bracer_r.iff"
    };
    public static final String[] ARMOR_SET_RECON = 
    {
        "ithorian_guardian/ith_armor_s02_bracer_r.iff"
    };
    public static final String[] ASSAULT_TYPE = 
    {
        "Ithorian Sentinel"
    };
    public static final String[] BATTLE_TYPE = 
    {
        "Ithorian Defender"
    };
    public static final String[] RECON_TYPE = 
    {
        "Ithorian Guardian"
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
