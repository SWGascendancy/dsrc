package script.ai;

import script.*;


public class veras extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Veras");
        sendSystemMessagePlanetTestingOnly("ATTENTION CORELLIA CIVILIANS: The World Boss Veras has been located northeast of the Rebel Hideout");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION CORELLIA CIVILIANS: The World Boss Veras has been located northeast of the Rebel Hideout");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION CORELLIA CIVILIANS: The World Boss Veras has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
