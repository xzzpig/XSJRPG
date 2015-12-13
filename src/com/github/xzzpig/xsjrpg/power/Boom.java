package com.github.xzzpig.xsjrpg.power;

import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Boom
{
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getDamager();
		ItemStack is = player.getItemInHand();
		if(is == null||is.getType() == Material.AIR)
			return;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null){
			return;
		}
		for(String arg:lore)
		{
			if(arg.equalsIgnoreCase("BOOM"))
			{
				player.playSound(player.getLocation(),Sound.EXPLODE,10,10);
			}
		}
	}
}
