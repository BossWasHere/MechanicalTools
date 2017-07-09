package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;

public class GeneratorTaskTimer extends MechRunnable {

	private Set<String> keys = new HashSet<>();
	public static boolean shouldReload = false;
	
	public GeneratorTaskTimer() {
		super();
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		boolean shouldSave = false;
		for (String key : keys) {
			double fuel = plugin.placed.getDouble(key + ".fuel");
			if (fuel < 1) continue;
			fuel--;
			plugin.placed.set(key + ".fuel", fuel);
			plugin.placed.set(key + ".power", plugin.placed.getDouble(key + ".power") + 1);
			shouldSave = true;
		}
		if (shouldSave) plugin.placed.saveAndReload();
	}
	
	public void reload() {
		Set<String> newKeys = plugin.placed.getKeys(false);
		keys.clear();
		for (String key : newKeys) {
			if (MechanicalBlocks.GENERATOR.getId().equals(plugin.placed.getString(key + ".id"))) {
				keys.add(key);
			}
		}
		shouldReload = false;
	}

}
