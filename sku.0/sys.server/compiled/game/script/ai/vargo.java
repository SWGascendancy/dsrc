package script.ai;

import script.*;


public class vargo extends script.base_script
{
      public int OnAttach(obj_id self)
    {
        setName(self, "Vargo");
        sendSystemMessagePlanetTestingOnly("ATTENTION CORELLIA CIVILIANS: The World Boss Vargo has been located northeast of the Rebel Hideout");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION CORELLIA CIVILIANS: The World Boss Vargo has been located northeast of the Rebel Hideout");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION CORELLIA CIVILIANS: The World Boss Vargo has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
