package com.github.xzzpig.xsjrpg;
import com.github.xzzpig.BukkitTools.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

public class LevelLimit implements Listener
{
	@EventHandler
	public void onCommand(PlayerLevelChangeEvent event)
	{
		Player player = event.getPlayer();
		String world = player.getWorld().getName();
		int maxlevel = TConfig.getConfigFile("XSJRPG","levellimit.yml").getInt(world,-1); 
		if(maxlevel == -1)
			return;
		else if(player.hasPermission("xsjrpg.admin.level"))
			return;
		else if(player.getLevel() > maxlevel)
		{
			player.setLevel(maxlevel);
			player.sendMessage(TString.Prefix("新世纪RPG",4)+"你的等级已达到该世界最大限制");
		}
	}
}
