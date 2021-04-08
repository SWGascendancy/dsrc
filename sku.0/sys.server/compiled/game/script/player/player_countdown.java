package script.player;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.sui;
import script.library.utils;
import script.library.buff;

public class player_countdown extends script.base_script
{
    public player_countdown()
    {
    }
    public int OnInitialize(obj_id self)
    {
        detachScript(self, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
        return SCRIPT_CONTINUE;
    }
    public int OnDetach(obj_id self)
    {
        removeObjVar(self, sui.COUNTDOWNTIMER_SUI_VAR);
        utils.removeScriptVarTree(self, sui.COUNTDOWNTIMER_VAR);
        int channelBuff = buff.getBuffOnTargetFromGroup(self, "channeled");
        if (channelBuff != 0)
        {
            buff.removeBuff(self, channelBuff);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnEnteredCombat(obj_id self)
    {
        if (sui.hasEventFlag(self, sui.CD_EVENT_COMBAT))
        {
            sui.cancelCountdownTimer(self, sui.CD_EVENT_COMBAT);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnLocomotionChanged(obj_id self, int newLocomotion, int oldLocomotion)
    {
        if (newLocomotion != LOCOMOTION_STANDING && newLocomotion != LOCOMOTION_KNEELING && newLocomotion != LOCOMOTION_PRONE)
        {
            if (sui.hasEventFlag(self, sui.CD_EVENT_LOCOMOTION))
            {
                sui.cancelCountdownTimer(self, sui.CD_EVENT_LOCOMOTION);
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnChangedPosture(obj_id self, int before, int after)
    {
        if (sui.hasEventFlag(self, sui.CD_EVENT_POSTURE))
        {
            sui.cancelCountdownTimer(self, sui.CD_EVENT_POSTURE);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnIncapacitated(obj_id self, obj_id killer)
    {
        if (sui.hasEventFlag(self, sui.CD_EVENT_INCAPACITATE))
        {
            sui.cancelCountdownTimer(self, sui.CD_EVENT_INCAPACITATE);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnCreatureDamaged(obj_id self, obj_id attacker, obj_id weapon, int[] damage)
    {
        if (sui.hasEventFlag(self, sui.CD_EVENT_DAMAGED))
        {
            sui.cancelCountdownTimer(self, sui.CD_EVENT_DAMAGED);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleCountdownTimerCleanup(obj_id self, dictionary params)
    {
        if (!hasObjVar(self, sui.COUNTDOWNTIMER_SUI_VAR))
        {
            detachScript(self, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
            return SCRIPT_CONTINUE;
        }
        int pid = params.getInt("id");
        int test_pid = getIntObjVar(self, sui.COUNTDOWNTIMER_SUI_VAR);
        if (pid == test_pid)
        {
            detachScript(self, sui.COUNTDOWNTIMER_PLAYER_SCRIPT);
            forceCloseSUIPage(pid);
        }
        return SCRIPT_CONTINUE;
    }
}
