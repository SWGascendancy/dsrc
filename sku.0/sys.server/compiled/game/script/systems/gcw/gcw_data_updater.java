package script.systems.gcw;

import script.dictionary;
import script.library.gcw;
import script.obj_id;

public class gcw_data_updater extends script.base_script
{
    public gcw_data_updater()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "updateGCWInfo", null, 1.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        messageTo(self, "updateGCWInfo", null, 1.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int updateGCWInfo(obj_id self, dictionary params)
    {
        if(!isIdValid(self) || !exists(self)){
            debugServerConsoleMsg(self, "Exception: Apparently, I'm not a valid thing for the GCW Data Updater.  (gcw_data_updater)");
            debugServerConsoleMsg(self, "What I am: " + getTemplateName(self));
            debugServerConsoleMsg(self, "I'm located at " + getLocation(self));
            debugServerConsoleMsg(self, "My Scene is " + getLocation(self).area);
            return SCRIPT_CONTINUE;
        }
        int imperial = gcw.getImperialPercentileByRegion(self);
        int rebel = gcw.getRebelPercentileByRegion(self);
        int oldImperial = getIntObjVar(self, "Imperial.controlScore");
        if ((oldImperial < 50 && imperial > 50) || (oldImperial > 50 && imperial < 50))
        {
            messageTo(self, "destroyChildren", null, 1.0f, false);
        }
        setObjVar(self, "Imperial.controlScore", imperial);
        setObjVar(self, "Rebel.controlScore", rebel);
        messageTo(self, "updateGCWInfo", null, gcw.GCW_UPDATE_PULSE + rand(1, 100), false);
        return SCRIPT_CONTINUE;
    }
}
