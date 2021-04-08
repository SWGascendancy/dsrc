package script.library;

import script.dictionary;
import script.obj_id;

public class sequencer extends script.base_script
{
    public sequencer()
    {
    }
    public static final int STOP_SEQUENCE = -1;
    public static final int CHANGE_TABLE = -2;
    public static obj_id getSequenceObject(String strIdentifier)
    {
        return getSequenceObject(getSelf(), strIdentifier);
    }
    public static obj_id getSequenceObject(obj_id self, String strIdentifier)
    {
        obj_id objMaster = getMasterSequenceObject(self);
        if (!isIdValid(objMaster))
        {
            return null;
        }
        return utils.getObjIdScriptVar(objMaster, strIdentifier);
    }
    public static boolean registerSequenceObject(obj_id objObject, String strIdentifier)
    {
        obj_id objMaster = getMasterSequenceObject(objObject);
        if (!isIdValid(objMaster))
        {
            return false;
        }
        utils.setScriptVar(objObject, "objMasterSequenceObject", objMaster);
        utils.setScriptVar(objMaster, strIdentifier, objObject);
        return true;
    }
    public static boolean registerSequenceObject(obj_id objObject)
    {
        if (hasObjVar(objObject, "strSequenceIdentifier"))
        {
            return registerSequenceObject(objObject, getStringObjVar(objObject, "strSequenceIdentifier"));
        }
        else 
        {
            return false;
        }
    }
    public static boolean cleanUpSequenceObject(obj_id objObject)
    {
        if (hasObjVar(objObject, "strSequenceIdentifier"))
        {
            return cleanUpSequenceObject(objObject, getStringObjVar(objObject, "strSequenceIdentifier"));
        }
        else 
        {
            return false;
        }
    }
    public static boolean cleanUpSequenceObject(obj_id objObject, String strIdentifier)
    {
        return utils.removeScriptVar(getMasterSequenceObject(objObject), strIdentifier);
    }
    public static obj_id getMasterSequenceObject(obj_id objObject)
    {
        if (utils.hasScriptVar(objObject, "objMasterSequencerObject"))
        {
            return utils.getObjIdScriptVar(objObject, "objMasterSequenceObject");
        }
        obj_id objMaster = getTopMostContainer(objObject);
        LOG("npe", "objMaster is " + objMaster);
        if (!isIdValid(objMaster))
        {
            return objObject;
        }
        return objMaster;
    }
    public static boolean isValidSequenceIdentifier(String strIdentifier) {
        obj_id objMaster = getMasterSequenceObject(getSelf());
        return isIdValid(objMaster) && utils.hasScriptVar(objMaster, strIdentifier);
    }
    public static void walkToSequenceObject(obj_id objNPC, obj_id objSeq)
    {
        pathTo(objNPC, getLocation(objSeq));
    }
    public static void walkToSequenceObject(obj_id objNPC, String strObject)
    {
        walkToSequenceObject(objNPC, getSequenceObject(strObject));
    }
    public static void runToSequenceObject(obj_id objNPC, obj_id objSeq)
    {
        setMovementRun(objNPC);
        pathTo(objNPC, getLocation(objSeq));
    }
    public static void runToSequenceObject(obj_id objNPC, String strObject)
    {
        runToSequenceObject(objNPC, getSequenceObject(strObject));
    }
    public static void doCombatAnimation(obj_id objNPC, obj_id objSeq, String strAnimation)
    {
        if (isDead(objNPC))
        {
            return;
        }
        if (!exists(objNPC))
        {
            return;
        }
        if (isIdValid(objNPC) && isIdValid(objSeq))
        {
            attacker_results cbtAttackerResults = makeDummyAttackerResults(objNPC);
            defender_results[] cbtDefenderResults = makeDummyDefenderResults(objSeq);
            if (!isIdValid(cbtAttackerResults.id) || !isIdValid(cbtDefenderResults[0].id))
            {
                return;
            }
            doCombatResults(strAnimation, cbtAttackerResults, cbtDefenderResults);
        }
    }
    public static void doCombatAnimation(obj_id objNPC, String strObject, String strAnimation)
    {
        doCombatAnimation(getSequenceObject(strObject), objNPC, strAnimation);
    }
    public static void faceToSequenceObject(obj_id objNPC, String strObject)
    {
        faceTo(objNPC, getSequenceObject(strObject));
    }
    public static attacker_results makeDummyAttackerResults(obj_id objAttacker)
    {
        attacker_results cbtAttackerResults = new attacker_results();
        cbtAttackerResults.id = objAttacker;
        cbtAttackerResults.weapon = getCurrentWeapon(objAttacker);
        LOG("npe", "weapon is " + getCurrentWeapon(objAttacker));
        cbtAttackerResults.endPosture = getPosture(objAttacker);
        return cbtAttackerResults;
    }
    public static defender_results[] makeDummyDefenderResults(obj_id objDefender)
    {
        defender_results[] cbtDefenderResults = new defender_results[1];
        cbtDefenderResults[0] = new defender_results();
        cbtDefenderResults[0].id = objDefender;
        cbtDefenderResults[0].endPosture = 0;
        cbtDefenderResults[0].result = COMBAT_RESULT_HIT;
        cbtDefenderResults[0].damageAmount = 0;
        cbtDefenderResults[0].hitLocation = 0;
        return cbtDefenderResults;
    }
    public static defender_results[] makeDummyMissDefenderResults(obj_id objDefender)
    {
        defender_results[] cbtDefenderResults = new defender_results[1];
        cbtDefenderResults[0] = new defender_results();
        cbtDefenderResults[0].id = objDefender;
        cbtDefenderResults[0].endPosture = 0;
        cbtDefenderResults[0].result = COMBAT_RESULT_MISS;
        cbtDefenderResults[0].damageAmount = 0;
        cbtDefenderResults[0].hitLocation = 0;
        return cbtDefenderResults;
    }
    public static void continueEventSequence(String strContinueTag)
    {
        dictionary dctParams = new dictionary();
        dctParams.put("strContinueTag", strContinueTag);
        messageTo(getMasterSequenceObject(getSelf()), "doEvents", dctParams, 0, false);
    }
    public static void stopSequence(obj_id objTarget)
    {
        messageTo(objTarget, "interruptSequence", null, 0, false);
    }
}
