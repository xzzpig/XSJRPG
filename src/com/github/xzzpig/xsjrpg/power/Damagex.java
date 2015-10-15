package com.github.xzzpig.xsjrpg.power;
import org.bukkit.Material;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

import com.github.xzzpig.BukkitTools.*;

public class Damagex
{
	@SuppressWarnings("deprecation")
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getDamager();
		int damage =(int) event.getDamage();
		
		int time = getAdd(player);
		if(time == 0)
			time = 100;
		damage = damage*time/100;
		event.setDamage(damage);
	}
	
	public static int getAdd(Player player)
	{
		int time = 0;
		ItemStack[] iss = player.getInventory().getArmorContents();
		if(iss == null)
			return 0;
		for(ItemStack is:iss)
		{
			time = time + getPreAdd(is);
		}
		ItemStack is = player.getItemInHand();
		time = time + getPreAdd(is);
		return time;
	}

	private static int getPreAdd(ItemStack is)
	{
		if(is == null||is.getType() == Material.AIR)
			return 0;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int time = 0;
		if(lore == null){
			return 0;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("Damagex"))
			{
				try
				{
					time = Integer.valueOf(TString.sub(arg, "+", "%"));
				}
				catch (NumberFormatException e)
				{
					return 0;
				}
				return time;
			}
		}
		return 0;
	}
}
