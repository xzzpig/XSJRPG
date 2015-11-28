package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
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
		if(is == null||is.getType() == Material.AIR)
			return;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int amount = 0;
		if(lore == null){
			return;
		}
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
				int health =(int)((Damageable)player).getHealth()+amount;
				int maxhealth =(int)((Damageable)player).getMaxHealth();
				if(health > maxhealth)
					health = maxhealth;
				player.setHealth(health+amount);
			}
		}
	}
}
