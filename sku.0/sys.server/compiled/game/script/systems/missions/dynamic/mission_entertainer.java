package script.systems.missions.dynamic;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;

public class mission_entertainer extends script.systems.missions.base.mission_dynamic_base
{
    public mission_entertainer()
    {
    }
    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int abortMission(obj_id self, dictionary params)
    {
        sendEntertainerIncomplete(self);
        return SCRIPT_CONTINUE;
    }
    public int OnStartMission(obj_id self, dictionary params)
    {
        debugServerConsoleMsg(self, "Setting up mission data in mission object for entertainer mission");
        obj_id objMissionData = getMissionData(self);
        location locDestination = getMissionStartLocation(objMissionData);
        updateMissionWaypoint(self, locDestination);
        return SCRIPT_CONTINUE;
    }
    public int entertainerSuccess(obj_id self, dictionary params)
    {
        if (!hasObjVar(self, "intCompleted"))
        {
            setObjVar(self, "intCompleted", 1);
            obj_id objPlayer = getMissionHolder(self);
            playMusic(self, "sound/music_mission_complete.snd");
            deliverReward(self);
            endMission(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int entertainerFail(obj_id self, dictionary params)
    {
        endMission(self);
        return SCRIPT_CONTINUE;
    }
    public int entertainerIncomplete(obj_id self, dictionary params)
    {
        obj_id objPlayer = getMissionHolder(self);
        playMusic(objPlayer, "sound/music_mission_complete.snd");
        endMission(self);
        return SCRIPT_CONTINUE;
    }
    public int OnEndMission(obj_id self, dictionary params)
    {
        return SCRIPT_CONTINUE;
    }
    public int entertainerEvent(obj_id self, dictionary params)
    {
        return SCRIPT_CONTINUE;
    }
}
