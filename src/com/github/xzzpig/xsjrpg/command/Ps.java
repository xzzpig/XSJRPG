package com.github.xzzpig.xsjrpg.command;

import com.github.xzzpig.BukkitTools.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import com.github.xzzpig.xsjrpg.*;

public class Ps
{
	@SuppressWarnings("deprecation")
	public static boolean command(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = Bukkit.getPlayer(sender.getName());
		if(Commands.getarg(args,1) == null)
		{
			sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj ps help查看帮助");
			return true;
		}
		if(Commands.getarg(args,1).equalsIgnoreCase("help"))
		{
			if(!sender.hasPermission("xsjrpg.command.help"))
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
				return true;
			}
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj ps list -查看所有的Ps");
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj ps create [uid] [内容] -新建个Ps");
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("create"))
		{
			String uid = Commands.getarg(args,2),
				message = Commands.getarg(args,3);
			if(uid == null||message == null){
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"错误:uid或内容不可为空");
				return false;
			}
			message = PsSolver.solve(message);
			TConfig.saveConfig("XSJRPG","ps.yml","ps."+uid,message);
			sender.sendMessage(TString.Prefix("新世纪RPG",2)+"新的ps已创建");
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("list"))
		{
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"Ps列表:");
			try{
				for(String uid:TConfig.getConfigPath("XSJRPG","ps.yml","ps")){
					sender.sendMessage(uid+":"+TConfig.getConfig("XSJRPG","ps.yml","ps."+uid));
				}
			}
			catch(Exception e){
				sender.sendMessage("无");
			}
			return true;
		}
		
		return false;
	}
}
