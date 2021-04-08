package script.systems.crafting.armor;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.armor;
import script.library.utils;
import script.library.craftinglib;

public class crafting_base_psg extends script.systems.crafting.crafting_base
{
    public crafting_base_psg()
    {
    }
    public static final String VERSION = "v1.00.00";
    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes, dictionary craftingValuesDictionary)
    {
        for (int i = 0; i < itemAttributes.length; ++i)
        {
            if (itemAttributes[i] == null)
            {
                continue;
            }
        }
        super.calcAndSetPrototypeProperties(prototype, itemAttributes, craftingValuesDictionary);
    }
    public void calcAndSetPrototypeProperties(obj_id prototype, draft_schematic.attribute[] itemAttributes)
    {
        debugServerConsoleMsg(null, "Beginning assembly-phase prototype property setting");
        int armorLevel = getIntObjVar(prototype, armor.OBJVAR_ARMOR_BASE + "." + armor.OBJVAR_ARMOR_LEVEL);
        int psgRow = dataTableSearchColumnForInt(getStringCrc(armor.DATATABLE_PSG_ROW + armorLevel), armor.DATATABLE_TYPE_COL, armor.DATATABLE_ARMOR);
        if (psgRow < 0)
        {
            CustomerServiceLog("crafting", "WARNING: armor datatable " + armor.DATATABLE_ARMOR + " has missing psg row " + psgRow + ")");
            return;
        }
        for (int i = 0; i < itemAttributes.length; ++i)
        {
            if (itemAttributes[i] == null)
            {
                continue;
            }
            if (!calcAndSetPrototypeProperty(prototype, itemAttributes[i]))
            {
                if (itemAttributes[i].currentValue < itemAttributes[i].minValue)
                {
                    itemAttributes[i].currentValue = itemAttributes[i].minValue;
                }
                else if (itemAttributes[i].currentValue > itemAttributes[i].maxValue)
                {
                    itemAttributes[i].currentValue = itemAttributes[i].maxValue;
                }
                if ((itemAttributes[i].name).equals("special_protection"))
                {
                    float realValue = armor.getAbsoluteArmorAttribute(itemAttributes[i].currentValue, psgRow, armor.DATATABLE_MIN_SPEC_PROT_COL);
                    armor.setAbsoluteArmorSpecialProtection(prototype, armor.DATATABLE_PSG_LAYER, (int)(realValue + 0.5f));
                }
                else 
                {
                    setObjVar(prototype, armor.OBJVAR_ARMOR_BASE + "." + (itemAttributes[i].name).getAsciiId(), itemAttributes[i].currentValue);
                }
                if ((itemAttributes[i].name).equals(armor.OBJVAR_CONDITION))
                {
                    float hp = armor.getAbsoluteArmorAttribute(itemAttributes[i].currentValue, psgRow, armor.DATATABLE_MIN_CONDITION_COL);
                    if (hp != Float.MIN_VALUE)
                    {
                        if (hasObjVar(getSelf(), armor.OBJVAR_CONDITION_MULTIPLIER))
                        {
                            float multiplier = getFloatObjVar(getSelf(), armor.OBJVAR_CONDITION_MULTIPLIER);
                            if (multiplier > 0)
                            {
                                hp *= multiplier;
                            }
                            else 
                            {
                                CustomerServiceLog("crafting", "Armor schematic " + getDraftSchematic(getSelf()) + " has an invalid condition multiplier " + multiplier);
                            }
                        }
                        setMaxHitpoints(prototype, (int)hp);
                    }
                }
            }
        }
        armor.saveScaledDataToPrototype(getSelf(), prototype, itemAttributes, armor.OBJVAR_ARMOR_BASE);
    }
    public int OnManufactureObject(obj_id self, obj_id player, obj_id newObject, draft_schematic schematic, boolean isPrototype, boolean isRealObject)
    {
        super.OnManufactureObject(self, player, newObject, schematic, isPrototype, isRealObject); // finch need to validate why this was added
        messageTo(newObject, "handleInitializePsg", null, 0.5f, false);
        return SCRIPT_CONTINUE;
    }
}
