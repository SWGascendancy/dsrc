package script.ai;

import script.*;


public class ssissa extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Ssissa");
        sendSystemMessagePlanetTestingOnly("ATTENTION KASHYYYK CIVILIANS: The World Boss Ssissa has been located near Myyydril Caverns");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION KASHYYYK CIVILIANS: The World Boss Ssissa has been located near Myyydril Caverns");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION KASHYYYK CIVILIANS: The World Boss Ssissa has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
