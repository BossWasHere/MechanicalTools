package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;

public class AutoCrafterTaskTimer extends MechRunnable {

	private Set<Location> locations = new HashSet<>();
	public static boolean shouldReload = false;
	
	public AutoCrafterTaskTimer() {
		super();
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
	}
	
	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.AUTO_CRAFTER.getId().equals(plugin.placed.getString(key + ".id"))) {
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
