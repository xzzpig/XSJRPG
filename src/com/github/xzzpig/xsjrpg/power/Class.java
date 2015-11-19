package com.github.xzzpig.xsjrpg.power;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

import com.github.xzzpig.BukkitTools.*;

import org.bukkit.event.entity.*;

public class Class
{
	public static boolean canUse(Player player,ItemStack is)
	{
		if(player.hasPermission("xsjrpg.type.*"))
		{
			return true;
		}
		String type = "";
		if(is == null||is.getType() == Material.AIR)
			return true;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null){
			return true;
		}
		for(String arg:lore)
		{
			if(arg.startsWith("Type"))
			{
				try
				{
					type = arg.substring(5);
				}
				catch (NumberFormatException e1)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nType格式:Type:xxx");
					return false;
				}
				if(player.hasPermission("xsjrpg.type."+type))
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
		ItemStack[] iss = player.getInventory().getArmorContents();
		if(iss != null)
			for(ItemStack is2:iss)
				if(canUse(player,is2))
					return;
		if(canUse(player,is))
			return;
		event.setCancelled(true);
		player.sendMessage(TString.Prefix("新世纪RPG",4)+"由于type权限不足，你不能使用该物品");
	}
}
