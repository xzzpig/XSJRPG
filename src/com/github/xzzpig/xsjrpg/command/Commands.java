package com.github.xzzpig.xsjrpg.command;
import com.github.xzzpig.BukkitTools.*;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Commands
{
	@SuppressWarnings("deprecation")
	public static boolean command(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = Bukkit.getPlayer(sender.getName());
		if(label.equalsIgnoreCase("xsjrpg")||label.equalsIgnoreCase("xsj")){
			if(getarg(args,0).equalsIgnoreCase("help"))
			{
				if(!sender.hasPermission("xsjrpg.command.help"))
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
					return true;
				}
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"/xsj mz [物品名称] -修改物品名称");
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"/xsj lore (help) -修改物品lore");
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"/xsj ljt -打开垃圾桶");
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
				im.setDisplayName(getarg(args,1));
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
				Inventory inv = Bukkit.getServer().createInventory(null, InventoryType.CHEST,"新世纪RPG垃圾桶");
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
