package com.github.xzzpig.xsjrpg;
import com.github.xzzpig.BukkitTools.*;
import org.bukkit.entity.*;

public class PsSolver
{
	public static String solve(String ps){
		return ps.replaceAll("&",TString.s);
	}
	public static String solve(String ps,Player killer,LivingEntity entity){
		ps = solve(ps);
		ps = ps.replaceAll("<Killer>",killer.getName());
		if(entity instanceof Player)
			ps.replaceAll("<Killed>",((Player)entity).getName());
		else
			ps.replaceAll("<Killed>",entity.getType().name());
		ps = ps.replaceAll("<World>",killer.getLocation().getWorld().getName()).
		replaceAll("<Item>",killer.getItemInHand().getItemMeta().getDisplayName()+"("+killer.getItemInHand().getType()+killer.getItemInHand().getTypeId()+")").
			replaceAll("<Loc>","("+killer.getLocation().getBlockX()+","+killer.getLocation().getBlockY()+","+killer.getLocation().getBlockY()+")");
		return ps;
	}
}
