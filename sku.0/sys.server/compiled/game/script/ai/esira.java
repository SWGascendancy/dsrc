package script.ai;

import script.*;


public class esira extends script.base_script
{
      public int OnAttach(obj_id self)
    {
        setName(self, "Esira");
        sendSystemMessagePlanetTestingOnly("ATTENTION NABOO CIVILIANS: The World Boss Esira has been located northeast of Theed");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION NABOO CIVILIANS: The World Boss Esira has been located northeast of Theed");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION NABOO CIVILIANS: The World Boss Esira has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
