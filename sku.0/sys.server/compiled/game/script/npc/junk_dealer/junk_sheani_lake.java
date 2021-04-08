package script.npc.junk_dealer;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;

public class junk_sheani_lake extends script.base_script
{
    public junk_sheani_lake()
    {
    }
    public int OnAttach(obj_id self)
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        detachScript(self, "conversation.junk_sheani_lake");
        attachScript(self, "npc.converse.junk_dealer");
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        detachScript(self, "conversation.junk_sheani_lake");
        attachScript(self, "npc.converse.junk_dealer");
        return SCRIPT_CONTINUE;
    }
}
