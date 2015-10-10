package com.github.xzzpig.xsjrpg.command;
import com.github.xzzpig.BukkitTools.*;

import java.util.*;

import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

public class Item
{
	@SuppressWarnings("deprecation")
	public static void saveItem(String uid,ItemStack is)
	{
		ItemMeta im = is.getItemMeta();
		String name = im.getDisplayName();
		int id = is.getTypeId();
		List<String> lore = im.getLore();
		TConfig.saveConfig("XSJRPG","items.yml","items."+uid+".name",name);
		TConfig.saveConfig("XSJRPG","items.yml","items."+uid+".id",id);
		TConfig.saveConfig("XSJRPG","items.yml","items."+uid+".lore",lore);
	}
	@SuppressWarnings("deprecation")
	public static ItemStack getItem(String uid)
	{
		String name = TConfig.getConfigFile("XSJRPG","items.yml").getString("items."+uid+".name","error");
		int id = TConfig.getConfigFile("XSJRPG","items.yml").getInt("items."+uid+".id",1);
		List<String> lore = TConfig.getConfigFile("XSJRPG","items.yml").getStringList("items."+uid+".lore");
		ItemStack is = new ItemStack(id);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean command(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = Bukkit.getPlayer(sender.getName());
		if(Commands.getarg(args,1) == null)
		{
			sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj item help查看帮助");
			return true;
		}
		if(Commands.getarg(args,1).equalsIgnoreCase("help"))
		{
			if(!sender.hasPermission("xsjrpg.command.help"))
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
				return true;
			}
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj item save [uid] -将手中物品保存");
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj item get [uid] -将指定uid物品加入背包");
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("save"))
		{
			if(Commands.getarg(args,2) == null)
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj item help 查看帮助");
				return true;
			}
			String uid = args[2];
			saveItem(uid,player.getItemInHand());
			sender.sendMessage((TString.Prefix("新世纪RPG",4)+"物品已保存"));
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("get"))
		{
			if(Commands.getarg(args,2) == null)
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj item help 查看帮助");
				return true;
			}
			String uid = args[2];
			ItemStack is = getItem(uid);
			player.getInventory().addItem(is);
			sender.sendMessage((TString.Prefix("新世纪RPG",4)+"物品已添加"));
			return true;
		}
		return false;
	}
}
