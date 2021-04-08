package script.theme_park.poi;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.poi;
import script.ai.ai_combat;

public class base extends script.base_script
{
    public base()
    {
    }
    public static final int POI_INCOMPLETE = poi.POI_INCOMPLETE;
    public static final int POI_SUCCESS = poi.POI_SUCCESS;
    public static final int POI_FAIL = poi.POI_FAIL;
    public static final String POI_STRING_LIST = poi.POI_STRING_LIST;
    public static final String POI_STRING_LIST_INDEXNAME = poi.POI_STRING_LIST_INDEXNAME;
    public static final String POI_CREDIT_LIST = poi.POI_CREDIT_LIST;
    public static final String POI_DENY_LIST = poi.POI_DENY_LIST;
    public static final String POI_DESTROY_MESSAGE = poi.POI_DESTROY_MESSAGE;
    public static final String POI_DESTROY_MESSAGE_DELAY = poi.POI_DESTROY_MESSAGE_DELAY;
    public static final String POI_BASE_OBJECT = poi.POI_BASE_OBJECT;
    public static final String POI_NO_MESSAGE_ON_DESTROY = poi.POI_NO_MESSAGE_ON_DESTROY;
    public static final String POI_COMPLETED = poi.POI_COMPLETED;
    public static final String POI_DIFFICULTY = poi.POI_DIFFICULTY;
    public static final String POI_FACTION = poi.POI_FACTION;
    public static final String POI_FACTION_VALUE = poi.POI_FACTION_VALUE;
    public static final String POI_OBJECTIVE = poi.POI_OBJECTIVE;
    public static final String POI_OBJECT_SCRIPT = poi.POI_OBJECT_SCRIPT;
    public obj_id poiCreateObject(obj_id poiObject, String template, float x, float z)
    {
        return poi.createObject(poiObject, template, x, z);
    }
    public obj_id poiCreateObject(obj_id poiObject, String template, float x, float z, int level)
    {
        return poi.createObject(poiObject, template, x, z, level);
    }
    public obj_id poiCreateObject(String template, float x, float z)
    {
        return poi.createObject(template, x, z);
    }
    public obj_id poiCreateObject(String template, float x, float z, int level)
    {
        return poi.createObject(template, x, z, level);
    }
    public obj_id poiCreateObject(obj_id poiObject, String name, String template, float x, float z)
    {
        return poi.createObject(poiObject, name, template, x, z);
    }
    public obj_id poiCreateObject(obj_id poiObject, String name, String template, float x, float z, int level)
    {
        return poi.createObject(poiObject, name, template, x, z, level);
    }
    public obj_id poiCreateObject(obj_id poiObject, String strName, location locCenter)
    {
        return poi.createObject(poiObject, strName, locCenter);
    }
    public obj_id poiCreateObject(obj_id poiObject, String strName, location locCenter, int level)
    {
        return poi.createObject(poiObject, strName, locCenter, level);
    }
    public obj_id poiCreateObject(String name, String template, float x, float z)
    {
        return poi.createObject(name, template, x, z);
    }
    public obj_id poiCreateObject(String name, String template, float x, float z, int level)
    {
        return poi.createObject(name, template, x, z, level);
    }
    public obj_id poiCreateNpc(obj_id poiObject, String type, float x, float z)
    {
        return poi.createNpc(poiObject, type, x, z);
    }
    public obj_id poiCreateNpc(obj_id poiObject, String type, float x, float z, int level)
    {
        return poi.createNpc(poiObject, type, x, z, level);
    }
    public obj_id poiCreateNpc(String type, float x, float z)
    {
        return poi.createNpc(type, x, z);
    }
    public obj_id poiCreateNpc(String type, float x, float z, int level)
    {
        return poi.createNpc(type, x, z, level);
    }
    public obj_id poiCreateNpc(obj_id poiObject, String ident, String type, float x, float z)
    {
        return poi.createNpc(poiObject, ident, type, x, z);
    }
    public obj_id poiCreateNpc(obj_id poiObject, String ident, String type, float x, float z, int level)
    {
        return poi.createNpc(poiObject, ident, type, x, z, level);
    }
    public obj_id poiCreateNpc(String ident, String type, float x, float z)
    {
        return poi.createNpc(ident, type, x, z);
    }
    public obj_id poiCreateNpc(String ident, String type, float x, float z, int level)
    {
        return poi.createNpc(ident, type, x, z, level);
    }
    public obj_id poiFindObject(obj_id poiObject, String name)
    {
        return poi.findObject(poiObject, name);
    }
    public obj_id poiFindObject(String name)
    {
        return poi.findObject(name);
    }
    public String poiFindObjectName(obj_id poiObject)
    {
        return poi.findObjectName(poiObject);
    }
    public String poiFindObjectName()
    {
        return poi.findObjectName();
    }
    public void poiSetDestroyMessage(obj_id poiObject, String name, String messageHandlerName, int delay)
    {
        poi.setDestroyMessage(poiObject, name, messageHandlerName, delay);
    }
    public void poiSetDestroyMessage(obj_id poiObject, String name, String messageHandlerName)
    {
        poi.setDestroyMessage(poiObject, name, messageHandlerName);
    }
    public void poiSetDestroyMessage(String name, String messageHandlerName, int delay)
    {
        poi.setDestroyMessage(name, messageHandlerName, delay);
    }
    public void poiSetDestroyMessage(String name, String messageHandlerName)
    {
        poi.setDestroyMessage(name, messageHandlerName);
    }
    public void poiSetDestroyMessage(obj_id poiObject, String messageHandlerName, int delay)
    {
        poi.setDestroyMessage(poiObject, messageHandlerName, delay);
    }
    public void poiSetDestroyMessage(obj_id poiObject, String messageHandlerName)
    {
        poi.setDestroyMessage(poiObject, messageHandlerName);
    }
    public void poiSetDestroyMessage(String messageHandlerName, int delay)
    {
        poi.setDestroyMessage(messageHandlerName, delay);
    }
    public void poiSetDestroyMessage(String messageHandlerName)
    {
        poi.setDestroyMessage(messageHandlerName);
    }
    public void poiGrantCredit(obj_id poiObject, obj_id player)
    {
        poi.grantCredit(poiObject, player);
    }
    public void poiGrantCredit(obj_id player)
    {
        poi.grantCredit(player);
    }
    public void poiRemoveCredit(obj_id poiObject, obj_id player)
    {
        poi.removeCredit(poiObject, player);
    }
    public void poiRemoveCredit(obj_id player)
    {
        poi.removeCredit(player);
    }
    public void poiDenyCredit(obj_id poiObject, obj_id player)
    {
        poi.denyCredit(poiObject, player);
    }
    public void poiDenyCredit(obj_id player)
    {
        poi.denyCredit(player);
    }
    public boolean poiIsGrantedCredit(obj_id poiObject, obj_id player)
    {
        return poi.isGrantedCredit(poiObject, player);
    }
    public boolean poiIsGrantedCredit(obj_id player)
    {
        return poi.isGrantedCredit(player);
    }
    public boolean poiIsGrantedCredit(obj_id poiBaseObject, String objVarName)
    {
        return poi.isGrantedCredit(poiBaseObject, objVarName);
    }
    public boolean poiIsDeniedCredit(obj_id poiObject, obj_id player)
    {
        return poi.isDeniedCredit(poiObject, player);
    }
    public boolean poiIsDeniedCredit(obj_id player)
    {
        return poi.isDeniedCredit(player);
    }
    public boolean poiIsDeniedCredit(obj_id poiBaseObject, String objVarName)
    {
        return poi.isDeniedCredit(poiBaseObject, objVarName);
    }
    public void poiAddToStringList(obj_id element, String name)
    {
        poi.addToStringList(element, name);
    }
    public void poiAddToStringList(String name)
    {
        poi.addToStringList(name);
    }
    public void poiRemoveFromStringList(obj_id element)
    {
        poi.removeFromStringList(element);
    }
    public void poiRemoveFromStringList()
    {
        poi.removeFromStringList();
    }
    public void poiRemoveFromStringList(obj_id poiObject, String name)
    {
        poi.removeFromStringList(poiObject, name);
    }
    public void poiRemoveFromStringList(String name)
    {
        poi.removeFromStringList(name);
    }
    public void poiAddToMasterList(obj_id poiBaseObject, obj_id elementToAdd)
    {
        poi.addToMasterList(poiBaseObject, elementToAdd);
    }
    public void poiRemoveFromMasterList(obj_id poiBaseObject, obj_id elementToRemove)
    {
        poi.removeFromMasterList(poiBaseObject, elementToRemove);
    }
    public boolean poiIsInMasterList(obj_id poiBaseObject, obj_id element)
    {
        return poi.isInMasterList(poiBaseObject, element);
    }
    public obj_id poiGetBaseObject(obj_id poiObject)
    {
        return poi.getBaseObject(poiObject);
    }
    public obj_id poiGetBaseObject()
    {
        return poi.getBaseObject();
    }
    public void poiBaseObjectDestroyed(obj_id poiBaseObject)
    {
        poi.baseObjectDestroyed(poiBaseObject);
    }
    public void poiObjectDestroyed(obj_id element)
    {
        poi.objectDestroyed(element);
    }
    public String poiGetDifficulty(obj_id poiObject)
    {
        return poi.getDifficulty(poiObject);
    }
    public String poiGetDifficulty()
    {
        return poi.getDifficulty();
    }
    public int poiGetIntDifficulty()
    {
        return poi.getIntDifficulty();
    }
    public String poiGetFaction(obj_id poiObject)
    {
        return poi.getFaction(poiObject);
    }
    public String poiGetFaction()
    {
        return poi.getFaction();
    }
    public float poiGetFactionValue(obj_id poiObject)
    {
        return poi.getFactionValue(poiObject);
    }
    public float poiGetFactionValue()
    {
        return poi.getFactionValue();
    }
    public String poiGetObjective(obj_id poiObject)
    {
        return poi.getObjective(poiObject);
    }
    public String poiGetObjective()
    {
        return poi.getObjective();
    }
    public void poiSetFaction(obj_id poiObject, String faction)
    {
        poi.setFaction(poiObject, faction);
    }
    public void poiSetFaction(String faction)
    {
        poi.setFaction(faction);
    }
    public void poiSetFactionValue(obj_id poiObject, float factionValue)
    {
        poi.setFactionValue(poiObject, factionValue);
    }
    public void poiSetFactionValue(float factionValue)
    {
        poi.setFactionValue(factionValue);
    }
    public void poiComplete(obj_id poiObject, int status)
    {
        poi.complete(poiObject, status);
    }
    public void poiComplete(int status)
    {
        poi.complete(status);
    }
    public void poiComplete(obj_id poiObject)
    {
        poi.complete(poiObject);
    }
    public void poiComplete()
    {
        poi.complete();
    }
    public void poiAwardFactionStanding(obj_id poiBaseObject)
    {
        poi.awardFactionStanding(poiBaseObject);
    }
    public void poiSendMissionStatus(obj_id poiBaseObject, int status)
    {
        poi.sendMissionStatus(poiBaseObject, status);
    }
    public void poiBaseObjectRemovedFromWorld(obj_id poiBaseObject)
    {
        poi.baseObjectRemovedFromWorld(poiBaseObject);
    }
    public boolean poiIsCompleted(obj_id poiObject)
    {
        return poi.isComplete(poiObject);
    }
    public boolean poiIsCompleted()
    {
        return poi.isComplete();
    }
}
