package com.github.xzzpig.xsjrpg.power;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

import com.github.xzzpig.BukkitTools.*;

import org.bukkit.event.entity.*;

public class Level
{
	public static boolean canUse(Player player,ItemStack is)
	{
		if(player.hasPermission("xsjrpg.admin.level"))
		{
			return true;
		}
		if(is == null||is.getType() == Material.AIR)
			return true;
		int level = player.getLevel();
		int limitlevel = 0;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null){
			return true;
		}
		for(String arg:lore)
		{
			if(arg.startsWith("Lv"))
			{
				try
				{
					limitlevel = Integer.valueOf(arg.substring(3));
				}
				catch (NumberFormatException e1)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nLv格式:Lv xxx");
					return false;
				}
				if(level >= limitlevel)
					return true;
				else
					return false;
			}
		}
		return true;
	}
	
	public static void onPlayerDamage(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getDamager();
		ItemStack is = player.getItemInHand();
		if(!canUse(player,is))
		{
			event.setCancelled(true);
			player.sendMessage(TString.Prefix("新世纪RPG",4)+"由于等级不足，你不能使用该物品");
		}
	}
}
