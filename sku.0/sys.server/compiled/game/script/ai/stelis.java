package script.ai;

import script.*;


public class stelis extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Stelis");
        sendSystemMessagePlanetTestingOnly("ATTENTION YAVIN IV CIVILIANS: The World Boss Stelis can be located due west of the Imperial Fortress.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION YAVIN IV CIVILIANS: The World Boss Stelis can be located due west of the Imperial Fortress.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION YAVIN IV: The World Boss has Stelis has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
