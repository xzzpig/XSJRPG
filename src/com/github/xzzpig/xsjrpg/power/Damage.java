package com.github.xzzpig.xsjrpg.power;
import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Damage
{
	@SuppressWarnings("deprecation")
	public static void run(EntityDamageByEntityEvent event)
	{
		if(event.getDamager().getType() !=EntityType.PLAYER||event.isCancelled())
			return;
		Player player = (Player) event.getDamager();
		int damage = (int)event.getDamage();
		ItemStack is = player.getItemInHand();
		if(is == null||is.getType() == Material.AIR)
			return;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		int add = 0;
		int f = 0;
		int e = 0;
		if(lore == null){
			return;
		}
		for(String arg:lore)
		{
			if(arg.endsWith("Damage"))
			{
				try
				{
					f = Integer.valueOf(TString.sub(arg, "+", "-"));
					e = Integer.valueOf(TString.sub(arg, "-", " D"));
				}
				catch (NumberFormatException e1)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nDamage格式:+xxx-yyy Damage");
					return;
				}
				if(e<f)
				{
					player.sendMessage(TString.Prefix("新世纪RPG",4)+"lore错误\nDamage中应前者<=后者");
					return;
				}
				add = Math.abs(new Random().nextInt(e-f)+ f);
				damage = damage+add;
				event.setDamage(damage);
			}
		}
	}
}
