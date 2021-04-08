package script.ai;

import script.*;


public class argerda extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Argerda");
        sendSystemMessagePlanetTestingOnly("ATTENTION DATHOMIR CIVILIANS: The World Boss Argerda has been located due north of the Singing Mountain Clan POI");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DATHOMIR CIVILIANS: The World Boss Deza has been located due north of the Singing Mountain Clan POI");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DATHOMIR CIVILIANS: The World Boss Argerda has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
