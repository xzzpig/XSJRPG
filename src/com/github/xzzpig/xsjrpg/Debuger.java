package com.github.xzzpig.xsjrpg;

import org.bukkit.*;
import org.bukkit.entity.*;

public class Debuger
{
	public static boolean isdebug = true;
	@SuppressWarnings("deprecation")
	public static void prints(String s)
	{
		if(isdebug == false)return;
		System.out.println("\n****************\n"+s+"\n****************");
		for(Player p: Bukkit.getServer().getOnlinePlayers())
		{
			if(p.isOp())
				p.sendMessage(s);
		}
	}
}
