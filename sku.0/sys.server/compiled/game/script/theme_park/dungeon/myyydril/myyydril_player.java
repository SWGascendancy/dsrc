package script.theme_park.dungeon.myyydril;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;

public class myyydril_player extends script.base_script
{
    public myyydril_player()
    {
    }
    public static final boolean LOGGING = false;
    public int OnLogin(obj_id self)
    {
        doLogging("OnLogin", "OnLogin Fired, validating location");
        messageTo(self, "validateLocation", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int OnTransferred(obj_id self, obj_id sourceContainer, obj_id destContainer, obj_id transferer)
    {
        doLogging("OnTransferred", "OnTransferred Fired, validating location");
        messageTo(self, "validateLocation", null, 2, false);
        return SCRIPT_CONTINUE;
    }
    public int validateLocation(obj_id self, dictionary params)
    {
        obj_id container = getContainedBy(self);
        doLogging("validateLocation", "I am contained by(" + container + ")");
        if (!isIdValid(container))
        {
            String scene = getCurrentSceneName();
            doLogging("validateLocation", "Current scene name is(" + scene + ")");
            if (scene.equals("kashyyyk_pob_dungeons"))
            {
                doLogging("validateLocation", "I am uncontained in POB Dungeons. Warping to Dead Forest");
                warpPlayer(self, "kashyyyk_dead_forest", -205, 38, 301, null, 0, 0, 0);
            }
            else 
            {
                doLogging("validateLocation", "I was uncontained not on POB Dungeons. Removing script");
                detachScript(self, "theme_park.dungeon.myyydril.myyydril_player");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnUnsticking(obj_id self)
    {
        warpPlayer(self, "kashyyyk_dead_forest", -205, 38, 301, null, 0, 0, 0);
        return SCRIPT_OVERRIDE;
    }
    public int cmdSetGrievousAuthorized(obj_id self, obj_id target, String params, float defaultTime)
    {
        if (isGod(self))
        {
            doLogging("cmdSetGrievousAuthorized", "God mode user(" + getName(self) + "/" + self + ") requsted to be made grievous authoratative");
            utils.setScriptVar(self, "grievous_lock.god_authorized", true);
        }
        return SCRIPT_CONTINUE;
    }
    public void doLogging(String section, String message)
    {
        if (LOGGING)
        {
            LOG("logging/myyydril_player/" + section, message);
        }
    }
}
