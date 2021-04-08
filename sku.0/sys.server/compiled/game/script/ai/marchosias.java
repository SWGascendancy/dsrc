package script.ai;

import script.*;


public class marchosias extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Marchosias");
        sendSystemMessagePlanetTestingOnly("ATTENTION ENDOR CIVILIANS: The World Boss Marchosias has been located due northeast of the Ewok Lake Village");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION ENDOR CIVILIANS: The World Boss Marchosias has been located due northeast of the Ewok Lake Village");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION ENDOR CIVILIANS: The World Boss Marchosias has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
