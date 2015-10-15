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
		if(TConfig.getConfigFile("XSJRPG","bancommand.yml").getStringList(world).contains(event.getMessage().split(" ")[0].replaceAll("/", "")))
		{
			System.out.println(TString.Prefix("新世纪RPG",4)+player.getName()+"在世界"+world+"使用了被禁止命令"+event.getMessage().split(" ")[0].replaceAll("/", ""));
			if(player.hasPermission("xsjrpg.admin.bancommand"))
				return;
			event.setCancelled(true);
			player.sendMessage(TString.Prefix("新世纪RPG",4)+"你无法在该世界执行该命令");
		}
	}
}
