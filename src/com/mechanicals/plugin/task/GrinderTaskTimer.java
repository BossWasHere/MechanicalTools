package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.utils.EntityUtils;

public class GrinderTaskTimer extends BukkitRunnable {

	final MechMain plugin;
	private Set<Location> locations = new HashSet<>();
	private final double damage;
	private final boolean damagePlayers;
	public static boolean shouldReload = false;
	
	public GrinderTaskTimer(MechMain plugin) {
		this.plugin = plugin;
		damage = (float)plugin.blockData.getDouble("block.grinder.damage");
		damagePlayers = plugin.blockData.getBoolean("block.grinder.damagePlayers");
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		for (Location l : locations) {
			for (Entity e : EntityUtils.getNearbyEntities(l, 2, false)) {
				if ((e.getType() == EntityType.PLAYER && damagePlayers) || e.getType() != EntityType.DROPPED_ITEM) {
					if (e instanceof LivingEntity) {
						((LivingEntity)e).damage(damage);
					}
				}
			}
		}
	}
	
	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.GRINDER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		locations.clear();
		for (String key : newKeys) {
			Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
			locations.add(a);
		}
		shouldReload = false;
	}
	
}
