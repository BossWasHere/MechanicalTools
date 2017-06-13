package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;

public class TreeCutterTaskTimer extends BukkitRunnable {

	final MechMain plugin;
	private Set<Location> locations = new HashSet<>();
	private Set<Location> droppers = new HashSet<>();
	private int max;
	private int i = 0;
	private Set<Location> locs = new HashSet<>();
	public static boolean shouldReload = false;
	
	public TreeCutterTaskTimer(MechMain plugin) {
		this.plugin = plugin;
		int maxTemp = plugin.blockData.getInt("block.treeCutter.maxBlocksAtOnce");
		if (maxTemp < 1) max = 5;
		else max = maxTemp;
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		for (Location location : locations) {
			if (location.getBlock().getType() == Material.LOG || location.getBlock().getType() == Material.LOG_2) {
				addNeighbours(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
			}
			i = 0;
		}
		if (plugin.debug) plugin.getLogger().log(Level.INFO, "---> Task: TreeCutter Running /// Blocks: Fetched " + droppers.size() + " /// Logs: Fetched " + locs.size(), this);
		if (plugin.debug) plugin.getLogger().log(Level.INFO, "---> Task: TreeCutter /// Max Break: " + max, this);
		

		for (Location loc : locs) {
			loc.getBlock().breakNaturally();
		}
		for (Location loc : droppers) {
			if (loc.getBlock().getType() != Material.DROPPER) {
				shouldReload = true;
				break;
			}
		}
		if (shouldReload) reload();
		locs.clear();
	}
	
	public void reload() {
		if (plugin.debug) plugin.getLogger().log(Level.INFO, "---> Task: TreeCutter Reloading", this);
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.TREE_CUTTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		
		locations.clear();
		droppers.clear();
		for (String key : newKeys) {
			Location b = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
			Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".targetX"), plugin.placed.getInt(key + ".targetY"), plugin.placed.getInt(key + ".targetZ"));
			locations.add(a);
			droppers.add(b);
		}
		
		shouldReload = false;
		
	}
	
	private void addNeighbours(final World world, final int x, final int y, final int z) {
		if (i < max) {
			locs.add(new Location(world, x, y, z));
			i++;
			if (i < max && isWood(world.getBlockAt(x + 1, y, z)) && !isAdded(world, x + 1, y, z)) {
				addNeighbours(world, x + 1, y, z);
			}
			if (i < max && isWood(world.getBlockAt(x - 1, y, z)) && !isAdded(world, x - 1, y, z)) {
				addNeighbours(world, x - 1, y, z);
			}
			if (i < max && isWood(world.getBlockAt(x, y + 1, z)) && !isAdded(world, x, y + 1, z)) {
				addNeighbours(world, x, y + 1, z);
			}
			if (i < max && isWood(world.getBlockAt(x, y - 1, z)) && !isAdded(world, x, y - 1, z)) {
				addNeighbours(world, x, y - 1, z);
			}
			if (i < max && isWood(world.getBlockAt(x, y, z + 1)) && !isAdded(world, x, y, z + 1)) {
				addNeighbours(world, x, y, z + 1);
			}
			if (i < max && isWood(world.getBlockAt(x, y, z - 1)) && !isAdded(world, x, y, z - 1)) {
				addNeighbours(world, x, y, z - 1);
			}
		}
	}
	
	private boolean isWood(Block block) {
		if (block.getType() == Material.LOG || block.getType() == Material.LOG_2) return true;
		return false;
	}
	
	private boolean isAdded(World world, int x, int y, int z) {
		for (Location l : locs) {
			if (l.getWorld().equals(world) && l.getBlockX() == x && l.getBlockY() == y && l.getBlockZ() == z) return true;
		}
		return false;
	}
	
}
