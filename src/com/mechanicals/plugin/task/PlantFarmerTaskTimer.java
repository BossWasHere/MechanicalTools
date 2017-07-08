package com.mechanicals.plugin.task;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Crops;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;
import com.mechanicals.plugin.utils.WorldUtils;

public class PlantFarmerTaskTimer extends MechRunnable {

	private Set<Location> locations = new HashSet<>();
	private int radius;
	public static boolean shouldReload = false;

	public PlantFarmerTaskTimer() {
		super();
		radius = plugin.blockData.getInt("block.plantFarmer.radius", 5);
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		for (Location l : locations) {
			Location start = new Location(l.getWorld(), l.getBlockX() - radius, l.getBlockY() - radius, l.getBlockZ() - radius);
			Location end = new Location(l.getWorld(), l.getBlockX() + radius, l.getBlockY() + radius, l.getBlockZ() + radius);
			List<Location> blocks = WorldUtils.getBlocksInCuboid(start, end);
			for (Location loc : blocks) {
				if (loc.getBlock().getType().equals(Material.CROPS) || loc.getBlock().getType().equals(Material.POTATO)
						|| loc.getBlock().getType().equals(Material.CARROT) || loc.getBlock().getType().equals(Material.BEETROOT)) {
					if (loc.getBlock().getState().getData() instanceof Crops) {
						boolean shouldBreak = false;
						BlockState s = loc.getBlock().getState();
						Crops crop = (Crops) s.getData();
						CropState state = crop.getState();
						switch (state) {
						case SEEDED:
							state = CropState.GERMINATED;
							break;
						case GERMINATED:
							state = CropState.VERY_SMALL;
							break;
						case VERY_SMALL:
							state = CropState.SMALL;
							break;
						case SMALL:
							state = CropState.MEDIUM;
							break;
						case MEDIUM:
							state = CropState.TALL;
							break;
						case TALL:
							state = CropState.VERY_TALL;
							break;
						case VERY_TALL:
							state = CropState.RIPE;
							break;
						case RIPE:
							shouldBreak = true;
							break;
						}
						if (shouldBreak) {
							state = CropState.SEEDED;
							Collection<ItemStack> drops = loc.getBlock().getDrops();
							for (ItemStack item : drops) loc.getWorld().dropItemNaturally(loc, item);
						}
						crop.setState(state);
						s.update(true);
					}
				}
			}
		}
	}

	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.PLANT_FARMER.getId().equals(plugin.placed.getString(key + ".id"))) {
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
