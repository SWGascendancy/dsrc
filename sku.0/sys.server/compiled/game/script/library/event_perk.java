package script.library;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.buff;
import script.library.ai_lib;
import script.library.camping;
import script.library.locations;
import script.library.city;
import script.library.battlefield;
import script.library.player_structure;

public class event_perk extends script.base_script
{
    public event_perk()
    {
    }
    public static final String HALLOWEEN = new String("event/halloween");
    public static final string_id STEALTHED = new string_id(HALLOWEEN, "stealthed");
    public static final string_id TWELVE_COINS = new string_id(HALLOWEEN, "twelve_coins");
    public static final string_id TEN_COINS = new string_id(HALLOWEEN, "ten_coins");
    public static final string_id SIX_COINS = new string_id(HALLOWEEN, "six_coins");
    public static final string_id FIVE_COINS = new string_id(HALLOWEEN, "five_coins");
    public static final string_id STATIC_NPC = new string_id(HALLOWEEN, "static_npc");
    public static final string_id REACHED_LIMIT = new string_id(HALLOWEEN, "reached_limit");
    public static final string_id ZOZ = new string_id(HALLOWEEN, "zozpheratu");
    public static final String COUNTER = new String("galacticCoinCounter.numberOfCoins");
    public static final String COUNTER_TIMESTAMP = new String("galacticCoinCounter.timeStamp");
    public static final String COUNTER_RESTARTTIME = new String("galacticCoinCounter.startTime");
    public static final int COIN_LIMIT = utils.getIntConfigSetting("GameServer", "halloweenCoinLimit");
    public static final int COIN_AMOUNT_LOW = 5;
    public static final int COIN_AMOUNT_LOW_BONUS = 6;
    public static final int COIN_AMOUNT_HIGH = 10;
    public static final int COIN_AMOUNT_HIGH_BONUS = 12;
    public static final int LOCKOUT_LENGTH = 240;
    public static final String LIST_VAR = new String("galacticMoonNpcList");
    public static final string_id TOO_SOON = new string_id(HALLOWEEN, "too_soon");
    public static final string_id SHAPECHANGE_SPACE = new string_id("spam", "shapechange_space");
    public static final String DATATABLE = "datatables/event_perk/perk_data.iff";
    public static final String STF_FILE = "event_perk";
    public static final String PONDA_BABA_ARM_BUFF = "scary_halloween_hand";
    public static boolean canPlaceEventPerkHere(obj_id self, obj_id player, location here)
    {
        if (!isIdValid(player) || here == null)
        {
            return false;
        }
        if (isIdValid(here.cell))
        {
            sendSystemMessage(player, new string_id(STF_FILE, "not_inside"));
            return false;
        }
        if (here.area.startsWith("space_"))
        {
            sendSystemMessage(player, new string_id(STF_FILE, "not_in_space"));
            return false;
        }
        region bf = battlefield.getBattlefield(here);
        if (bf != null)
        {
            sendSystemMessage(player, new string_id(STF_FILE, "not_in_battlefield"));
            return false;
        }
        if (locations.isInMissionCity(here))
        {
            int city_id = getCityAtLocation(here, 0);
            if (city_id == 0)
            {
                sendSystemMessage(player, new string_id(STF_FILE, "not_in_municipal_zone"));
                return false;
            }
            if (cityExists(city_id) && city.isCityZoned(city_id) && isIdValid(player))
            {
                if (!city.hasZoningRights(player, city_id))
                {
                    sendSystemMessage(player, new string_id(STF_FILE, "no_zoning_rights"));
                    return false;
                }
            }
        }
        if (tooCloseToSomething(here, player, self))
        {
            return false;
        }
        if (tooManyPerks(here, player))
        {
            return false;
        }
        return true;
    }
    public static boolean tooCloseToSomething(location here, obj_id player, obj_id self)
    {
        if (here == null || !isIdValid(player))
        {
            return true;
        }
        int numEventObjects = 0;
        int deedNumber = getIntObjVar(self, "event_perk.deedNumber");
        float range = dataTableGetFloat(DATATABLE, deedNumber, "RANGE");
        if (range < 0)
        {
            return false;
        }
        obj_id[] objs = getNonCreaturesInRange(here, range);
        if ((objs == null) || (objs.length == 0))
        {
            return false;
        }
        else 
        {
            sendSystemMessage(player, new string_id(STF_FILE, "too_close_something"));
            return true;
        }
    }
    public static boolean tooManyPerks(location here, obj_id player)
    {
        int numPerkObjects = 0;
        float range = 256;
        obj_id[] objNPCs = getNPCsInRange(here, range);
        obj_id[] objs = getNonCreaturesInRange(here, range);
        if ((objs == null || objs.length == 0) && (objNPCs == null || objNPCs.length == 0))
        {
            return false;
        }
        else 
        {
            for (int i = 0; i < objs.length; i++)
            {
                obj_id item = objs[i];
                if (hasObjVar(item, "event_perk.lifeSpan"))
                {
                    numPerkObjects++;
                    if (numPerkObjects > 25)
                    {
                        sendSystemMessage(player, new string_id(STF_FILE, "too_many_perks"));
                        return true;
                    }
                }
            }
            for (int i = 0; i < objNPCs.length; i++)
            {
                obj_id item = objNPCs[i];
                if (hasObjVar(item, "event_perk.lifeSpan"))
                {
                    numPerkObjects++;
                    if (numPerkObjects > 25)
                    {
                        sendSystemMessage(player, new string_id(STF_FILE, "too_many_perks"));
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean canCallShuttle(obj_id self, obj_id player)
    {
        if (hasObjVar(self, "event_perk.shuttle.shuttle"))
        {
            sendSystemMessage(player, new string_id(STF_FILE, "already_have_shuttle"));
            return false;
        }
        else 
        {
            return true;
        }
    }
    public static void increaseDailyCoinCounter(obj_id player)
    {
        if (!hasObjVar(player, COUNTER))
        {
            setObjVar(player, COUNTER, COIN_AMOUNT_LOW);
        }
        else 
        {
            int currentCoinLimit = getIntObjVar(player, COUNTER);
            int newCoinLimit = currentCoinLimit + COIN_AMOUNT_LOW;
            setObjVar(player, COUNTER, newCoinLimit);
            if (newCoinLimit > COIN_LIMIT)
            {
                buff.applyBuff(player, "event_halloween_coin_limit");
            }
        }
    }
    public static void playerLaugh(obj_id player)
    {
        if (buff.hasBuff(player, "event_halloween_costume_jawa"))
        {
            play2dNonLoopingSound(player, "sound/utinni.snd");
        }
        if (buff.hasBuff(player, "event_halloween_costume_droid"))
        {
            play2dNonLoopingSound(player, "sound/voc_dro_ig106_emote.snd");
        }
        if (buff.hasBuff(player, "event_halloween_costume_kowakian"))
        {
            play2dNonLoopingSound(player, "sound/voc_kowakian_laugh.snd");
        }
        if (buff.hasBuff(player, "event_halloween_costume_hutt_female"))
        {
            play2dNonLoopingSound(player, "sound/halloween_jabba.snd");
        }
        if (buff.hasBuff(player, "event_halloween_costume_toydarian"))
        {
            play2dNonLoopingSound(player, "sound/halloween_toydarian_laugh.snd");
        }
    }
    private static string_id getCoinStringId(int quantity)
    {
        switch (quantity)
        {
            case COIN_AMOUNT_LOW:
                return FIVE_COINS;
            case COIN_AMOUNT_LOW_BONUS:
                return SIX_COINS;
            case COIN_AMOUNT_HIGH:
                return TEN_COINS;
            case COIN_AMOUNT_HIGH_BONUS:
                return TWELVE_COINS;
            default:
                return new string_id("", "");
        }
    }
    public static void giveTreat(obj_id player, boolean bonus)
    {
        obj_id pInv = utils.getInventoryContainer(player);
        obj_id halloweenCoins = utils.getStaticItemInInventory(player, "item_event_halloween_coin");
        if (bonus)
        {
            int coinQuantity = buff.hasBuff(player, PONDA_BABA_ARM_BUFF) ? COIN_AMOUNT_LOW_BONUS : COIN_AMOUNT_LOW;
            if (isIdValid(halloweenCoins))
            {
                int currentCoins = getCount(halloweenCoins);
                setCount(halloweenCoins, currentCoins + coinQuantity);
                increaseDailyCoinCounter(player);
            }
            else 
            {
                obj_id coins = static_item.createNewItemFunction("item_event_halloween_coin", pInv, coinQuantity);
                increaseDailyCoinCounter(player);
            }
            playerLaugh(player);
            sendSystemMessage(player, getCoinStringId(coinQuantity));
        }
        else
        {
            int coinQuantity = buff.hasBuff(player, PONDA_BABA_ARM_BUFF) ? COIN_AMOUNT_HIGH_BONUS : COIN_AMOUNT_HIGH;
            if (isIdValid(halloweenCoins))
            {
                int currentCoins = getCount(halloweenCoins);
                setCount(halloweenCoins, currentCoins + coinQuantity);
                increaseDailyCoinCounter(player);
            }
            else 
            {
                obj_id coins = static_item.createNewItemFunction("item_event_halloween_coin", pInv, coinQuantity);
                increaseDailyCoinCounter(player);
            }
            playerLaugh(player);
            sendSystemMessage(player, getCoinStringId(coinQuantity));
        }
        if (!hasObjVar(player, event_perk.COUNTER_RESTARTTIME))
        {
            int now = getCalendarTime();
            int secondsUntil = secondsUntilNextDailyTime(10, 0, 0);
            int then = now + secondsUntil;
            setObjVar(player, event_perk.COUNTER_RESTARTTIME, then);
        }
    }
    public static void handlePayout(obj_id player, obj_id npc)
    {
        int trickTreatDialogue = rand(1, 5);
        int trickOrTreat = rand(1, 100);
        if (trickOrTreat < 51)
        {
            faceTo(npc, player);
            doAnimationAction(npc, "shiver");
            chat.chat(npc, player, chat.CHAT_STUTTER, new string_id(HALLOWEEN, "treat_" + trickTreatDialogue), chat.ChatFlag_targetOnly);
            buff.applyBuff(npc, "event_halloween_trick_cooldown");
            giveTreat(player, rand(1, 5) == 1);
        }
        else 
        {
            faceTo(npc, player);
            doAnimationAction(npc, "laugh");
            chat.chat(npc, player, chat.CHAT_SAY, new string_id(HALLOWEEN, "trick_" + trickTreatDialogue), chat.ChatFlag_targetOnly);
            buff.applyBuff(npc, "event_halloween_trick_cooldown");
            utils.setScriptVar(npc, "readyForTrickFromPlayer." + player, player);
        }
    }
    public static boolean newDayOrNot(obj_id player)
    {
        if (hasObjVar(player, event_perk.COUNTER_TIMESTAMP))
        {
            int now = getCalendarTime();
            int then = getIntObjVar(player, event_perk.COUNTER_TIMESTAMP);
            if (now > then)
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        if (hasObjVar(player, event_perk.COUNTER_RESTARTTIME))
        {
            int now = getCalendarTime();
            int then = getIntObjVar(player, event_perk.COUNTER_RESTARTTIME);
            if (now > then)
            {
                return true;
            }
            else 
            {
                return false;
            }
        }
        return true;
    }
    public static boolean timeStampCheck(obj_id player)
    {
        if (newDayOrNot(player))
        {
            if (buff.hasBuff(player, "event_halloween_coin_limit"))
            {
                buff.removeBuff(player, "event_halloween_coin_limit");
                if (hasObjVar(player, event_perk.COUNTER))
                {
                    removeObjVar(player, event_perk.COUNTER);
                }
                if (hasObjVar(player, event_perk.COUNTER_TIMESTAMP))
                {
                    removeObjVar(player, event_perk.COUNTER_TIMESTAMP);
                }
                if (hasObjVar(player, event_perk.COUNTER_RESTARTTIME))
                {
                    removeObjVar(player, event_perk.COUNTER_RESTARTTIME);
                }
                return true;
            }
            else if (hasObjVar(player, event_perk.COUNTER))
            {
                if (hasObjVar(player, event_perk.COUNTER_RESTARTTIME))
                {
                    int now = getCalendarTime();
                    int then = getIntObjVar(player, event_perk.COUNTER_RESTARTTIME);
                    if (now > then)
                    {
                        removeObjVar(player, event_perk.COUNTER_RESTARTTIME);
                        removeObjVar(player, event_perk.COUNTER);
                        return true;
                    }
                }
                int currentCoinCount = getIntObjVar(player, event_perk.COUNTER);
                if (currentCoinCount > COIN_LIMIT)
                {
                    buff.applyBuff(player, "event_halloween_coin_limit");
                    sendSystemMessage(player, event_perk.REACHED_LIMIT);
                    return false;
                }
            }
            return true;
        }
        if (!newDayOrNot(player))
        {
            if (hasObjVar(player, event_perk.COUNTER))
            {
                int currentCoinCount = getIntObjVar(player, event_perk.COUNTER);
                if (currentCoinCount > COIN_LIMIT)
                {
                    if (!buff.hasBuff(player, "event_halloween_coin_limit"))
                    {
                        sendSystemMessage(player, event_perk.REACHED_LIMIT);
                        buff.applyBuff(player, "event_halloween_coin_limit");
                        return false;
                    }
                    sendSystemMessage(player, event_perk.REACHED_LIMIT);
                    return false;
                }
            }
            return true;
        }
        sendSystemMessage(player, event_perk.REACHED_LIMIT);
        if (!buff.hasBuff(player, "event_halloween_coin_limit"))
        {
            buff.applyBuff(player, "event_halloween_coin_limit");
        }
        return false;
    }
}
