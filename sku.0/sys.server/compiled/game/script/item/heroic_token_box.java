package script.item;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;

import script.library.static_item;
import script.library.sui;
import script.library.trial;
import script.library.utils;

public class heroic_token_box extends script.base_script
{
    public heroic_token_box()
    {
    }
    private static final string_id MNU_WITHDRAW = new string_id("token_box", "withdraw");
    private static final String[] TOKEN_OPTIONS = {
        "Axkva Min",
        "Tusken King",
        "IG-88",
        "Black Sun",
        "Exar Kun",
        "Echo Base",
        "Rebel Massassi Isle Battlefield Token",
        "Imperial Massassi Isle Battlefield Token",
        "Rebel Jungle Warfare Battlefield Token",
        "Imperial Jungle Warfare Battlefield Token",
        "Rebel Bunker Assault Battlefield Token",
        "Imperial Bunker Assault Battlefield Token",
        "Rebel Data Runner Battlefield Token",
        "Imperial Data Runner Battlefield Token",
        "Copper Chronicles Token",
        "Silver Chronicles Token",
        "Gold Chronicles Token",
        "Rebel Alliance Galactic Civil War Token",
        "Imperial Alliance Galactic Civil War Token",
        "A Duty Mission Token",
        "Dathomir Amber",
        "Spider Silk",
        "Rancor Teeth",
        "Whuffa Leather",
        "Rare Dried Herbs",
        "Veteran Reward",
        "Imperial Station Token",
        "Rebel Station Token"
      //  "Ascendancy Token",
      //  "Black Market Token"
    };
    private static final byte[] TOKENS = {
        0,
        1,
        2,
        3,
        4,
        5,
        6,
        7,
        8,
        9,
        10,
        11,
        12,
        13,
        14,
        15,
        16,
        17,
        18,
        19,
        20,
        21,
        22,
        23,
        24,
        25,
        26,
        27
      //  28,
      //  29
    };
    public int OnAttach(obj_id self)
    {
        if (!hasObjVar(self, "item.set.tokens_held"))
        {
            trial.initializeBox(self);
        }
        else
        {
            trial.verifyBox(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnInitialize(obj_id self)
    {
        if (!hasObjVar(self, "item.set.tokens_held"))
        {
            trial.initializeBox(self);
        }
        else
        {
            trial.verifyBox(self);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnGetAttributes(obj_id self, obj_id player, String[] names, String[] attribs)
    {
        int free = getFirstFreeIndex(names);
        if (free == -1)
        {
            return SCRIPT_CONTINUE;
        }
        if (!hasObjVar(self, "item.set.tokens_held"))
        {
            return SCRIPT_CONTINUE;
        }
        int[] tokenTypes = getIntArrayObjVar(self, "item.set.tokens_held");
        if (tokenTypes.length == trial.NUM_HEROIC_TOKEN_TYPES)
        {
            for (int i = 0; i < tokenTypes.length; i++)
            {
                names[free] = utils.packStringId(new string_id("static_item_n", trial.HEROIC_TOKENS[i]));
                attribs[free++] = "" + tokenTypes[i];
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuRequest(obj_id self, obj_id player, menu_info mi)
    {
        if (utils.isNestedWithin(self, player))
        {
            mi.addRootMenu(menu_info_types.SERVER_MENU1, MNU_WITHDRAW);
        }
        return SCRIPT_CONTINUE;
    }
    public int OnObjectMenuSelect(obj_id self, obj_id player, int item)
    {
        if (utils.isNestedWithin(self, player) && item == menu_info_types.SERVER_MENU1)
        {
            String title = "Heroic Token Box";
            String prompt = "Please select the token that you would like to withdraw.";
            int pid = sui.listbox(self, player, prompt, sui.OK_CANCEL, title, TOKEN_OPTIONS, "handleOptionSelect", true, false);
        }
        return SCRIPT_CONTINUE;
    }
    public int handleOptionSelect(obj_id self, dictionary params) {
        int bp = sui.getIntButtonPressed(params);
        if (bp == sui.BP_CANCEL)
        {
            return SCRIPT_CONTINUE;
        }
        int idx = sui.getListboxSelectedRow(params);
        if (idx == -1 || idx > TOKEN_OPTIONS.length)
        {
            return SCRIPT_CONTINUE;
        }
        setObjVar(self, "tokenType", idx);
        String title = "Heroic Token Box";
        String prompt = "Please select how many tokens you want to withdraw from your Token Box.";
        sui.filteredInputbox(self, sui.getPlayerId(params), prompt, title, "handleQuantitySelect", "");
        return SCRIPT_CONTINUE;
    }
    public int handleQuantitySelect(obj_id self, dictionary params) {
        obj_id player = utils.getContainingPlayer(self);
        obj_id tokenBox = utils.getObjectInInventory(player, trial.TOKEN_BOX);

        String input = sui.getInputBoxText(params);
        if (input == null || input.equals(""))
        {
            return SCRIPT_CONTINUE;
        }
        int amount = Integer.parseInt(input);
        int selectedRow = getIntObjVar(self, "tokenType");
        String tokenType = trial.HEROIC_TOKENS[TOKENS[selectedRow]];
        int tokensInBox = trial.getTokenAmountInBox(tokenBox, tokenType);
        if (amount < 1 || amount > tokensInBox)
        {
            sendSystemMessageTestingOnly(player, "You cannot withdraw " + amount + " tokens, you only have " + tokensInBox + " in your token box.");
        }
        else
        {
            obj_id inv = getObjectInSlot(player, "inventory");
            if (getVolumeFree(inv) > 0)
            {
                trial.withdrawTokensFromBox(tokenBox, tokenType, amount);
                static_item.createNewItemFunction(tokenType, player, amount);
            }
            else
            {
                sendSystemMessageTestingOnly(player, "Please make space in your inventory.");
            }
        }
        return SCRIPT_CONTINUE;
    }
    public int OnDestroy(obj_id self)
    {
        obj_id whoDat = getTopMostContainer(self);
        sendSystemMessage(whoDat, new string_id("spam", "can_not_destroy"));
        return SCRIPT_OVERRIDE;
    }
}
