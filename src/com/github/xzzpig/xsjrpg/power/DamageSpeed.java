package com.github.xzzpig.xsjrpg.power;

import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class DamageSpeed
{
	public static HashMap<String, Boolean> on = new HashMap<String, Boolean>();
	@SuppressWarnings("deprecation")
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() !=EntityType.PLAYER||((LivingEntity)event.getEntity()).isDead()||event.isCancelled())
			return;
		Player player = (Player) event.getDamager();
		int damage =(int) event.getDamage();
		ItemStack is = player.getItemInHand();
		if(is == null||is.getType() == Material.AIR)
			return;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int time = 0;
		if(lore == null){
			return;
		}
		for(String arg:(lore))
		{
			if(arg.endsWith("DamageSpeed"))
			{
				try
				{
					time = Integer.valueOf(TString.sub(arg, "+", " D"));
				}
				catch (NumberFormatException e1)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nDamageSpeed格式:+xxx DamageSpeed");
					return;
				}
				on.put(player.getName(),true);
				while(time>0)
				{
					LivingEntity le = (LivingEntity) event.getEntity();
					le.damage(damage,player);
					time--;
				}
				on.put(player.getName(),false);
			}
		}
	}
}
