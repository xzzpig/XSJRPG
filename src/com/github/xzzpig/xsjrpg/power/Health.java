package com.github.xzzpig.xsjrpg.power;
import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Health
{
	@SuppressWarnings("deprecation")
	public static void freshHealth(Player player)
	{
		int maxhealth = getExHealth(player)+20;
		if(((Damageable)player).getMaxHealth() == maxhealth)
			return;
		player.setMaxHealth(maxhealth);
		player.sendMessage(TString.Prefix("新世纪RPG",3)+"你的最大生命值已刷新");
	}
	public static int getExHealth(Player player)
	{
		int exhealth = 0;
		ItemStack[] iss = player.getInventory().getArmorContents();
		if(iss == null)
			return 0;
		for(ItemStack is:iss)
		{
			exhealth = exhealth + getPreExHealth(is);
		}
		return exhealth;
	}

	private static int getPreExHealth(ItemStack is)
	{
		if(is == null||is.getType() == Material.AIR)
			return 0;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int exhealth = 0;
		if(lore == null){
			return 0;
		}
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
