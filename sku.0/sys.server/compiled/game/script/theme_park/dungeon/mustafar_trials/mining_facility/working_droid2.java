package script.theme_park.dungeon.mustafar_trials.mining_facility;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.sequencer;

public class working_droid2 extends script.base_script
{
    public working_droid2()
    {
    }
    public int OnAttach(obj_id self)
    {
        attachScript(self, "content_tools.sequencer_master_object");
        sequencer.registerSequenceObject(self, "droid2");
        setObjVar(self, "strSequenceTable", "must_droid2");
        setInvulnerable(self, true);
        messageTo(self, "doEvents", null, 10, false);
        return SCRIPT_CONTINUE;
    }
}
