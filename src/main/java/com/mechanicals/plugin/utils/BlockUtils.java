package com.mechanicals.plugin.utils;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.item.Items;

public class BlockUtils {

	@SuppressWarnings("deprecation")
	public static byte getData(Block block) {
		return block.getData();
	}
	
	@SuppressWarnings("deprecation")
	public static void setBlockAndData(Block block, Material id, byte data, boolean update) {
		block.setTypeIdAndData(id.getId(), data, update);
	}
	
	@SuppressWarnings("deprecation")
	public static void setData(Block block, byte data) {
		block.setData(data);
	}
	
	public static boolean breakBlockAtPos(Location block, Location dropPos) {
		for (Location l : MechMain.mechanicalBlocks) {
			if (block.getBlockX() == l.getBlockX() && block.getBlockY() == l.getBlockY() && block.getBlockZ() == l.getBlockZ() && block.getWorld().getName().equalsIgnoreCase(l.getWorld().getName())) {
				return false;
			}
		}
		if (Items.hasBlockInventory(block)) block.getBlock().breakNaturally();
		else {
			block.getWorld().dropItem((dropPos != null ? dropPos : block), Items.getDropItem(block.getBlock().getType(), BlockUtils.getData(block.getBlock())));
			block.getBlock().setType(Material.AIR);
		}
		return true;
	}
	
	public static boolean breakBlock(Location block, List<String> blacklist) {
		for (String s : blacklist) {
			try {
				if (block.getBlock().getType().equals(Items.getMaterial(s).getType())) return false;
			} catch (Exception e) {}
		}
		return breakBlockAtPos(block, null);
	}
	
	public static boolean breakBlock(Location block, List<String> blacklist, Location dropPos) {
		for (String s : blacklist) {
			try {
				if (block.getBlock().getType().equals(Items.getMaterial(s).getType())) return false;
			} catch (Exception e) {}
		}
		return breakBlockAtPos(block, dropPos);
	}
	
}
