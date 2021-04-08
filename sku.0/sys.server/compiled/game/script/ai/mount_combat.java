package script.ai;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.ai.ai_combat;
import script.library.ai_lib;
import script.library.combat;
import script.library.pclib;
import script.library.factions;
import script.library.utils;
import script.library.pet_lib;
import script.library.scout;
import script.library.colors;
import script.library.xp;

public class mount_combat extends script.systems.combat.combat_base
{
    public mount_combat()
    {
    }
    public static final int RANGE_NEAR = 0;
    public static final int RANGE_MID_NEAR = 1;
    public static final int RANGE_MID_FAR = 2;
    public static final int RANGE_FAR = 3;
    public static final int COMBATMODE_NONE = 0;
    public static final int COMBATMODE_DEFAULT = 1;
    public static final int COMBATMODE_EQUIPPED = 2;
    public static final int COMBATMODE_SPECIAL = 3;
    public static final String CREATURE_TABLE = "datatables/mob/creatures.iff";
    public static final String ALERT_VOLUME_NAME = "alertTriggerVolume";
    public int aiCombatLoop(obj_id self, dictionary params)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id weapon, int[] damage)
    {
        return SCRIPT_OVERRIDE;
    }
    public int defenderCombatAction(obj_id self, obj_id attacker, obj_id weapon, int combatResult)
    {
        return SCRIPT_OVERRIDE;
    }
    public int handleDefenderCombatAction(obj_id self, dictionary params)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnDefenderCombatAction(obj_id self, obj_id attacker, obj_id weapon, int combatResult)
    {
        return SCRIPT_OVERRIDE;
    }
    public int vocalizeEndCombat(obj_id self, dictionary params)
    {
        return SCRIPT_OVERRIDE;
    }
    public int postCombatPathHome(obj_id self, dictionary params)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnMovePathComplete(obj_id self)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnMoveMoving(obj_id self)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnBehaviorChange(obj_id self, int newBehavior, int oldBehavior, int[] changeFlags)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnTriggerVolumeEntered(obj_id self, String volumeName, obj_id breacher)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnIncapacitateTarget(obj_id self, obj_id target)
    {
        return SCRIPT_OVERRIDE;
    }
    public int OnAttach(obj_id self)
    {
        setWantSawAttackTriggers(self, false);
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self)
    {
        setWantSawAttackTriggers(self, true);
        return SCRIPT_CONTINUE;
    }
}
