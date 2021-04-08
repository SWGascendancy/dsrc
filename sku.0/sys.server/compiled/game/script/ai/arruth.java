package script.ai;

import script.*;


public class arruth extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Arruth");
        sendSystemMessagePlanetTestingOnly("ATTENTION KASHYYYK CIVILIANS: The World Boss Arruth has been located west of Kachirho");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION KASHYYYK CIVILIANS: The World Boss Arruth has been located west of Kachirho");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION KASHYYYK CIVILIANS: The World Boss Arruth has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
