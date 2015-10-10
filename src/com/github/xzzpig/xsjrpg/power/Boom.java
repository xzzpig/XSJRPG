package com.github.xzzpig.xsjrpg.power;

import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Boom
{
	public static void run(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		ItemStack is = player.getItemInHand();
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		for(String arg:lore)
		{
			if(arg.equalsIgnoreCase("BOOM"))
			{
				player.playSound(player.getLocation(),Sound.EXPLODE,10,10);
			}
		}
	}
}
