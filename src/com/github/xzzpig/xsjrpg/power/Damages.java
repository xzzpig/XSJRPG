package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Damages
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
		int range = 0;
		if(lore == null){
			return;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("Damages"))
			{
				try
				{
					range = Integer.valueOf(TString.sub(arg, "+", "%"));
				}
				catch (NumberFormatException e)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nDamages格式:+xxx% Damages");
					return;
				}
				int i = Math.abs(new Random().nextInt(100));
				if(i<=range)
				{
					double health = Double.MAX_VALUE;
					event.setDamage(health);
				}
			}
		}
	}
}
