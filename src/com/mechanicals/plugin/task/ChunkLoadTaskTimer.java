package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;

public class ChunkLoadTaskTimer extends MechRunnable {

	public static boolean shouldReload = false;
	Set<Location> toLoad = new HashSet<>();
	Set<Location> tempLocs = new HashSet<>();
	private final boolean shouldGenerate;
	
	public ChunkLoadTaskTimer() {
		super();
		shouldGenerate = plugin.blockData.getBoolean("block.chunkLoader.generateNewChunks", true);
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		for (Location c : toLoad) {
			if (!c.getChunk().isLoaded()) {
				c.getChunk().load(shouldGenerate);
			}
		}
		for (Location c : tempLocs) {
			if (c.getChunk().isLoaded()) {
				c.getChunk().unload(true);
			}
			tempLocs.remove(c);
		}
	}
	
	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.CHUNK_LOADER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		for (Location loc : toLoad) {
			tempLocs.add(loc);
		}
		toLoad.clear();
		for (String key : newKeys) {
			Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
			for (Location loc : tempLocs) {
				if (a.getBlock().getLocation().equals(a.getBlock().getLocation())) {
					tempLocs.remove(loc);
				}
			}
			toLoad.add(a);
		}
		shouldReload = false;
	}

}
