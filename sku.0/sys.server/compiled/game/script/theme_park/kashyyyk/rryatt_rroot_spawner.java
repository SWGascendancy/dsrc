package script.theme_park.kashyyyk;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.create;
import script.library.groundquests;
import script.library.ai_lib;

public class rryatt_rroot_spawner extends script.base_script
{
    public rryatt_rroot_spawner()
    {
    }
    public static final String triggerName = "triggerVolumeSpawnRroot";
    public static final float triggerRange = 100f;
    public static final String rrootSpawned = "spawned.rrootSpawned";
    public static final String rrootId = "spawned.rrootId";
    public static final String rrootOwner = "spawned.rrootOwner";
    public static final String rrootParent = "spawned.rrootParent";
    public static final boolean doLogging = false;
    public int OnInitialize(obj_id self)
    {
        establishTriggerVolume(self);
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        establishTriggerVolume(self);
        return SCRIPT_CONTINUE;
    }
    public void establishTriggerVolume(obj_id self)
    {
        if (!hasTriggerVolume(self, triggerName))
        {
            doLogging("establishTriggerVolume", "Trigger Volume(" + triggerName + "/" + triggerRange + ") set");
            createTriggerVolume(triggerName, triggerRange, true);
            return;
        }
        doLogging("establishTriggerVolume", "Trigger Volume(" + triggerName + "/" + triggerRange + ") already established");
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher)
    {
        if (volumeName.equals(triggerName))
        {
            if (hasRrootQuest(breacher))
            {
                if (isRrootAvailable(self))
                {
                    generateRroot(self, breacher);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnTriggerVolumeExited(obj_id self, String volumeName, obj_id breacher)
    {
        if (volumeName.equals(triggerName))
        {
            if (hasRrootQuest(breacher))
            {
                if (ownsThisSpawn(self, breacher))
                {
                    handlePlayerDepart(self);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
    public boolean hasRrootQuest(obj_id player)
    {
        return groundquests.isQuestActive(player, "ep3_kymayrr_rescue_rroot");
    }
    public void generateRroot(obj_id self, obj_id player)
    {
        location spawnLoc = getLocation(self);
        obj_id rroot = create.object("ep3_rroot", spawnLoc);
        setYaw(rroot, 117);
        ai_lib.setDefaultCalmBehavior(rroot, ai_lib.BEHAVIOR_SENTINEL);
        utils.setScriptVar(self, rrootSpawned, 1);
        utils.setScriptVar(self, rrootId, rroot);
        utils.setScriptVar(self, rrootOwner, player);
        utils.setScriptVar(rroot, rrootParent, self);
        messageTo(rroot, "destroyRroot", null, 300, false);
    }
    public boolean ownsThisSpawn(obj_id self, obj_id player)
    {
        if (utils.hasScriptVar(self, rrootOwner))
        {
            return (utils.getObjIdScriptVar(self, rrootOwner) == player);
        }
        else 
        {
            return false;
        }
    }
    public boolean isGettingKey(obj_id player)
    {
        return groundquests.isTaskActive(player, "ep3_kymayrr_rescue_rroot", "taskRecoverCode");
    }
    public void handlePlayerDepart(obj_id self)
    {
        destroyObject(utils.getObjIdScriptVar(self, rrootId));
        utils.removeScriptVar(self, rrootSpawned);
        utils.removeScriptVar(self, rrootId);
        utils.removeScriptVar(self, rrootOwner);
    }
    public boolean isRrootAvailable(obj_id self)
    {
        return (!utils.hasScriptVar(self, rrootSpawned));
    }
    public void doLogging(String section, String message)
    {
        if (doLogging)
        {
            LOG("debug/rryatt_rroot_spawner/" + section, message);
        }
    }
    public int destroyRroot(obj_id self, dictionary params)
    {
        handlePlayerDepart(self);
        return SCRIPT_CONTINUE;
    }
    public int OnHearSpeech(obj_id self, obj_id speaker, String text)
    {
        if (doLogging)
        {
            sendSystemMessageTestingOnly(speaker, "My objId is " + self);
            establishTriggerVolume(self);
        }
        return SCRIPT_CONTINUE;
    }
}
