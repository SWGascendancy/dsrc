package script.working.tfiala;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

public class wound_me extends script.base_script
{
    public wound_me()
    {
    }
    public int OnHearSpeech(obj_id self, obj_id objSpeaker, String strText)
    {
        return SCRIPT_CONTINUE;
    }
}
