package script.quest.task;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.quests;
import script.library.utils;
import script.library.prose;
import script.library.xp;

public class destroy_multi extends script.base_script
{
    public destroy_multi()
    {
    }
    public int receiveCreditForKill(obj_id self, dictionary params)
    {
        String targetType = params.getString("creatureName");
        location creatureLoc = params.getLocation("location");
        if (getDistance(getLocation(self), creatureLoc) > xp.MAX_DISTANCE)
        {
            return SCRIPT_CONTINUE;
        }
        if (targetType != null && targetType.length() > 0)
        {
            String objvarName = "quest.destroy_multi." + targetType;
            if (hasObjVar(self, objvarName))
            {
                String[] questNames = getStringArrayObjVar(self, objvarName);
                if (questNames != null && questNames.length > 0)
                {
                    int iter = 0;
                    for (iter = 0; iter < questNames.length; ++iter)
                    {
                        String questName = questNames[iter];
                        if (quests.isActive(questName, self))
                        {
                            objvarName = "quest." + questName + ".parameter";
                            if (hasObjVar(self, objvarName))
                            {
                                int killsRemaining = getIntObjVar(self, objvarName);
                                killsRemaining--;
                                LOG("newquests", "destroy_multi: onReceiveCreditForKill: kills remaining = " + killsRemaining);
                                if (killsRemaining < 1)
                                {
                                    String[] newQuestNames = new String[questNames.length - 1];
                                    int ni = 0;
                                    int index = 0;
                                    for (ni = 0; ni < questNames.length; ++ni)
                                    {
                                        if (ni != iter)
                                        {
                                            newQuestNames[index] = questNames[ni];
                                            ++index;
                                        }
                                    }
                                    if (newQuestNames.length < 1)
                                    {
                                        objvarName = "quest.destroy_multi." + targetType;
                                        removeObjVar(self, objvarName);
                                    }
                                    quests.complete(questName, self, true);
                                }
                                else 
                                {
                                    objvarName = "quest." + questName + ".parameter";
                                    setObjVar(self, objvarName, killsRemaining);
                                    string_id message = new string_id("quest/quests", "kill_credit");
                                    String summary = quests.getDataEntry(questName, "JOURNAL_ENTRY_SUMMARY");
                                    prose_package pp = prose.getPackage(message, self, self, summary, killsRemaining);
                                    sendSystemMessageProse(self, pp);
                                }
                                break;
                            }
                        }
                    }
                }
                else 
                {
                    LOG("newquests", "destroy_multi: onReceiveCreditForKill: questNames objvar \"" + objvarName + "\" string array is empty");
                }
            }
            else 
            {
                LOG("newquests", "destroy_multi: onReceiveCreditForKill: could not retrieve objvar " + objvarName);
            }
        }
        else 
        {
            LOG("newquests", "destroy_multi: onReceiveCreditForKill: could not determine target type name from target creature");
        }
        return SCRIPT_CONTINUE;
    }
    public int OnQuestActivated(obj_id self, int questRow)
    {
        if (quests.isMyQuest(questRow, "quest.task.destroy_multi"))
        {
            String questName = quests.getDataEntry(questRow, "NAME");
            LOG("newquests", "OnQuestActivated(" + questName + ")");
            String target = null;
            String objvarName = "quest." + questName + ".target";
            if (hasObjVar(self, objvarName))
            {
                target = getStringObjVar(self, objvarName);
            }
            else 
            {
                target = quests.getDataEntry(questRow, "TARGET");
            }
            int killCount = 0;
            if (target != null && target.length() > 0)
            {
                setObjVar(self, objvarName, target);
                objvarName = "quest." + questName + ".parameter";
                if (hasObjVar(self, objvarName))
                {
                    killCount = getIntObjVar(self, objvarName);
                }
                else 
                {
                    String countName = quests.getDataEntry(questRow, "PARAMETER");
                    if (countName != null && countName.length() > 0)
                    {
                        killCount = utils.stringToInt(countName);
                    }
                }
                if (killCount > 0)
                {
                    setObjVar(self, objvarName, killCount);
                    objvarName = "quest.destroy_multi." + target;
                    String[] questNames = getStringArrayObjVar(self, objvarName);
                    String[] newQuestNames = null;
                    if (questNames == null)
                    {
                        newQuestNames = new String[1];
                        newQuestNames[0] = questName;
                    }
                    else 
                    {
                        newQuestNames = new String[questNames.length + 1];
                        int iter = 0;
                        for (iter = 0; iter < questNames.length; ++iter)
                        {
                            newQuestNames[iter] = questNames[iter];
                        }
                        newQuestNames[questNames.length] = questName;
                    }
                    if (newQuestNames != null && newQuestNames.length > 0)
                    {
                        setObjVar(self, objvarName, newQuestNames);
                    }
                    else 
                    {
                        LOG("newquests", "destroy_multi: OnQuestActivated: Could not set questnames string array objvar");
                    }
                }
                else 
                {
                    LOG("newquests", "destroy_multi: OnQuestActivated: could not determine kill count");
                }
            }
            else 
            {
                LOG("newquests", "destroy_multi: OnQuestActivated: could not determine target creature type");
            }
        }
        return SCRIPT_CONTINUE;
    }
}
