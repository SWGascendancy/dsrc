package script.ai;

import script.*;


public class ig88_general_grievous extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "General Grievous");
        sendSystemMessagePlanetTestingOnly("ATTENTION: General Grievous has taken the field of battle.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: General Grievous has taken the field of battle.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: General Grievous has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
