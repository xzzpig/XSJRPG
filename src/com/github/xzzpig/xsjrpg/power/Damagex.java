package com.github.xzzpig.xsjrpg.power;
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
		ItemStack is = player.getItemInHand();
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int time = 100;
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
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nDamagex格式:+xxx% Damagex");
					return;
				}
				damage = damage*time/100;
				event.setDamage(damage);
			}
		}
	}
}
