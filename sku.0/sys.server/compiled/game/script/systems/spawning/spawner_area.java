package script.systems.spawning;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.ai_lib;
import script.library.space_utils;
import script.library.spawning;
import script.library.create;
import script.dictionary;
import script.location;
import script.obj_id;

public class spawner_area extends script.base_script
{
    public spawner_area()
    {
    }
    public int OnAttach(obj_id self)
    {
        if (!hasObjVar(self, "registerWithController"))
        {
            setObjVar(self, "registerWithController", 1);
        }
        if (hasObjVar(self, "strName"))
        {
            setName(self, getStringObjVar(self, "strName"));
        }
        if (canSpawnByConfigSetting())
        {
            messageTo(self, "doSpawnEvent", null, 20, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        if (!hasObjVar(self, "registerWithController"))
        {
            setObjVar(self, "registerWithController", 1);
        }
        if (hasObjVar(self, "strName"))
        {
            setName(self, getStringObjVar(self, "strName"));
        }
        if (canSpawnByConfigSetting())
        {
            messageTo(self, "doSpawnEvent", null, 20, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int doSpawnEvent(obj_id self, dictionary params)
    {
        if (!spawning.checkSpawnCount(self))
        {
            return SCRIPT_CONTINUE;
        }
        int intGoodLocationSpawner = getIntObjVar(self, "intGoodLocationSpawner");
        float fltRadius = getFloatObjVar(self, "fltRadius");
        String strSpawnType = getStringObjVar(self, "strSpawns");
        float fltSize = 8.0f;
        String strSpawn = strSpawnType;
        String strFileName = "datatables/spawning/ground_spawning/types/" + strSpawnType + ".iff";
        if (dataTableOpen(strFileName))
        {
            String[] strSpawns = dataTableGetStringColumnNoDefaults(strFileName, "strItem");
            float[] fltSizes = dataTableGetFloatColumn(strFileName, "fltSize");
            if (strSpawns == null || strSpawns.length == 0)
            {
                setName(self, "Mangled spawner. strFileName is " + strFileName + " I couldnt find any spawns in that file.");
            }
            int intRoll = rand(0, strSpawns.length - 1);
            if (fltSizes.length == 0)
            {
                setName(self, "Missing spawn sizes in " + strFileName);
            }
            if (fltSizes.length != strSpawns.length)
            {
                setName(self, "MISSING VALUES: Each spawn must have an associated size (" + strFileName + ")");
            }
            fltSize = fltSizes[intRoll];
            strSpawn = strSpawns[intRoll];
        }
        if (strSpawn == null || strSpawn.length() < 1)
        {
            setName(self, "Mangled spawner. strSpawn objvar is missing.");
            return SCRIPT_CONTINUE;
        }
        location locTest = spawning.getRandomLocationInCircle(getLocation(self), fltRadius);
        fltSize = getClosestSize(fltSize);
        if (intGoodLocationSpawner > 0)
        {
            requestLocation(self, strSpawn, locTest, rand(100, 200), fltSize, true, true);
        }
        else 
        {
            createMob(strSpawn, null, locTest, fltRadius, self);
        }
        return SCRIPT_CONTINUE;
    }
    public float getClosestSize(float fltOriginalSize)
    {
        if (fltOriginalSize <= 4.0f)
        {
            return 4.0f;
        }
        if (fltOriginalSize <= 8.0f)
        {
            return 8.0f;
        }
        if (fltOriginalSize <= 12.0f)
        {
            return 12.0f;
        }
        if (fltOriginalSize <= 16.0f)
        {
            return 16.0f;
        }
        if (fltOriginalSize <= 32.0f)
        {
            return 32.0f;
        }
        else if (fltOriginalSize <= 48.0f)
        {
            return 48.0f;
        }
        else if (fltOriginalSize <= 64.0f)
        {
            return 64.0f;
        }
        else if (fltOriginalSize <= 80.0f)
        {
            return 80f;
        }
        else if (fltOriginalSize <= 96.0f)
        {
            return 96f;
        }
        return 32f;
    }
    private float getRespawnTime(obj_id self){
        try {
            if (hasObjVar(self, "fltRespawnTime")) {
                return getFloatObjVar(self, "fltRespawnTime");
            } else {
                return rand(getFloatObjVar(self, "fltMinSpawnTime"), getFloatObjVar(self, "fltMaxSpawnTime"));
            }
        }
        catch(Exception e){
            setName(self, "BAD SPAWNEGG!!  Could not get a respawn time from datatable.");
            return 0;
        }
    }
    public void createMob(String strId, obj_id objLocationObject, location locLocation, float fltRadius, obj_id self)
    {
        if (!spawning.checkSpawnCount(self))
        {
            return;
        }
        float spawnerYaw = getYaw(self);
        int intIndex = strId.indexOf(".iff");
        float fltRespawnTime = getRespawnTime(self);

        if (isIdValid(objLocationObject))
        {
            destroyObject(objLocationObject);
        }
        obj_id objTemplate;
        if (intIndex > -1)
        {
            objTemplate = createObject(strId, locLocation);
            if (!isIdValid(objTemplate))
            {
                setName(self, "BAD MOB OF TYPE " + strId);
                return;
            }
        }
        else 
        {
            objTemplate = create.object(strId, locLocation);
            if (!isIdValid(objTemplate))
            {
                setName(self, "BAD MOB OF TYPE " + strId);
                return;
            }
            int intBehavior = getIntObjVar(self, "intDefaultBehavior");
            ai_lib.setDefaultCalmBehavior(objTemplate, intBehavior);
        }
        spawning.incrementSpawnCount(self);
        spawning.addToSpawnDebugList(self, objTemplate);
        setObjVar(objTemplate, "objParent", self);
        setObjVar(objTemplate, "fltRespawnTime", fltRespawnTime);
        attachScript(objTemplate, "systems.spawning.spawned_tracker");
        setYaw(objTemplate, spawnerYaw);

        if (!spawning.checkSpawnCount(self))
        {
            return;
        }
        messageTo(self, "doSpawnEvent", null, fltRespawnTime, false);
        return;
    }
    public int OnLocationReceived(obj_id self, String strId, obj_id objLocationObject, location locLocation, float fltRadius)
    {
        if (isIdValid(objLocationObject))
        {
            createMob(strId, objLocationObject, locLocation, fltRadius, self);
        }
        else 
        {
            messageTo(self, "doSpawnEvent", null, getRespawnTime(self), false);
        }
        return SCRIPT_CONTINUE;
    }
    public int spawnDestroyed(obj_id self, dictionary params)
    {
        int intCurrentSpawnCount = utils.getIntScriptVar(self, "intCurrentSpawnCount");
        intCurrentSpawnCount = intCurrentSpawnCount - 1;
        if (intCurrentSpawnCount > -1)
        {
            utils.setScriptVar(self, "intCurrentSpawnCount", intCurrentSpawnCount);
        }
        else 
        {
            utils.setScriptVar(self, "intCurrentSpawnCount", 0);
        }
        messageTo(self, "doSpawnEvent", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public boolean canSpawnByConfigSetting()
    {
        String disableSpawners = getConfigSetting("GameServer", "disableAreaSpawners");
        if (disableSpawners == null)
        {
            return true;
        }
        if (disableSpawners.equals("true") || disableSpawners.equals("1"))
        {
            return false;
        }
        return true;
    }
    public int OnDestroy(obj_id self)
    {
        if (utils.hasScriptVar(self, "debugSpawnList"))
        {
            obj_id[] spawns = utils.getObjIdArrayScriptVar(self, "debugSpawnList");
            if (spawns == null || spawns.length == 0)
            {
                return SCRIPT_CONTINUE;
            }
            for (int i = 0; i < spawns.length; ++i)
            {
                if (isIdValid(spawns[i]) && exists(spawns[i]))
                {
                    messageTo(spawns[i], "selfDestruct", null, 5, false);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
