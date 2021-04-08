package script.ai;

import script.*;


public class tun_badon extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Tun Badon");
        sendSystemMessagePlanetTestingOnly("ATTENTION ENDOR CIVILIANS: The World Boss Tun Badon has been located around the Orphaned Marauder's Cave");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION ENDOR CIVILIANS: The World Boss Tun Badon has been located around the Orphaned Marauder's Cave");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION ENDOR CIVILIANS: The World Boss Tun Badon has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
