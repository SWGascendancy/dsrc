package script.ai;

import script.*;


public class brakas extends script.base_script
{
    public int OnAttach(obj_id self)
    {
        setName(self, "Brakas");
        sendSystemMessagePlanetTestingOnly("ATTENTION YAVIN CIVILIANS: The World Boss Brakas has been located due north of the Labor Outpost.");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION YAVIN CIVILIANS: The World Boss Brakas has been located due north of the Labor Outpost.");
      return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
      sendSystemMessagePlanetTestingOnly("ATTENTION YAVIN CIVILIANS: The World Boss Brakas has been slain by " + getName(killer));
      return SCRIPT_CONTINUE;
    }
}
