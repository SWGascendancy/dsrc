package script.theme_park.dungeon.mustafar_trials.volcano_battlefield;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;
import script.library.utils;
import script.library.create;
import script.library.buff;
import script.library.trial;

public class event_three_boss extends script.base_script
{
    public event_three_boss()
    {
    }
    public static final boolean LOGGING = false;
    public int OnAttach(obj_id self)
    {
        trial.setHp(self, trial.HP_VOLCANO_THREE_BOSS);
        trial.markAsVolcanoCommander(self);
        setInvulnerable(self, true);
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        obj_id parent = trial.getParent(self);
        dictionary dict = new dictionary();
        dict.put("type", "boss");
        if (isIdValid(parent))
        {
            messageTo(parent, "eventMobDied", dict, 0, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnExitedCombat(obj_id self)
    {
        if (!isIncapacitated(self))
        {
            resetEncounter(self);
            return SCRIPT_CONTINUE;
        }
        return SCRIPT_CONTINUE;
    }
    public int guardReset(obj_id self, dictionary params)
    {
        resetEncounter(self);
        return SCRIPT_CONTINUE;
    }
    public void resetEncounter(obj_id self)
    {
        clearAllAdds(self);
        respawnAdds(self);
        resetSelf(self);
    }
    public void clearAllAdds(obj_id self)
    {
        obj_id[] objects = getCreaturesInRange(getLocation(self), 400);
        if (objects == null || objects.length == 0)
        {
            doLogging("clearAllAdds", "There are no objects in range");
            return;
        }
        for (int i = 0; i < objects.length; i++)
        {
            if (hasObjVar(objects[i], "boss"))
            {
                obj_id parent = getObjIdObjVar(objects[i], "boss");
                if (parent == self)
                {
                    trial.cleanupNpc(objects[i]);
                }
            }
        }
    }
    public void respawnAdds(obj_id self)
    {
        obj_id parent = trial.getParent(self);
        messageTo(parent, "spawnGuards", null, 2, false);
    }
    public void resetSelf(obj_id self)
    {
        int max = getMaxHealth(self);
        int current = getHealth(self);
        int toHeal = max - current;
        addToHealth(self, toHeal);
        setInvulnerable(self, true);
        ai_lib.clearCombatData();
    }
    public int beginAttack(obj_id self, dictionary params)
    {
        setInvulnerable(self, false);
        obj_id[] players = trial.getValidTargetsInRadius(self, 100.0f);
        if (players == null || players.length == 0)
        {
            doLogging("beginAttack", "Found no players to attack");
            return SCRIPT_CONTINUE;
        }
        obj_id toAttack = trial.getClosest(self, players);
        if (!isIdValid(toAttack))
        {
            doLogging("beginAttack", "player toAttack, was invalid");
            return SCRIPT_CONTINUE;
        }
        startCombat(self, toAttack);
        return SCRIPT_CONTINUE;
    }
    public void doLogging(String section, String message)
    {
        if (LOGGING)
        {
            LOG("logging/event_two_boss/" + section, message);
        }
    }
}
