package script.npc.celebrity;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;

public class nien_nunb extends script.base_script
{
    public nien_nunb()
    {
    }
    public int OnAttach(obj_id self)
    {
        setName(self, "Nien Nunb");
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "conversation.rtp_nien_nunb_main");
        return SCRIPT_CONTINUE;
    }
}
