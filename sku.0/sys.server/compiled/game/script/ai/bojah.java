package script.ai;

import script.*;


public class bojah extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Bojah");
        sendSystemMessagePlanetTestingOnly("ATTENTION NABOO CIVILIANS: The World Boss Bojah has been located west of the Gungan Sacred Place");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION NABOO CIVILIANS: The World Boss Bojah has been located west of the Gungan Sacred Place");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION NABOO CIVILIANS: The World Boss Bojah has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
