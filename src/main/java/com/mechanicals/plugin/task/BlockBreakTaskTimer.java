package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;
import com.mechanicals.plugin.utils.BlockUtils;

public class BlockBreakTaskTimer extends MechRunnable {

	private Set<Location> droppers = new HashSet<>();
	private Set<Location> breakBlocks = new HashSet<>(); 
	public static boolean shouldReload = false;
	
	public BlockBreakTaskTimer() {
		super();
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		Iterator<Location> blocks = breakBlocks.iterator();
		Iterator<Location> dropper = droppers.iterator();
		while (blocks.hasNext()) {
			Location block = blocks.next();
			Location drop = dropper.next();
			if (drop.getBlock().getType() != Material.DROPPER) {
				shouldReload = true;
				continue;
			}
			BlockUtils.breakBlock(block, plugin.blockBreaker.blacklist);
		}
		if (shouldReload) reload();
	}
	
	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.BLOCK_BREAKER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		
		breakBlocks.clear();
		for (String key : newKeys) {
			Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".targetX"), plugin.placed.getInt(key + ".targetY"), plugin.placed.getInt(key + ".targetZ"));
			Location b = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
			breakBlocks.add(a);
			droppers.add(b);
		}
		
		shouldReload = false;
		
	}
	
}
