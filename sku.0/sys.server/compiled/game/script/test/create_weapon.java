package script.test;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.utils;
import script.library.craftinglib;
import script.library.weapons;

public class create_weapon extends script.base_script
{
    public create_weapon()
    {
    }
    public void makeResource(obj_id self, String rclass)
    {
        obj_id[] rtypes = getResourceTypes(rclass);
        sendSystemMessageTestingOnly(self, "Types are..." + rtypes[0].toString());
        obj_id rtype = rtypes[0];
        if (!isIdValid(rtype))
        {
            sendSystemMessageTestingOnly(self, "No id found");
            sendSystemMessageTestingOnly(self, "Type was " + rclass);
            return;
        }
        String crateTemplate = getResourceContainerForType(rtype);
        if (!crateTemplate.equals(""))
        {
            obj_id pInv = utils.getInventoryContainer(self);
            if (isIdValid(pInv))
            {
                obj_id crate = createObject(crateTemplate, pInv, "");
                if (addResourceToContainer(crate, rtype, 100000, self))
                {
                    sendSystemMessageTestingOnly(self, "Resource of class " + rclass + " added");
                }
            }
        }
    }
    public int OnSpeaking(obj_id self, String strText)
    {
        String[] strCommands = split(strText, ' ');
        if (strCommands[0].equals("cwep"))
        {
            obj_id pInv = utils.getInventoryContainer(self);
            String type = "all";
            if (strCommands.length > 1)
            {
                type = strCommands[1];
            }
            weapons.createOneOfEach(type, pInv, weapons.VIA_TEMPLATE, 1.0f);
        }
        return SCRIPT_CONTINUE;
    }
}
