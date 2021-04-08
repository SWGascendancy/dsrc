package script.ai;

import script.*;


public class grange extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Grange");
        sendSystemMessagePlanetTestingOnly("ATTENTION DATHOMIR CIVILIANS: The World Boss Grange has been located due West of the Dathomir Tar Pits");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DATHOMIR CIVILIANS: The World Boss Grange has been located due West of the Dathomir Tar Pits");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DATHOMIR CIVILIANS: The World Boss Grange has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
