package script.systems.combat;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.callable;
import script.library.static_item;
import script.library.utils;
import script.library.pet_lib;
import script.library.ai_lib;
import script.library.create;
import script.library.prose;

import script.library.factions;

public class combat_supply_drop_controller extends script.base_script
{
    public combat_supply_drop_controller()
    {
    }
    public static final string_id SID_ACQUIRE_HIRELING = new string_id("spam", "officer_pet_success");
    public static final string_id SID_TOO_MANY_HIRELINGS = new string_id("spam", "officer_too_many_pets");
    public static final String FOOD_TABLE = "datatables/loot/officer_supply_drop.iff";
    public static final String FOOD_LOW = "low";
    public static final String FOOD_MID = "mid";
    public static final String FOOD_HIGH = "high";
    public static final int FOOD_ITEMS = 2;
    public static final int GROUP_SIZE = 8;
    public static final int LEVEL_LOW_CAP = 45;
    public static final int LEVEL_MID_CAP = 75;
    public int startLandingSequence(obj_id self, dictionary params)
    {
        obj_id owner = params.getObjId("owner");
        int supplyId = params.getInt("supplyId");
        queueCommand(self, (-1114832209), self, "", COMMAND_PRIORITY_FRONT);
        setPosture(self, POSTURE_PRONE);
        dictionary d = new dictionary();
        d.put("owner", owner);
        d.put("supplyId", supplyId);
        if (supplyId < 13)
        {
            messageTo(self, "dropSupplies", d, 22.0f, false);
        }
        else 
        {
            messageTo(self, "dropReinforcements", d, 22.0f, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int dropReinforcements(obj_id self, dictionary params)
    {
        obj_id owner = params.getObjId("owner");
        int supplyId = params.getInt("supplyId");
        location loc = getLocation(self);
        String itemString = "";
        switch (supplyId)
        {
            case 13:
            itemString = "officer_reinforcement_1";
            break;
            case 14:
            itemString = "officer_reinforcement_2";
            break;
            case 15:
            itemString = "officer_reinforcement_3";
            break;
            case 16:
            itemString = "officer_reinforcement_4";
            break;
            case 17:
            itemString = "officer_reinforcement_5";
            break;
        }
        switch (factions.getFactionFlag(owner)) {
            case factions.FACTION_FLAG_IMPERIAL:
                itemString += "_imp";
                break;
            case factions.FACTION_FLAG_REBEL:
                itemString += "_reb";
                break;
        }
        if (supplyId > 12 && supplyId <= 17 && (itemString != null || !itemString.equals("")))
        {
            summonOfficerPet(owner, itemString, loc);
        }
        dictionary d = new dictionary();
        d.put("owner", owner);
        messageTo(self, "startTakeOffSequence", d, 2.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int dropSupplies(obj_id self, dictionary params)
    {
        obj_id owner = params.getObjId("owner");
        int supplyId = params.getInt("supplyId");
        int level = getLevel(owner);
        location loc = getLocation(self);
        obj_id crate = createObject("object/tangible/container/drum/supply_drop_crate.iff", loc);
        utils.setScriptVar(crate, "supply_drop.crateOwner", owner);
        attachScript(crate, "systems.combat.combat_supply_drop_crate");
        String itemString = "";
        if (supplyId == 0)
        {
            static_item.createNewItemFunction("item_stimpack_a_02_01", crate);
            static_item.createNewItemFunction("item_stimpack_a_02_01", crate);
            static_item.createNewItemFunction("weapon_npe_grenade_frag_02_01", crate);
            String foodList = "";
            if (level <= LEVEL_LOW_CAP)
            {
                foodList = FOOD_LOW;
            }
            else if (level <= LEVEL_MID_CAP)
            {
                foodList = FOOD_MID;
            }
            else 
            {
                foodList = FOOD_HIGH;
            }
            int tierStims = 1;
            if (level == 90)
            {
                tierStims = 6;
            }
            else if (level >= 76)
            {
                tierStims = 5;
            }
            else if (level >= 62)
            {
                tierStims = 4;
            }
            else if (level >= 48)
            {
                tierStims = 3;
            }
            else if (level >= 34)
            {
                tierStims = 2;
            }
            static_item.createNewItemFunction("item_off_temp_stimpack_02_0" + tierStims, crate);
            static_item.createNewItemFunction("item_off_temp_tactical_buff_02_0" + tierStims, crate);
            String[] foodItems = dataTableGetStringColumn(FOOD_TABLE, foodList);
            for (int i = 0; i < FOOD_ITEMS; i++)
            {
                int r = rand(0, (foodItems.length - 1));
                static_item.createNewItemFunction(foodItems[r], crate);
            }
        }
        else if (supplyId > 0 && supplyId <= 12 && (itemString != null || !itemString.equals("")))
        {
            boolean tacStimQual = hasSkill(owner, "expertise_of_tactical_sup_1");
            for (int i = 0; i < GROUP_SIZE; i++)
            {
                if (tacStimQual)
                {
                    static_item.createNewItemFunction("item_off_temp_tactical_buff_02_0" + supplyId, crate);
                }
                static_item.createNewItemFunction("item_off_temp_stimpack_02_0" + supplyId, crate);
            }
        }
        dictionary d = new dictionary();
        d.put("owner", owner);
        messageTo(self, "startTakeOffSequence", d, 2.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int startTakeOffSequence(obj_id self, dictionary params)
    {
        queueCommand(self, (-1465754503), self, "", COMMAND_PRIORITY_FRONT);
        setPosture(self, POSTURE_UPRIGHT);
        messageTo(self, "cleanUp", null, 20.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int cleanUp(obj_id self, dictionary params)
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
    public void summonOfficerPet(obj_id owner, String itemString, location spawnPoint)
    {
        if (!pet_lib.hasMaxPets(owner, pet_lib.PET_TYPE_NPC) && !pet_lib.hasMaxStoredPetsOfType(owner, pet_lib.PET_TYPE_NPC))
        {
            obj_id hireling = create.createCreature(itemString, spawnPoint, true);
            if (!isIdValid(hireling))
            {
                return;
            }
            setObjVar(hireling, "pet.petRestriction", 1);
            obj_id petControlDevice = pet_lib.makeControlDevice(owner, hireling);
            callable.setCallableCD(hireling, petControlDevice);
            pet_lib.makePet(hireling, owner);
            pet_lib.setupOfficerPetCommands(hireling);
            ai_lib.setDefaultCalmBehavior(hireling, ai_lib.BEHAVIOR_STOP);
            callable.setCallableLinks(owner, petControlDevice, hireling);
            dictionary params = new dictionary();
            params.put("pet", hireling);
            params.put("master", owner);
            params.put("controlDevice", petControlDevice);
            messageTo(hireling, "handleAddMaster", params, 0, false);
        }
        return;
    }
}
