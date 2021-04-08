package script.ai;

import script.*;


public class tular extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Tular");
        sendSystemMessagePlanetTestingOnly("ATTENTION MUSTAFAR CIVILIANS: The World Boss Tular can be located north of Mensix Mining Facility.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION MUSTAFAR CIVILIANS: The World Boss Tular can be located north of Mensix Mining Facility.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION MUSTAFAR CIVILIANS: The World Boss has Tular has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
