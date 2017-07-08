package com.mechanicals.plugin.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

public class EntityUtils {
	
	public static Collection<Entity> getNearbyEntities(Location l, int radius, boolean half) {
		if (radius < 1) radius = 1;
		return l.getWorld().getNearbyEntities(l, half ? radius / 2 : radius, half ? radius / 2 : radius, half ? radius / 2 : radius);
	}
	
	public static Set<Player> getPlayersAboveBlock(Location block) {
		block.setY(block.getY() + 1);
		Collection<Entity> e = EntityUtils.getNearbyEntities(block, 1, false);
		Set<Player> set = new HashSet<>();
		for (Entity ent : e) { if (ent instanceof Player) set.add((Player)ent); }
		return set;
	}
	
	public static void shootFireballProjectile(Player player) {
		Fireball fireball = player.launchProjectile(Fireball.class);
		fireball.setVelocity(fireball.getVelocity().multiply(10));
		fireball.setYield(3F);
		fireball.setIsIncendiary(true);
	}

	public static List<String> getOnlinePlayersWithPriority(CommandSender sender) {
		List<String> online = new ArrayList<>();
		if (sender instanceof Player) {
			online.add(((Player)sender).getName());
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (!online.contains(p.getName())) online.add(p.getName());
			}
		} else {
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				online.add(p.getName());
			}
		}
		return online;
	}
	
	public static Player getPlayer(String name) {
		Player target = null;
		for (Player p : Bukkit.getOnlinePlayers()) {
			if (p.getName().equalsIgnoreCase(name)) target = p;
		}
		return target;
	}
}
