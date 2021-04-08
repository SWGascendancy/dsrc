package script.systems.spawning.dropship;

import script.dictionary;
import script.obj_id;

public class emperorsday_yt1300 extends script.systems.spawning.dropship.base
{
    public emperorsday_yt1300()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "handleAttachDelay", null, 5.0f, false);
        return super.OnAttach(self);
    }
    public int handleAttachDelay(obj_id self, dictionary params)
    {
        setPosture(self, POSTURE_UPRIGHT);
        queueCommand(self, (-1465754503), self, "", COMMAND_PRIORITY_FRONT);
        messageTo(self, "takeOff", null, 600.0f, true);
        return SCRIPT_CONTINUE;
    }
    public int changePosture(obj_id self, dictionary params)
    {
        setPosture(self, POSTURE_PRONE);
        queueCommand(self, (-1114832209), self, "", COMMAND_PRIORITY_FRONT);
        messageTo(self, "selfCleanUp", null, 60.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int takeOff(obj_id self, dictionary params)
    {
        messageTo(self, "changePosture", null, 5.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int selfCleanUp(obj_id self, dictionary params)
    {
        if (isIdValid(self))
        {
            destroyObject(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
}
