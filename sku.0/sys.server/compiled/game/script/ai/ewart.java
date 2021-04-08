package script.ai;

import script.*;


public class ewart extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Ewart");
        sendSystemMessagePlanetTestingOnly("ATTENTION RORI CIVILIANS: The World Boss Ewart has been located due northwest of Restuss");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION RORI CIVILIANS: The World Boss Ewart has been located due northwest of Restuss");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION RORI CIVILIANS: The World Boss Ewart has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
