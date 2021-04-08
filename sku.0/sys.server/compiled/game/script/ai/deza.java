package script.ai;

import script.*;


public class deza extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Deza Model 99");
        sendSystemMessagePlanetTestingOnly("ATTENTION MUSTAFAR CIVILIANS: The World Boss Deza has been located due west of the Old Research Facility");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION MUSTAFAR CIVILIANS: The World Boss Deza has been located due west of the Old Research Facility");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION MUSTAFAR CIVILIANS: The World Boss Deza has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
