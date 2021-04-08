package script.ai;

import script.*;


public class dryden_vos extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Dryden Vos");
        sendSystemMessagePlanetTestingOnly("ATTENTION LOK CIVILIANS: The World Boss Dryden Vos has been located due southwest of the Imperial Outpost");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION LOK CIVILIANS: The World Boss Dryden Vos has been located due southwest of the Imperial Outpost");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION LOK CIVILIANS: The World Boss Dryden Vos has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
