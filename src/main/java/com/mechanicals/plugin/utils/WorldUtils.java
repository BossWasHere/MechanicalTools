package com.mechanicals.plugin.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class WorldUtils {

	public static Location setBlockLocationToMiddle(Location location) {
		double x = location.getBlockX() + 0.5;
		double z = location.getBlockZ() + 0.5;
		location.setX(x);
		location.setZ(z);
		return location;
	}

	public static Set<Location> getBlocksSurrounding(Location location) {
		int x = location.getBlockX(), y = location.getBlockY(), z = location.getBlockZ();
		Set<Location> surround = new HashSet<>();
		surround.add(new Location(location.getWorld(), x + 1, y, z));
		surround.add(new Location(location.getWorld(), x - 1, y, z));
		surround.add(new Location(location.getWorld(), x, y + 1, z));
		surround.add(new Location(location.getWorld(), x, y - 1, z));
		surround.add(new Location(location.getWorld(), x, y, z + 1));
		surround.add(new Location(location.getWorld(), x, y, z - 1));
		surround.add(new Location(location.getWorld(), x + 1, y, z + 1));
		surround.add(new Location(location.getWorld(), x - 1, y, z - 1));
		surround.add(new Location(location.getWorld(), x - 1, y, z + 1));
		surround.add(new Location(location.getWorld(), x + 1, y, z - 1));
		surround.add(new Location(location.getWorld(), x + 1, y + 1, z));
		surround.add(new Location(location.getWorld(), x + 1, y - 1, z));
		surround.add(new Location(location.getWorld(), x - 1, y + 1, z));
		surround.add(new Location(location.getWorld(), x - 1, y - 1, z));
		surround.add(new Location(location.getWorld(), x, y + 1, z + 1));
		surround.add(new Location(location.getWorld(), x, y - 1, z - 1));
		surround.add(new Location(location.getWorld(), x, y + 1, z - 1));
		surround.add(new Location(location.getWorld(), x, y - 1, z + 1));
		return surround;
	}

	public static boolean isSameLocation(Location a, Location b) {
		return a.getBlockX() == b.getBlockX() && a.getBlockY() == b.getBlockY() && a.getBlockZ() == b.getBlockZ() && a.getWorld() == b.getWorld();
	}

	public static List<Location> getBlocksUpY(World world, int x, int z, Material blockType) {
		List<Location> locs = new ArrayList<>();
		for (int y = 1; y < 256; y++) {
			if (world.getBlockAt(x, y, z).getType().equals(blockType)) {
				locs.add(new Location(world, x, y, z));
			}
		}
		return locs;
	}

	public static boolean isYCloser(Location start, Location isCloser, Location than) {
		if (getYDistance(start, isCloser) <= getYDistance(start, than)) {
			return true;
		} else {
			return false;
		}
	}

	public static int getYDistance(Location a, Location b) {
		int r = a.getBlockY() - b.getBlockY();
		if (r < 0)
			r *= -1;
		return r;
	}

	public static List<Location> getBlocksInCuboid(Location l1, Location l2) {

		List<Location> blocks = new ArrayList<>();

		int topBlockX = (l1.getBlockX() < l2.getBlockX() ? l2.getBlockX() : l1.getBlockX());

		int bottomBlockX = (l1.getBlockX() > l2.getBlockX() ? l2.getBlockX() : l1.getBlockX());

		int topBlockY = (l1.getBlockY() < l2.getBlockY() ? l2.getBlockY() : l1.getBlockY());

		int bottomBlockY = (l1.getBlockY() > l2.getBlockY() ? l2.getBlockY() : l1.getBlockY());

		int topBlockZ = (l1.getBlockZ() < l2.getBlockZ() ? l2.getBlockZ() : l1.getBlockZ());

		int bottomBlockZ = (l1.getBlockZ() > l2.getBlockZ() ? l2.getBlockZ() : l1.getBlockZ());

		for (int x = bottomBlockX; x <= topBlockX; x++) {

			for (int y = bottomBlockY; y <= topBlockY; y++) {

				for (int z = bottomBlockZ; z <= topBlockZ; z++) {

					Location block = l1.getWorld().getBlockAt(x, y, z).getLocation();

					blocks.add(block);

				}

			}

		}
		return blocks;

	}
}
