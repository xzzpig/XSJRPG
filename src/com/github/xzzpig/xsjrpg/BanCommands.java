package com.github.xzzpig.xsjrpg;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import com.github.xzzpig.BukkitTools.*;

public class BanCommands implements Listener
{
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String world = player.getWorld().getName();
		System.out.println("已监听命令事件，命令为"+ event.getMessage().split("")[0]);
		if(TConfig.getConfigFile("XSJRPG","bancommand.yml").getStringList(world).contains(event.getMessage().split("")[0]))
		{
			event.setCancelled(true);
			player.sendMessage(TString.Prefix("新世纪RPG",4)+"你无法在该世界执行该命令");
		}
	}
}
