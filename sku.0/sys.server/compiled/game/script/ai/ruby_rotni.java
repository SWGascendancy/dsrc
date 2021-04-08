package script.ai;

import script.*;


public class ruby_rotni extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Ruby Rotten");
        sendSystemMessagePlanetTestingOnly("ATTENTION LOK CIVILIANS: The World Boss Ruby Rotten has been located due west of Kimogila Town");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION LOK CIVILIANS: The World Boss Ruby Rotten has been located due west of Kimogila Town");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION LOK CIVILIANS: The World Boss Ruby Rotten has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
