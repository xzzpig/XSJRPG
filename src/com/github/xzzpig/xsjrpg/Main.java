package com.github.xzzpig.xsjrpg;

import org.bukkit.command.*;
import org.bukkit.plugin.java.*;
import com.github.xzzpig.xsjrpg.command.*;
import com.github.xzzpig.xsjrpg.power.*;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		getLogger().info(getName()+"已加载");
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new PowerListener(), this);
	}
	
	@Override
	public void onDisable() {
	getLogger().info(getName()+"已卸载");
	}
	
	@Override
	public boolean onCommand(CommandSender sender,Command cmd,String label,String[] args)  {
		return Commands.command(sender, cmd, label, args);
	}

}
