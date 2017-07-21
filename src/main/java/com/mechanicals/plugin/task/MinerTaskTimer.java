package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;
import com.mechanicals.plugin.utils.WorldUtils;

public class MinerTaskTimer extends MechRunnable {

	private Set<Location> locations = new HashSet<>();
	private Set<Location> generators = new HashSet<>();
	public static boolean shouldReload = false;
	
	public MinerTaskTimer() {
		super();
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		boolean ranOnce = false;
		for (Location loc : locations) {
			//int blocksToBreak = 0;
			Set<Location> surrounding = WorldUtils.getBlocksSurrounding(loc);
			for (Location generator : surrounding) {
				if (generator.getBlock().getType().equals(Material.FURNACE) || generator.getBlock().getType().equals(Material.BURNING_FURNACE)) {
					ranOnce = true;
				}
			}
		}
		if (ranOnce) plugin.placed.saveAndReload();
	}
	
	public void reload() {
		Set<String> newKeys = plugin.placed.getKeys(false);
		locations.clear();
		for (String key : newKeys) {
			if (MechanicalBlocks.MINER.getId().equals(plugin.placed.getString(key + ".id"))) {
				Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
				locations.add(a);
			}
		}
		shouldReload = false;
	}
	
	public void cloneGeneratorReferences(Set<Location> gens) {
		generators.clear();
		if (gens == null) return;
		for (Location g : gens) {
			generators.add(new Location(g.getWorld(), g.getX(), g.getY(), g.getZ()));
		}
	}

}
