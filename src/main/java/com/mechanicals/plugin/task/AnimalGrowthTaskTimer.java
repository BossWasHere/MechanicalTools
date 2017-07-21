package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Entity;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;
import com.mechanicals.plugin.utils.EntityUtils;

public class AnimalGrowthTaskTimer extends MechRunnable {

	private Set<Location> locations = new HashSet<>();
	private int radius;
	public static boolean shouldReload = false;

	public AnimalGrowthTaskTimer() {
		super();
		radius = MechMain.plugin.blockData.getInt("block.animalGrowth.radius", 5);
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		for (Location l : locations) {
			for (Entity e : EntityUtils.getNearbyEntities(l, radius, false)) {
				if (e instanceof Ageable) {
					Ageable a = (Ageable) e;
					if (a.isAdult()) continue;
					a.setAgeLock(false);
					a.setAge(a.getAge() + 2000);
				}
			}
		}
	}

	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.ANIMAL_GROWTH.getId().equals(plugin.placed.getString(key + ".id"))) {
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
