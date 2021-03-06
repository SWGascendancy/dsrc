package script.theme_park.heroic.axkva_min;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.ai_lib;
import script.library.trial;

public class nandina extends script.base_script
{
    public nandina()
    {
    }
    public int OnAttach(obj_id self)
    {
        messageTo(self, "setGorvoAsPet", null, 1.0f, false);
        trial.setHp(self, trial.HP_AKXVA_NANDINA);
        return SCRIPT_CONTINUE;
    }
    public int setGorvoAsPet(obj_id self, dictionary params)
    {
        obj_id[] spawn_id = trial.getObjectsInDungeonWithObjVar(trial.getTop(self), "spawn_id");
        if (spawn_id == null || spawn_id.length == 0)
        {
            debugSpeakMsg(self, "found no spawn_id");
            return SCRIPT_CONTINUE;
        }
        obj_id gorvo = null;
        for (int i = 0; i < spawn_id.length; i++)
        {
            if ((getStringObjVar(spawn_id[i], "spawn_id")).equals("gorvo"))
            {
                gorvo = spawn_id[i];
            }
        }
        setMaster(gorvo, self);
        obj_id[] allies = new obj_id[2];
        allies[0] = gorvo;
        allies[1] = self;
        ai_lib.establishAgroLink(self, allies);
        return SCRIPT_CONTINUE;
    }
    public int OnEnteredCombat(obj_id self)
    {
        obj_id[] players = trial.getPlayersInDungeon(trial.getTop(self));
        if (players == null || players.length == 0)
        {
            return SCRIPT_CONTINUE;
        }
        for (int i = 0; i < players.length; i++)
        {
            addHate(self, players[i], 1);
        }
        return SCRIPT_CONTINUE;
    }
}
