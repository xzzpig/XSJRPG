package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;
import java.util.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Evasion
{
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getEntity().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getEntity();
		ItemStack is = player.getItemInHand();
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int chance = 0;
		for(String arg:lore)
		{
			if(arg.endsWith("Evasion"))
			{
				try
				{
					chance = Integer.valueOf(TString.sub(arg, "+", "%"));
				}
				catch (NumberFormatException e)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nEvasion格式:+xxx% Evasion");
					return;
				}
				int i = Math.abs(new Random().nextInt(100));
				if(i<=chance)
				{
					event.setCancelled(true);
				}
			}
		}
	}
}
