package script.theme_park.tatooine.bestine_pilots_club;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.hue;
import script.library.colors;
import script.library.ai_lib;
import script.library.factions;
import script.ai.ai;

public class trooper1 extends script.base_script
{
    public trooper1()
    {
    }
    public int OnInitialize(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnAttach(obj_id self)
    {
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self)
    {
        obj_id club = getObjIdObjVar(self, "club");
        messageTo(club, "trooper1Died", null, 20, true);
        return SCRIPT_CONTINUE;
    }
}
