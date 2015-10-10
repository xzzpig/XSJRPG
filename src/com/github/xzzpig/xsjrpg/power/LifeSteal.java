package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class LifeSteal
{
	@SuppressWarnings("deprecation")
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getDamager();
		ItemStack is = player.getItemInHand();
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int amount = 0;
		for(String arg:lore)
		{
			if(arg.endsWith("LifeSteal"))
			{
				try
				{
					amount = Integer.valueOf(TString.sub(arg, "+", " L"));
				}
				catch (NumberFormatException e)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nLifeSteal格式:+xxx LifeSteal");
					return;
				}
				int health =(int)((Damageable)player).getHealth() ;
				player.setHealth(health+amount);
			}
		}
	}
}
