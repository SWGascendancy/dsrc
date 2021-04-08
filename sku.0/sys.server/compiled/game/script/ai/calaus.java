package script.ai;

import script.*;


public class calaus extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Calaus");
        sendSystemMessagePlanetTestingOnly("ATTENTION RORI CIVILIANS: The World Boss Calaus has been located due north of the Poacher vs Creature Battle");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION RORI CIVILIANS: The World Boss Calaus has been located due north of the Poacher vs Creature Battle");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION RORI CIVILIANS: The World Boss Calaus has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
