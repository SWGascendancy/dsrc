package script.npc.celebrity;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;
import script.library.hue;
import script.library.colors;
import script.library.chat;
import script.library.factions;
import script.library.skill;

public class bib_fortuna extends script.base_script
{
    public bib_fortuna()
    {
    }
    public static final String CONVO = "celebrity/captain_eso";
    public int OnAttach(obj_id self)
    {
        ai_lib.setDefaultCalmBehavior(self, ai_lib.BEHAVIOR_SENTINEL);
        attachScript(self, "conversation.bib_fortuna");
        return SCRIPT_CONTINUE;
    }
}
