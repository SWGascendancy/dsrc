package script.cureward;

import script.*;
import script.base_class.*;
import script.combat_engine.*;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;
import script.base_script;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import script.library.static_item;
import script.obj_id;
import script.library.utils;

public class cureward extends script.base_script {
    private static final int DAYS_ON_LAUNCH = 6086;
    private static final int VET_TOKEN_BONUS = utils.getIntConfigSetting("GameServer", "veteranTokenBonus");

    public static void giveVeteranRewardToken(obj_id player, int amount) {
        obj_id tatooine = getPlanetByName("tatooine");
        String objVar = "vetTokenCD_" + getPlayerStationId(player);
        showLootBox(player, new obj_id[]{ static_item.createNewItemFunction("item_vet_reward_token_01_01", player, amount * VET_TOKEN_BONUS) });
        setObjVar(tatooine, objVar, getCalendarTime());
    }

    public static void grantReward(obj_id player) {
        if (!hasCommand(player, "veteranPlayerBuff"))
        {
            grantCommand(player, "veteranPlayerBuff");
        }
        if (!hasCommand(player, "veteranPlayerBuff2"))
        {
            grantCommand(player, "veteranPlayerBuff2");
        }
        if (!hasCommand(player, "veteranPlayerBuff3"))
        {
            grantCommand(player, "veteranPlayerBuff3");
        }


		LocalDateTime now = LocalDateTime.now();
		int currentYear = now.getYear();
		int currentMonth = now.getMonthValue();
		int finchDate = now.getDayOfMonth();
		int finchGiftCount = 0;
		
        obj_id tatooine = getPlanetByName("tatooine");
		String finchGift1 = "finchGift1_" + getPlayerStationId(player);
		String finchGift2 = "finchGift2_" + getPlayerStationId(player);
		String finchGift3 = "finchGift3_" + getPlayerStationId(player);
		String finchGift4 = "finchGift4_" + getPlayerStationId(player);
		String finchGift5 = "finchGift5_" + getPlayerStationId(player);
		String finchGift6 = "finchGift6_" + getPlayerStationId(player);
		String finchGift7 = "finchGift7_" + getPlayerStationId(player);
		String finchGift8 = "finchGift8_" + getPlayerStationId(player);
		String finchGift9 = "finchGift9_" + getPlayerStationId(player);
		String finchGift10 = "finchGift10_" + getPlayerStationId(player);
		String finchGift11 = "finchGift11_" + getPlayerStationId(player);
		String finchGift12 = "finchGift12_" + getPlayerStationId(player);
		String finchGift13 = "finchGift13_" + getPlayerStationId(player);
		String finchGift14 = "finchGift14_" + getPlayerStationId(player);
		String finchGift15 = "finchGift15_" + getPlayerStationId(player);
		String finchGift16 = "finchGift16_" + getPlayerStationId(player);
		String finchGift17 = "finchGift17_" + getPlayerStationId(player);
		String finchGift18 = "finchGift18_" + getPlayerStationId(player);
		String finchGift19 = "finchGift19_" + getPlayerStationId(player);
		String finchGift20 = "finchGift20_" + getPlayerStationId(player);
		String finchGift21 = "finchGift21_" + getPlayerStationId(player);
		String finchGift22 = "finchGift22_" + getPlayerStationId(player);
		String finchGift23 = "finchGift23_" + getPlayerStationId(player);
		String finchGift24 = "finchGift24_" + getPlayerStationId(player);
		String finchGift25 = "finchGift25_" + getPlayerStationId(player);
		String finchGift26 = "finchGift26_" + getPlayerStationId(player);
		String finchGift27 = "finchGift27_" + getPlayerStationId(player);
		String finchGift28 = "finchGift28_" + getPlayerStationId(player);
		String finchGift29 = "finchGift29_" + getPlayerStationId(player);
		String finchGift30 = "finchGift30_" + getPlayerStationId(player);
		String finchGift31 = "finchGift31_" + getPlayerStationId(player);
		String finchGift31B = "finchGift31B_" + getPlayerStationId(player);

	if(currentYear == 2019)
	{
		if(currentMonth == 4)
		{
			if(finchDate == 1)
			{
				if (!hasObjVar(tatooine, finchGift1))
				{
					static_item.createNewItemFunction("item_content_decor_hologram_disk_03_01", player, 1);
					setObjVar(tatooine, finchGift1, getCalendarTime());
				}
			}
			if(finchDate == 2)
			{
				if (!hasObjVar(tatooine, finchGift2))
				{
					static_item.createNewItemFunction("recapture_gift_chapter_11_hoth_hologram_02_01", player, 1);
					setObjVar(tatooine, finchGift2, getCalendarTime());
				}
			}
			if(finchDate == 3)
			{
				if (!hasObjVar(tatooine, finchGift3))
				{
					static_item.createNewItemFunction("nebulon_frigate_holo_retention", player, 1);
					setObjVar(tatooine, finchGift3, getCalendarTime());
				}
			}
			if(finchDate == 4)
			{
				if (!hasObjVar(tatooine, finchGift4))
				{
					static_item.createNewItemFunction("col_buddy_token", player, 1);
					setObjVar(tatooine, finchGift4, getCalendarTime());
				}
			}
			if(finchDate == 5)
			{
				if (!hasObjVar(tatooine, finchGift5))
				{
					static_item.createNewItemFunction("col_rock_steaming_reward_01_01", player, 1);
					setObjVar(tatooine, finchGift5, getCalendarTime());
				}
			}
			if(finchDate == 6)
			{
				if (!hasObjVar(tatooine, finchGift6))
				{
					static_item.createNewItemFunction("col_buddy_02_token", player, 1);
					setObjVar(tatooine, finchGift6, getCalendarTime());
				}
			}
			if(finchDate == 7)
			{
				if (!hasObjVar(tatooine, finchGift7))
				{
					static_item.createNewItemFunction("col_mail_opt_in_3_01", player, 1);
					setObjVar(tatooine, finchGift7, getCalendarTime());
				}
			}
			if(finchDate == 8)
			{
				if (!hasObjVar(tatooine, finchGift8))
				{
					static_item.createNewItemFunction("item_publish_gift_29_generic_04_01", player, 1);
					setObjVar(tatooine, finchGift8, getCalendarTime());
				}
			}
			if(finchDate == 9)
			{
				if (!hasObjVar(tatooine, finchGift9))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_corellia_02", player, 1);
					setObjVar(tatooine, finchGift9, getCalendarTime());
				}
			}
			if(finchDate == 10)
			{
				if (!hasObjVar(tatooine, finchGift10))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_dantooine_02", player, 1);
					setObjVar(tatooine, finchGift10, getCalendarTime());
				}
			}
			if(finchDate == 11)
			{
				if (!hasObjVar(tatooine, finchGift11))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_endor_02", player, 1);
					setObjVar(tatooine, finchGift11, getCalendarTime());
				}
			}
			if(finchDate == 12)
			{
				if (!hasObjVar(tatooine, finchGift12))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_tatooine_02", player, 1);
					setObjVar(tatooine, finchGift12, getCalendarTime());
				}
			}
			if(finchDate == 13)
			{
				if (!hasObjVar(tatooine, finchGift13))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_lok_02", player, 1);
					setObjVar(tatooine, finchGift13, getCalendarTime());
				}
			}
			if(finchDate == 14)
			{
				if (!hasObjVar(tatooine, finchGift14))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_naboo_02", player, 1);
					setObjVar(tatooine, finchGift14, getCalendarTime());
				}
			}
			if(finchDate == 15)
			{
				if (!hasObjVar(tatooine, finchGift15))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_rori_02", player, 1);
					setObjVar(tatooine, finchGift15, getCalendarTime());
				}
			}
			if(finchDate == 16)
			{
				if (!hasObjVar(tatooine, finchGift16))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_talus_02", player, 1);
					setObjVar(tatooine, finchGift16, getCalendarTime());
				}
			}
			if(finchDate == 17)
			{
				if (!hasObjVar(tatooine, finchGift17))
				{
					static_item.createNewItemFunction("item_pgc_planet_hologram_yavin4_02", player, 1);
					setObjVar(tatooine, finchGift17, getCalendarTime());
				}
			}
			if(finchDate == 18)
			{
				if (!hasObjVar(tatooine, finchGift18))
				{
					static_item.createNewItemFunction("item_content_schematic_psg_droideka_02_01", player, 1);
					setObjVar(tatooine, finchGift18, getCalendarTime());
				}
			}
			if(finchDate == 19)
			{
				if (!hasObjVar(tatooine, finchGift19))
				{
					static_item.createNewItemFunction("col_fish_endor_buzzfish_02_01", player, 1);
					setObjVar(tatooine, finchGift19, getCalendarTime());
				}
			}
			if(finchDate == 20)
			{
				if (!hasObjVar(tatooine, finchGift20))
				{
					static_item.createNewItemFunction("item_housepack_xeno_couch_01_01", player, 1);
					static_item.createNewItemFunction("item_housepack_xeno_rug_01_01", player, 1);
					setObjVar(tatooine, finchGift20, getCalendarTime());
				}
			}
			if(finchDate == 21)
			{
				if (!hasObjVar(tatooine, finchGift21))
				{
					static_item.createNewItemFunction("col_fish_dathomir_burra_02_01", player, 1);
					setObjVar(tatooine, finchGift21, getCalendarTime());
				}
			}
			if(finchDate == 22)
			{
				if (!hasObjVar(tatooine, finchGift22))
				{
					static_item.createNewItemFunction("item_tow_bleed_abs_02_02", player, 1);
					static_item.createNewItemFunction("item_tow_bleed_abs_02_02", player, 1);
					setObjVar(tatooine, finchGift22, getCalendarTime());
				}
			}
			if(finchDate == 23)
			{
				if (!hasObjVar(tatooine, finchGift23))
				{
					static_item.createNewItemFunction("item_cts_sarlacc_non_functional", player, 1);
					setObjVar(tatooine, finchGift23, getCalendarTime());
				}
			}
			if(finchDate == 24)
			{
				if (!hasObjVar(tatooine, finchGift24))
				{
					static_item.createNewItemFunction("item_event_loveday_flowers_2009", player, 1);
					setObjVar(tatooine, finchGift24, getCalendarTime());
				}
			}
			if(finchDate == 25)
			{
				if (!hasObjVar(tatooine, finchGift25))
				{
					static_item.createNewItemFunction("col_fish_tank_front_panel_02_01", player, 1);
					setObjVar(tatooine, finchGift25, getCalendarTime());
				}
			}
			if(finchDate == 26)
			{
				if (!hasObjVar(tatooine, finchGift26))
				{
					static_item.createNewItemFunction("item_tcg_loot_reward_series1_greeter_ewok", player, 1);
					setObjVar(tatooine, finchGift26, getCalendarTime());
				}
			}
			if(finchDate == 27)
			{
				if (!hasObjVar(tatooine, finchGift27))
				{
					static_item.createNewItemFunction("item_tcg_loot_reward_series4_generic_rug_02_01", player, 1);
					setObjVar(tatooine, finchGift27, getCalendarTime());
				}
			}
			if(finchDate == 28)
			{
				if (!hasObjVar(tatooine, finchGift28))
				{
					static_item.createNewItemFunction("item_event_halloween_spider_web_01", player, 1);
					setObjVar(tatooine, finchGift28, getCalendarTime());
				}
			}
			if(finchDate == 29)
			{
				if (!hasObjVar(tatooine, finchGift29))
				{
					static_item.createNewItemFunction("item_wearable_fedora_hat", player, 1);
					setObjVar(tatooine, finchGift29, getCalendarTime());
				}
			}
			if(finchDate == 30)
			{
				if (!hasObjVar(tatooine, finchGift30))
				{
					static_item.createNewItemFunction("item_cts_sarlacc_mini_game", player, 1);
					setObjVar(tatooine, finchGift30, getCalendarTime());
				}
			}
		}
		if(currentMonth == 5)
			{
				if(finchDate == 01)
				{
					if (!hasObjVar(tatooine, finchGift31))
					{
						static_item.createNewItemFunction("item_cs_damage_booster_e_04_01", player, 1);
						static_item.createNewItemFunction("item_cs_decor_stuffed_rancor_02_01", player, 1);
						static_item.createNewItemFunction("item_tcg_loot_reward_series4_peko_peko_mount_02_01", player, 1);
						setObjVar(tatooine, finchGift31, getCalendarTime());
					}
					if (hasObjVar(tatooine, finchGift1))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift2))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift3))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift4))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift5))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift6))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift7))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift8))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift9))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift10))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift11))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift12))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift13))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift14))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift15))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift16))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift17))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift18))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift19))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift20))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift21))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift22))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift23))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift24))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift25))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift26))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift27))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift28))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift29))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift30))
					{
						finchGiftCount ++;
					}
					if (hasObjVar(tatooine, finchGift31))
					{
						finchGiftCount ++;
					}
					if (!hasObjVar(tatooine, finchGift31B) && finchGiftCount >= 20)
					{
						static_item.createNewItemFunction("item_tcg_loot_reward_series4_home_itv_02_01", player, 1);
						setObjVar(tatooine, finchGift31B, getCalendarTime());
					}
				}
			}
		}
    }
}
