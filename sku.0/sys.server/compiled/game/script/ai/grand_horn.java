package script.ai;

import script.*;


public class grand_horn extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Grand Horn");
        sendSystemMessagePlanetTestingOnly("ATTENTION TATOOINE CIVILIANS: The World Boss Grand Horn has been located due West of Ben Kenobi's House");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION TATOOINE CIVILIANS: The World Boss Grand Horn has been located due West of Ben Kenobi's House");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION TATOOINE CIVILIANS: The World Boss Grand Horn has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
