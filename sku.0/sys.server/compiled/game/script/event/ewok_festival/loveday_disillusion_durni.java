package script.event.ewok_festival;

import script.dictionary;
import script.obj_id;

public class loveday_disillusion_durni extends script.base_script
{
    public loveday_disillusion_durni()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "handleDurniInitialize", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int handleDurniInitialize(obj_id self, dictionary params)
    {
        setObjVar(self, "questFlavorObject", true);
        return SCRIPT_CONTINUE;
    }
    public int handleQuestFlavorObject(obj_id self, dictionary params)
    {
        messageTo(self, "handleCleanUp", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public int handleCleanUp(obj_id self, dictionary params)
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
