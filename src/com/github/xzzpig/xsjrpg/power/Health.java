package com.github.xzzpig.xsjrpg.power;
import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Health
{
	@SuppressWarnings("deprecation")
	public static void freshHealth(Player player)
	{
		int maxhealth = getExHealth(player)+20;
		player.setHealth(maxhealth);
		player.sendMessage(TString.Prefix("新世纪RPG",3)+"你的最大生命值已刷新");
	}
	public static int getExHealth(Player player)
	{
		int exhealth = 0;
		ItemStack[] iss = player.getInventory().getArmorContents();
		for(ItemStack is:iss)
		{
			exhealth = exhealth + getPreExHealth(is);
		}
		return exhealth;
	}

	private static int getPreExHealth(ItemStack is)
	{
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int exhealth = 0;
		for(String arg:lore)
		{
			if(arg.endsWith("Health"))
			{
				try
				{
					exhealth = Integer.valueOf(TString.sub(arg, "+", " H"));
				}
				catch (NumberFormatException e)
				{
					return 0;
				}
				return exhealth;
			}
		}
		return 0;
	}
}
