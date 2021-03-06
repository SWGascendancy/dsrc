package script.theme_park.warren;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;

public class inquisitor_letter_maker extends script.base_script
{
    public inquisitor_letter_maker()
    {
    }
    public static final String LETTER_TEMPLATE = "object/tangible/mission/quest_item/warren_inquisitor_letter.iff";
    public static final String SYSTEM_MESSAGES = "theme_park/warren/warren_system_messages";
    public int OnInitialize(obj_id self)
    {
        utils.setScriptVar(self, "messageSent", true);
        messageTo(self, "respawnLetter", null, 1, false);
        return SCRIPT_CONTINUE;
    }
    public void spawnLetter(obj_id container)
    {
        obj_id[] contents = getContents(container);
        if (contents != null)
        {
            for (int i = 0; i < contents.length; i++)
            {
                if (hasObjVar(contents[i], "warren.inquisitor_letter"))
                {
                    for (int j = i + 1; j < contents.length; ++j)
                    {
                        destroyObject(contents[j]);
                    }
                }
                return;
            }
        }
        obj_id letter = createObject(LETTER_TEMPLATE, container, "");
        attachScript(letter, "theme_park.warren.inquisitor_letter");
        setObjVar(letter, "warren.inquisitor_letter", true);
        setName(letter, "");
        setName(letter, new string_id(SYSTEM_MESSAGES, "inq_letter_name"));
    }
    public int respawnLetter(obj_id self, dictionary params)
    {
        utils.removeScriptVar(self, "messageSent");
        spawnLetter(self);
        return SCRIPT_CONTINUE;
    }
    public int OnClosedContainer(obj_id self, obj_id player)
    {
        if (utils.hasScriptVar(self, "messageSent"))
        {
            return SCRIPT_CONTINUE;
        }
        obj_id[] contents = getContents(self);
        if (contents != null)
        {
            for (int i = 0; i < contents.length; i++)
            {
                if (hasObjVar(contents[i], "warren.inquisitor_letter"))
                {
                    return SCRIPT_CONTINUE;
                }
            }
        }
        messageTo(self, "respawnLetter", null, 3600, false);
        utils.setScriptVar(self, "messageSent", true);
        return SCRIPT_CONTINUE;
    }
}
