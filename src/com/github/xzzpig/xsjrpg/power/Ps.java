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
	public static void run(final EntityDamageByEntityEvent event)
	{
		Debuger.prints("Ps");
		new Thread(new Runnable(){
				@Override
				public void run(){
					try{
						Thread.sleep(500);
					}
					catch(InterruptedException e){}
					Debuger.prints("Ps…run");
					LivingEntity killed = (LivingEntity) event.getEntity();
					if(!killed.isDead())
						return;
					Debuger.prints("Ps…dead");
					if(!(event.getDamager() instanceof Player))
						return;
					Debuger.prints("Ps…isplayer");
					Player killer =(Player) event.getDamager();
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
							Debuger.prints("Ps…haslore");
							String uid = arg.replaceAll("Ps:","");
							Debuger.prints("Ps…uid:"+uid);
							String ps = (String)TConfig.getConfig("XSJRPG","ps.yml","ps."+uid);
							Debuger.prints("Ps…ps:"+ps);
							killer.getServer().broadcastMessage(PsSolver.solve(ps,killer,killed));
						}
					}
				}
			}).start();
		
	}
}
