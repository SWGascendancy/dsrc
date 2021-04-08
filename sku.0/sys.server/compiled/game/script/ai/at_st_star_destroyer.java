package script.ai;

import script.*;


public class at_st_star_destroyer extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Orbital Attack Haggis Beta");
        sendSystemMessagePlanetTestingOnly("ATTENTION: Orbital Attack Haggis Beta has taken the field of battle.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: Orbital Attack Haggis Beta has taken the field of battle.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION: Orbital Attack Haggis Beta has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
