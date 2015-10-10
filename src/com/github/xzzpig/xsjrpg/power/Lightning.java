package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Lightning
{
	public static void run(PlayerInteractEntityEvent event)
	{
		Player player = event.getPlayer();
		ItemStack is = player.getItemInHand();
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		for(String arg:lore)
		{
			if(arg.endsWith("Lightning"))
			{
				int chance;
				try
				{
					chance = Integer.valueOf(TString.sub(arg, "+", "%"));
				}
				catch (NumberFormatException e1)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nLightning格式:+xxx% Lightning");
					return;
				}
				int i = Math.abs(new Random().nextInt(100));
				if(i<chance)
				{
					player.getWorld().strikeLightning(event.getRightClicked().getLocation());
				}
			}
		}
	}
}
