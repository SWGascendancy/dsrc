package script.theme_park.heroic.echo_base;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.trial;

public class the_bomb extends script.base_script
{
    public the_bomb()
    {
    }
    public int OnAttach(obj_id self)
    {
        trial.setHp(self, 150000);
        return SCRIPT_CONTINUE;
    }
    public int start_detonate(obj_id self, dictionary params)
    {
        int detonate_time = hasObjVar(self, "detonate") ? getIntObjVar(self, "detonate") : 900;
        messageTo(self, "detonate", null, detonate_time, false);
        return SCRIPT_CONTINUE;
    }
    public int detonate(obj_id self, dictionary params)
    {
        dictionary dict = trial.getSessionDict(trial.getTop(self));
        dict.put("triggerType", "triggerId");
        dict.put("triggerName", "p2_rebel_command_destroy");
        messageTo(trial.getTop(self), "triggerFired", dict, 1.0f, false);
        location death = getLocation(self);
        playClientEffectObj(getClosestPlayer(death), "clienteffect/combat_explosion_lair_large.cef", self, "");
        playClientEffectLoc(getClosestPlayer(death), "clienteffect/combat_explosion_lair_large.cef", death, 0);
        messageTo(self, "cleanup", null, 3.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnObjectDisabled(obj_id self, obj_id killer)
    {
        messageTo(self, "cleanup", null, 3.0f, false);
        return SCRIPT_CONTINUE;
    }
    public int cleanup(obj_id self, dictionary params)
    {
        trial.cleanupObject(self);
        return SCRIPT_CONTINUE;
    }
}
