package com.github.xzzpig.xsjrpg.command;
import com.github.xzzpig.BukkitTools.*;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import java.util.*;

public class Commands
{
	@SuppressWarnings("deprecation")
	public static boolean command(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = Bukkit.getPlayer(sender.getName());
		if(label.equalsIgnoreCase("xsjrpg")||label.equalsIgnoreCase("xsj")){
			if(getarg(args,0)==null)
				return false;
			if(getarg(args,0).equalsIgnoreCase("help"))
			{
				if(!sender.hasPermission("xsjrpg.command.help"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj mz [物品名称] -修改物品名称");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj lore (help) -修改物品lore");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj item (help) -保存/提取物品");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj bancommand [world] [command] -禁止某世界使用某命令");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj levellimit [world] [level] -设置某世界最高等级");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj ljt -打开垃圾桶");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj temppremission [player] [权限] [时间] -设置某玩家在一点时间内有某权限(单位min)");
				sender.sendMessage(TString.Prefix("新世纪RPG",2)+"/xsj ps -Ps命令");
				return true;
			}
			else if(getarg(args,0).equalsIgnoreCase("mz"))
			{
				if(!sender.hasPermission("xsjrpg.command.mz"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				if(getarg(args,1) == null)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n用法:/xsj mz [物品名称]");
					return true;
				}
				ItemStack is = player.getItemInHand();
				ItemMeta im = is.getItemMeta();
				im.setDisplayName(getarg(args,1).replaceAll("&","§"));
				is.setItemMeta(im);
				sender.sendMessage(TString.Prefix("新世纪RPG",3)+"该物品名称已修改");
				return true;
			}
			else if(getarg(args,0).equalsIgnoreCase("lore"))
			{
				if(!sender.hasPermission("xsjrpg.command.lore"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				return Lore.command(sender, cmd, label, args);
			}
			else if(getarg(args,0).equalsIgnoreCase("ljt"))
			{
				if(!sender.hasPermission("xsjrpg.command.ljt"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				Inventory inv = Bukkit.getServer().createInventory(null, 6*9,"新世纪RPG垃圾桶");
				player.openInventory(inv);
			}
			else if(getarg(args,0).equalsIgnoreCase("item"))
			{
				if(!sender.hasPermission("xsjrpg.command.item"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				return Item.command(sender,cmd,label,args);
			}
			else if(getarg(args,0).equalsIgnoreCase("bancommand"))
			{
				if(!sender.hasPermission("xsjrpg.command.bancommand"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				String world = getarg(args,1);
				String command = getarg(args,2);
				List<String> commands = TConfig.getConfigFile("XSJRPG","bancommand.yml").getStringList(world);
				if(commands == null)
					commands = new ArrayList<String>();
				if(!commands.contains(command))
					commands.add(command);
				TConfig.saveConfig("XSJRPG","bancommand.yml",world,commands);
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"已禁止"+world+"的"+command);
			}
			else if(getarg(args,0).equalsIgnoreCase("levellimit"))
			{
				if(!sender.hasPermission("xsjrpg.command.levellimit"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				String world = getarg(args,1);
				String slevel = getarg(args,2);
				int level = -1;
				try
				{
					level = Integer.valueOf(slevel);
				}
				catch (NumberFormatException e)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n用法:/xsj levellimit [world] [level]");
					return true;
				}
				TConfig.saveConfig("XSJRPG", "levellimit.yml", world, level);
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"已设置"+world+"的最高等级"+level);
			}
			else if(getarg(args,0).equalsIgnoreCase("temppremission"))
			{
				if(!sender.hasPermission("xsjrpg.command.temppremission"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				String toplayer = getarg(args,1);
				String premission = getarg(args,2);
				int time = 0;
				if(toplayer==null||premission==null)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n用法:/xsj temppremission [player] [权限] [时间(min)]");
					return true;
				}
				try
				{
					time = Integer.valueOf(getarg(args,3));
				}
				catch (NumberFormatException e)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n用法:/xsj temppremission [player] [权限] [时间(min)]");
					return true;
				}
				Player p = Bukkit.getPlayer(toplayer);
				if(p == null)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"错误:该玩家未在线");
					return true;
				}
				p.addAttachment(Bukkit.getPluginManager().getPlugin("XSJRPG"),premission,true,time*20*60);
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"已设置"+toplayer+"在"+time+"分钟内拥有权限"+premission);
			}
			else if(getarg(args,0).equalsIgnoreCase("ps"))
			{
				if(!sender.hasPermission("xsjrpg.command.ps"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				return Ps.command(sender,cmd,label,args);
			}
		}
		return false;
	}
	
	public static String getarg(String[] args,int num)
	{
		if(args.length<=num)
		{
			return null;
		}
		return args[num];
	}
}
