package script.ai;

import script.*;


public class eppert extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Eppert");
        sendSystemMessagePlanetTestingOnly("ATTENTION TALUS CIVILIANS: The World Boss Eppert has been located due north of the Weapon Depot");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION TALUS CIVILIANS: The World Boss Eppert has been located due north of the Weapon Depot");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION TALUS CIVILIANS: The World Boss Eppert has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
