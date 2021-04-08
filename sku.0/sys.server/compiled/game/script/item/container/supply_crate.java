package script.item.container;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class supply_crate extends script.base_script
{
    public supply_crate()
    {
    }
    public int OnInitialize(obj_id self)
    {
        messageTo(self, "handleCleanUp", null, 300.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnLostItem(obj_id self, obj_id destContainer, obj_id transferer, obj_id item)
    {
        int items = getFilledVolume(self);
        if (items == 0)
        {
            messageTo(self, "handleCleanUp", null, 5.0f, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAboutToReceiveItem(obj_id self, obj_id srcContainer, obj_id transferer, obj_id item)
    {
        if (isIdValid(transferer))
        {
            return SCRIPT_OVERRIDE;
        }
        return SCRIPT_CONTINUE;
    }
    public int handleCleanUp(obj_id self, dictionary params)
    {
        destroyObject(self);
        return SCRIPT_CONTINUE;
    }
}
