package script.ai;

import script.*;


public class kargas extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Kargas");
        sendSystemMessagePlanetTestingOnly("ATTENTION DANTOOINE CIVILIANS: The World Boss Kargas has been located around the Jedi Temple Ruins");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DANTOOINE CIVILIANS: The World Boss Kargas has been located around the Jedi Temple Ruins");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION DANTOOINE CIVILIANS: The World Boss Kargas has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
