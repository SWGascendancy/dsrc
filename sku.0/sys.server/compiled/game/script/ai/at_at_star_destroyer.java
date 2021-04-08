package script.ai;

import script.*;


public class at_at_star_destroyer extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Orbital Attack Haggis Alpha");
        sendSystemMessagePlanetTestingOnly("ATTENTION: Orbital Attack Haggis Alpha has taken the field of battle.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: Orbital Attack Haggis Alpha has taken the field of battle.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: Orbital Attack Haggis Alpha has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
