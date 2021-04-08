package script.ai;

import script.*;


public class ascendancy_sith extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Ascendancy Sith Master");
        sendSystemMessagePlanetTestingOnly("ATTENTION: Ascendancy Sith Master has taken the field of battle.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: Ascendancy Sith Master has taken the field of battle.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: Ascendancy Sith Master has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
