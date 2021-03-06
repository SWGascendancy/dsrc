package script.quest.hero_of_tatooine;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.chat;
import script.library.utils;
import script.library.ai_lib;
import script.library.utils;
import script.library.anims;
import script.library.create;
import script.ai.ai_combat;

public class pirate_02 extends script.base_script
{
    public pirate_02()
    {
    }
    public static final string_id NPC_NAME = new string_id("quest/hero_of_tatooine/npc_names", "pirate_trapped_01");
    public int OnAttach(obj_id self)
    {
        detachScript(self, "ai.ai_combat");
        setInvulnerable(self, true);
        setName(self, "");
        setName(self, localize(NPC_NAME));
        messageTo(self, "action01", null, 1f, false);
        return SCRIPT_CONTINUE;
    }
    public int OnArrivedAtLocation(obj_id self, String name)
    {
        if (name.equals("rugLoc") && !hasObjVar(self, "rugReached"))
        {
            obj_id building = getTopMostContainer(self);
            obj_id rug = getObjIdObjVar(building, "rug");
            destroyObject(rug);
            setObjVar(self, "rugReached", 1);
            messageTo(self, "action02", null, 3f, false);
            chat.chat(self, "Wait for me!");
        }
        if (name.equals("door") && !hasObjVar(self, "doorReached"))
        {
            setObjVar(self, "doorReached", 1);
            destroyObject(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int action01(obj_id self, dictionary params)
    {
        obj_id structure = getTopMostContainer(self);
        if (!isIdValid(structure))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id building = getTopMostContainer(self);
        obj_id rug = getCellId(building, "livingroom");
        location here = getLocation(self);
        String planet = here.area;
        location rugTarget = new location(-8.07f, 0.31f, -3.29f, planet, rug);
        ai_lib.aiPathTo(self, rugTarget);
        addLocationTarget("rugLoc", rugTarget, 1);
        return SCRIPT_CONTINUE;
    }
    public int action02(obj_id self, dictionary params)
    {
        obj_id structure = getTopMostContainer(self);
        if (!isIdValid(structure))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id building = getTopMostContainer(self);
        obj_id door = getCellId(building, "hall1");
        location here = getLocation(self);
        String planet = here.area;
        location doorTarget = new location(-6.20f, 0.57f, 10.92f, planet, door);
        ai_lib.aiPathTo(self, doorTarget);
        addLocationTarget("door", doorTarget, 1);
        return SCRIPT_CONTINUE;
    }
}
