package script.theme_park.recruitment.base;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.ai.ai;
import script.library.ai_lib;

public class base_recruiter extends script.base_script
{
    public base_recruiter()
    {
    }
    public static final String DATA_ITEM_CONVO = "data_item";
    public int OnInitialize(obj_id self)
    {
        if (!isObjectPersisted(self))
        {
            persistObject(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        stop(self);
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        return SCRIPT_CONTINUE;
    }
    public int OnStartNpcConversation(obj_id self, obj_id speaker)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnNpcConversationResponse(obj_id self, String convo, obj_id player, string_id response)
    {
        return SCRIPT_CONTINUE;
    }
}
