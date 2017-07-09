package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.particle.ParticleManager;
import com.mechanicals.plugin.server.MechRunnable;

public class ParticleSpawnerTaskTimer extends MechRunnable {

	private Set<Location> mechBlocks = new HashSet<>();
	public static boolean shouldReload = false;
	
	public ParticleSpawnerTaskTimer() {
		reload();
	}
	
	@Override
	public void run() {
		for (Location l : mechBlocks) {
			ParticleManager.showBaseParticles(Particle.VILLAGER_HAPPY, l.getWorld(), l.getBlockX(), l.getBlockY(), l.getBlockZ());
		}
		if (shouldReload) reload();
	}
	
	public void reload() {
		mechBlocks.clear();
		Set<String> keys = plugin.placed.getKeys(false);
		for (String key : keys) {
			if (plugin.blockData.getBoolean("block.blockBreaker.showParticles") && MechanicalBlocks.BLOCK_BREAKER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.blockPlacer.showParticles") && MechanicalBlocks.BLOCK_PLACER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.treeCutter.showParticles") && MechanicalBlocks.TREE_CUTTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.entityTeleporter.showParticles") && MechanicalBlocks.ENTITY_TELEPORTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.itemTeleporter.showParticles") && MechanicalBlocks.ITEM_TELEPORTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.grinder.showParticles") && MechanicalBlocks.GRINDER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.largeTeleporter.showParticles") && MechanicalBlocks.LARGE_TELEPORTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
				if (plugin.placed.contains(key + ".destWorld")) mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".destWorld")), plugin.placed.getInt(key + ".destX"), plugin.placed.getInt(key + ".destY") + 1, plugin.placed.getInt(key + ".destZ")));
			} else if (plugin.blockData.getBoolean("block.chunkLoader.showParticles") && MechanicalBlocks.CHUNK_LOADER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.elevator.showParticles") && MechanicalBlocks.ELEVATOR.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.animalGrowth.showParticles") && MechanicalBlocks.ANIMAL_GROWTH.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.plantFarmer.showParticles") && MechanicalBlocks.PLANT_FARMER.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			} else if (plugin.blockData.getBoolean("block.generator.showParticles") && MechanicalBlocks.GENERATOR.getId().equals(plugin.placed.getString(key + ".id"))) {
				mechBlocks.add(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y") + 1, plugin.placed.getInt(key + ".z")));
			}
		}
		
		shouldReload = false;
	}

}
