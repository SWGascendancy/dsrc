package script.quest.util;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.groundquests;

public class retrieve_item_red_glow extends script.base_script
{
    public retrieve_item_red_glow()
    {
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        location here = getLocation(player);
        location term = getLocation(self);
        float dist = getDistance(here, term);
        if (hasScript(player, "quest.task.ground.retrieve_item"))
        {
            if (groundquests.playerNeedsToRetrieveThisItem(player, self))
            {
                if (item == menu_info_types.ITEM_USE)
                {
                    if (isDead(player) || isIncapacitated(player) || dist > 5.0)
                    {
                        return SCRIPT_CONTINUE;
                    }
                    playClientEffectLoc(player, "clienteffect/item_ring_hero_mark.cef", getLocation(self), 1f);
                }
            }
        }
        return SCRIPT_CONTINUE;
    }
}
