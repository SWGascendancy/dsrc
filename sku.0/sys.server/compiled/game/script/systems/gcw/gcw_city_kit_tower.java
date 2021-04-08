package script.systems.gcw;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.factions;
import script.library.gcw;
import script.library.utils;

public class gcw_city_kit_tower extends script.systems.gcw.gcw_city_kit
{
    public gcw_city_kit_tower()
    {
    }
    public obj_id getPlayerInTower()
    {
        return null;
    }
    public void putPlayerInTower()
    {
    }
    public void setupConstructionQuests(obj_id self, obj_id pylon)
    {
        setName(pylon, "Tower Construction Site");
        utils.setScriptVar(pylon, "gcw.name", "Tower Construction Site");
        attachScript(pylon, "systems.gcw.gcw_city_pylon_tower");
    }
    public void setupInvasionQuests(obj_id kit)
    {
    }
    public obj_id createFactionKit(int faction, location loc)
    {
        if (loc == null)
        {
            return null;
        }
        obj_id kit = null;
        if (factions.FACTION_FLAG_IMPERIAL == faction)
        {
            kit = createObject("object/tangible/destructible/gcw_imperial_tower.iff", loc);
            setName(kit, "Imperial Watch Tower");
        }
        else if (factions.FACTION_FLAG_REBEL == faction)
        {
            kit = createObject("object/tangible/destructible/gcw_rebel_tower.iff", loc);
            setName(kit, "Rebel Watch Tower");
        }
        return kit;
    }
}
