package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Armor
{
	public static int getArmor(Player player)
	{
		int armor = 0;
		ItemStack[] iss = player.getInventory().getArmorContents();
		if(iss == null)
			return 0;
		for(ItemStack is:iss)
		{
			armor = armor + getPreArmor(is);
		}
		ItemStack is = player.getItemInHand();
		armor = armor + getPreArmor(is);
		return armor;
	}
	
	private static int getPreArmor(ItemStack is)
	{
		if(is == null||is.getType() == Material.AIR)
			return 0;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int armor = 0;
		if(lore == null){
			return 0;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("Armor"))
			{
				try
				{
					armor = Integer.valueOf(TString.sub(arg, "+", " A"));
				}
				catch (NumberFormatException e)
				{
					return 0;
				}
				return armor;
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
		int armor = getArmor(player);
		if(damage<armor)
			damage = 0;
		else
			damage = damage-armor;
		event.setDamage(damage);
	}
}
