package script.item.trap;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.ai.ai_combat;
import script.library.combat;
import script.library.prose;
import script.library.ai_lib;
import script.library.utils;

public class trap_drowsy_dart extends script.item.trap.trap_base
{
    public trap_drowsy_dart()
    {
    }
    public static final int TRAP_DIFF = 5;
    public static final string_id SID_SYS_EFFECT = new string_id("trap/trap", "trap_drowsy_effect");
    public static final string_id SID_NO_EFFECT = new string_id("trap/trap", "trap_drowsy_effect_no");
    public int OnAttach(obj_id self)
    {
        if (!hasObjVar(self, "droid_trap"))
        {
            setObjVar(self, "trapDiff", TRAP_DIFF);
            setObjVar(self, "trapName", "drowsy_dart");
            setCount(self, 5);
        }
        return SCRIPT_CONTINUE;
    }
    public int trapHit(obj_id self, dictionary params)
    {
        if (params == null)
        {
            return super.trapHit(self, params);
        }
        obj_id target = params.getObjId("target");
        if ((target == null) || (target == obj_id.NULL_ID))
        {
            return super.trapHit(self, params);
        }
        if (!ai_lib.isMonster(target) || isIncapacitated(target) || isDead(target))
        {
            return super.trapHit(self, params);
        }
        obj_id player = params.getObjId("player");
        if (1 == getState(target, STATE_DIZZY))
        {
            prose_package pp = prose.getPackage(SID_NO_EFFECT, target);
            sendSystemMessageProse(player, pp);
            return super.trapHit(self, params);
        }
        prose_package pp = prose.getPackage(SID_SYS_EFFECT, target);
        sendSystemMessageProse(player, pp);
        if (!hasObjVar(self, "droid_trap"))
        {
            if (!ai_lib.isInCombat(target))
            {
                startCombat(target, player);
            }
        }
        else 
        {
            if (!ai_lib.isInCombat(target))
            {
                startCombat(target, self);
            }
        }
        float chance = params.getFloat("chance");
        grantTrapXP(player, target, 1.f);
        return super.trapHit(self, params);
    }
}
