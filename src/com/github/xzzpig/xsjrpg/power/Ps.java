package com.github.xzzpig.xsjrpg.power;
import com.github.xzzpig.BukkitTools.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import com.github.xzzpig.xsjrpg.*;

public class Ps
{
	public static void run(EntityDeathEvent event)
	{
		LivingEntity killed = event.getEntity();
		killed.getKiller();
		Player killer = killed.getKiller();
		if(killer == null)
			return;
		ItemStack is = killer.getItemInHand();
		if(is == null||is.getType() == Material.AIR)
			return;
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		if(lore == null){
			return;
		}
		for(String arg:lore)
		{
			if(arg.startsWith("Ps"))
			{
				String uid = arg.replaceAll("Ps:","");
				String ps = (String)TConfig.getConfig("XSJRPG","ps.yml","ps."+uid);
				killer.getServer().broadcastMessage(PsSolver.solve(ps,killer,killed));
			}
		}
	}
}
