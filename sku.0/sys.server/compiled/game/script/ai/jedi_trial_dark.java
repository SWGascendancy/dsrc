package script.ai;

import script.*;


public class jedi_trial_dark extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Force Ascendant Jedi");
        sendSystemMessagePlanetTestingOnly("ATTENTION ALL CIVILIANS: The Force Ascendant Jedi has shown up to test a young Jedi");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION ALL CIVILIANS: The Force Ascendant Jedi has shown up to test a young Jedi");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION ALL CIVILIANS: The Force Ascendant Jedi has been slain and we have a new Elder Jedi CONGRATULATIONS " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
