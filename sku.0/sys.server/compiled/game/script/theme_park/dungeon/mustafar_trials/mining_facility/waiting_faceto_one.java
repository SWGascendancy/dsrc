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

public class waiting_faceto_one extends script.base_script
{
    public waiting_faceto_one()
    {
    }
    public int OnAttach(obj_id self)
    {
        sequencer.registerSequenceObject(self, "wait_faceto1");
        return SCRIPT_CONTINUE;
    }
}
