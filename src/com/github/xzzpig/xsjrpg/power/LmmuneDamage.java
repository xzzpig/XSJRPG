package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class LmmuneDamage
{
	public static int getMax(Player player)
	{
		int max = 0;
		ItemStack[] iss = player.getInventory().getArmorContents();
		if(iss == null)
			return 0;
		for(ItemStack is:iss)
		{
			int temp = getPreMax(is);
			if(max > temp)
				max = temp;
		}
		return max;
	}

	private static int getPreMax(ItemStack is)
	{
		if(is == null||is.getType() == Material.AIR)
			return 0;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int max = 0;
		if(lore == null){
			return 0;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("lmmuneDamage"))
			{
				try
				{
					max = Integer.valueOf(TString.sub(arg, "+", " l"));
				}
				catch (NumberFormatException e)
				{
					return 0;
				}
				return max;
			}
		}
		return 0;
	}

	@SuppressWarnings("deprecation")
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getEntity().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getEntity();
		int damage =(int) event.getDamage();
		int max = getMax(player);
		if(damage>max)
			damage = max;
		event.setDamage(damage);
	}
}
