package script.fishing;

import script.dictionary;
import script.library.minigame;
import script.obj_id;

public class npc extends script.base_script
{
    public npc()
    {
    }
    public int OnDetach(obj_id self)
    {
        minigame.cleanupFishing(self);
        return SCRIPT_CONTINUE;
    }
    public int OnEnteredCombat(obj_id self)
    {
        minigame.stopFishing(self);
        return SCRIPT_CONTINUE;
    }
    public int handleFishingTick(obj_id self, dictionary params)
    {
        return SCRIPT_CONTINUE;
    }
    public int handlePlayCastSplash(obj_id self, dictionary params)
    {
        minigame.playCastSplash(self, params);
        messageTo(self, minigame.HANDLER_FISHING_TICK, null, minigame.FISHING_TICK, false);
        return SCRIPT_CONTINUE;
    }
}
