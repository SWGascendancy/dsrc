package script.theme_park.warren;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.sui;

public class inquisitor_letter extends script.base_script
{
    public inquisitor_letter()
    {
    }
    public static final String SYSTEM_MESSAGES = "theme_park/warren/warren_system_messages";
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        sui.msgbox(player, new string_id(SYSTEM_MESSAGES, "inq_letter_text"));
        return SCRIPT_OVERRIDE;
    }
}
