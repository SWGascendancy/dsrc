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

public class tech_goto_two extends script.base_script
{
    public tech_goto_two()
    {
    }
    public int OnAttach(obj_id self)
    {
        sequencer.registerSequenceObject(self, "tech_goto2");
        return SCRIPT_CONTINUE;
    }
}
