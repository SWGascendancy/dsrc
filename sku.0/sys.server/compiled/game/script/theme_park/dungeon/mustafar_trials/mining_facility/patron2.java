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

public class patron2 extends script.base_script
{
    public patron2()
    {
    }
    public int OnAttach(obj_id self)
    {
        setInvulnerable(self, true);
        sequencer.registerSequenceObject(self, "patron2");
        return SCRIPT_CONTINUE;
    }
}
