package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.*;

public class LmmuneElement
{
	public static boolean isPass(Player player)
	{
		boolean pass = false;
		ItemStack[] iss = player.getInventory().getArmorContents();
		if(iss == null)
			return false;
		for(ItemStack is:iss)
		{
			if(isPrePass(is))
				return true;
		}
		ItemStack is = player.getItemInHand();
		pass = isPrePass(is);
		return pass;
	}

	private static boolean isPrePass(ItemStack is)
	{
		if(is == null||is.getType() == Material.AIR)
			return false;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null){
			return false;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("lmmuneElement"))
				return true;
		}
		return false;
	}
	
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getEntity().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getEntity();
		if(isPass(player)){
			player.removePotionEffect(PotionEffectType.POISON);
			player.removePotionEffect(PotionEffectType.SLOW);
			player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
			player.removePotionEffect(PotionEffectType.WITHER);
		}
	}
}
