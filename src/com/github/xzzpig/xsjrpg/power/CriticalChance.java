package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class CriticalChance
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
		int chance = 0;
		int exdamage = 0;
		int damage = (int) event.getDamage();
		if(lore == null){
			return;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("CriticalDamage"))
			{
				try
				{
					chance = Integer.valueOf(TString.sub(arg, "+", "%"));
					exdamage = Integer.valueOf(TString.sub(arg, " +", " CriticalDamage"));
				}
				catch (NumberFormatException e)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nCriticalDamage格式:+xxx% CriticalChance +yyy CriticalDamage");
					return;
				}
				int i = Math.abs(new Random().nextInt(100));
				if(i<=chance)
				{
					event.setDamage(damage + exdamage);
				}
			}
		}
	}
}
