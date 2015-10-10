package com.github.xzzpig.xsjrpg.command;

import org.bukkit.command.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import com.github.xzzpig.BukkitTools.*;

import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class Lore
{
	@SuppressWarnings("deprecation")
	public static boolean command(CommandSender sender, Command cmd, String label, String[] args)
	{
		Player player = Bukkit.getPlayer(sender.getName());
		if(Commands.getarg(args,1) == null)
		{
			sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj lore help查看帮助");
			return true;
		}
		ItemStack is = player.getItemInHand();
		ItemMeta im = is.getItemMeta();
		List<String> lore = im.getLore();
		if(Commands.getarg(args,1).equalsIgnoreCase("help"))
		{
			if(!sender.hasPermission("xsjrpg.command.help"))
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"你没有权限执行该命令");
				return true;
			}
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj lore list -查看所有特殊lore");
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj lore add [lore] <行数> -给物品添加lore");
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj lore del [行数] -删除lore");
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("list"))
		{
			sender.sendMessage(TString.Prefix("新世纪RPG",3)+"/xsj lore add [lore] <行数> -给物品添加lore");
			sender.sendMessage(TString.Prefix("新世纪RPG",6)+"特殊lore列表：");
			sender.sendMessage("$3+xxx% Damagex    -攻击力×xxx%[武器]");
			sender.sendMessage("$3+xxx% Damages    -xxx%秒杀对象[武器]");
			sender.sendMessage("$3+xxx-yyy Damage  -表示范围xxx~yyy的范围[武器]");
			sender.sendMessage("$3+xxx Health      -额外增加xxx点生命[防具]");
			sender.sendMessage("$3+xxx DamageSpeed -增加2点攻击速度[武器]");
			sender.sendMessage("$3+xxx% Evasion    -增加玩家xxx%的闪避几率[武器|防具]");
			sender.sendMessage("$3+xxx% CriticalChance +yyy CriticalDamage -xxx%的几率造成额外yyy点伤害[武器]");
			sender.sendMessage("$3+xxx LifeSteal   -窃取xxx点生命[武器]");
			sender.sendMessage("$3+xxx Armor       -减少xxx点伤害[武器|防具]");
			sender.sendMessage("$3Lv xxx           -使用者至少xxx级才能使用[all]");
			sender.sendMessage("$3Type:xxx         -拥有 xsjrpg.type.xxx 才能使用[all]");
			sender.sendMessage("$3BOOM             -攻击带有爆炸<音效和效果>(攻击生物是生效)[all]");
			sender.sendMessage("$3+XX% Lightning   -攻击XX几率带有闪电(攻击生物时生效)[all]");
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("add"))
		{
			if(Commands.getarg(args,2) == null)
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj lore help 查看帮助");
				return true;
			}
			int line = -1;
			if(Commands.getarg(args,3) != null)
			{
				try
				{
					line = Integer.valueOf(args[3]);
				}
				catch (NumberFormatException e)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"错误:行数不是整数");
					return false;
				}
			}
			if(line == -1)
			{
				lore.add(args[2]);
			}
			else
			{
				lore.add(line,args[2]);
			}
			im.setLore(lore);
			is.setItemMeta(im);
			sender.sendMessage((TString.Prefix("新世纪RPG",4)+"lore已修改"));
			return true;
		}
		else if(Commands.getarg(args,1).equalsIgnoreCase("del"))
		{
			if(Commands.getarg(args,2) == null)
			{
				sender.sendMessage(TString.Prefix("新世纪RPG",4)+"用法错误\n输入:/xsj lore help 查看帮助");
				return true;
			}
			int line = 0;
			if(Commands.getarg(args,2) != null)
			{
				try
				{
					line = Integer.valueOf(args[2]);
				}
				catch (NumberFormatException e)
				{
					sender.sendMessage(TString.Prefix("新世纪RPG",4)+"错误:行数不是整数");
					return false;
				}
			}
			lore.remove(line);
			im.setLore(lore);
			is.setItemMeta(im);
			sender.sendMessage((TString.Prefix("新世纪RPG",4)+"lore已删除"));
			return true;
		}
		return false;
	}
}
