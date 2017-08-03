package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;
import com.mechanicals.plugin.utils.BlockUtils;
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
		for (Location loc : locations) {
			boolean power = false;
			Set<Location> surrounding = WorldUtils.getBlocksSurrounding(loc);
			for (Location generator : surrounding) {
				if (generator.getBlock().getType().equals(Material.FURNACE) || generator.getBlock().getType().equals(Material.BURNING_FURNACE)) {
					for (String key : plugin.placed.getKeys(false)) {
						if (!plugin.placed.getString(key + ".id").equalsIgnoreCase(plugin.generator.getMechBlock().getId())) continue;
						if (plugin.placed.getString(key + ".world").equalsIgnoreCase(generator.getWorld().getName())) {
							if (plugin.placed.getInt(key + ".x") == generator.getBlockX()) {
								if (plugin.placed.getInt(key + ".y") == generator.getBlockY()) {
									if (plugin.placed.getInt(key + ".z") == generator.getBlockZ()) {
										double p = plugin.placed.getDouble(key + ".power", 0.0);
										p -= plugin.miner.energyPerBlock;
										if (p < 0) continue;
										power = true;
										plugin.placed.set(key + ".power", p);
										plugin.placed.saveAndReload();
										break;
									}
								}
							}
						}
					}
				}
			}
			if (power) {
				List<Location> cuboid = WorldUtils.getBlocksInCuboid(new Location(loc.getWorld(), loc.getBlockX() - 1, 0, loc.getBlockZ() - 1), new Location(loc.getWorld(), loc.getBlockX() + 1, loc.getBlockY() - 1, loc.getBlockZ() + 1));
				for (Location l : cuboid) {
					if (l.getBlock().getType().equals(Material.AIR)) continue;
					if (BlockUtils.breakBlock(l, plugin.miner.blacklist, new Location(loc.getWorld(), loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ()))) break;
				}
			}
		}
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
