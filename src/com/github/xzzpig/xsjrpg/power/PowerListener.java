package com.github.xzzpig.xsjrpg.power;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import com.github.xzzpig.BukkitTools.*;

public class PowerListener implements Listener
{
	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent event)
	{
		Level.onPlayerDamage(event);//满足等级
		Class.onPlayerDamage(event);//满足职业
		Evasion.run(event);
		LifeSteal.run(event);//加血
		Damages.run(event);//秒杀
		Damage.run(event);//伤害
		Damagex.run(event);//伤害增加
		DamageSpeed.run(event);//多次伤害
		CriticalChance.run(event);//额外伤害
		Armor.run(event);//减伤
	}
	
	@EventHandler
	public void onEquip(InventoryClickEvent event)
	{
		Health.freshHealth((Player)event.getWhoClicked());//血量
	}
	@EventHandler
	public void onLeftClick(PlayerInteractEntityEvent event)
	{
		Boom.run(event);//爆炸声
	}
	@EventHandler
	public void on(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		if(player.hasPermission("xsjrpg.admin.drop"))
		{
			return;
		}
		player.sendMessage(TString.Prefix("新世纪RPG",4)+"禁止丢弃物品，输入/ljt打开垃圾桶(箱子)以丢弃");
		event.setCancelled(true);
	}
}
