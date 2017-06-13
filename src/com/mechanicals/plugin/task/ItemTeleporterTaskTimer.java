package com.mechanicals.plugin.task;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.scheduler.BukkitRunnable;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.obj.BlockFacingPartion;
import com.mechanicals.plugin.utils.WorldUtils;

public class ItemTeleporterTaskTimer extends BukkitRunnable {

	final MechMain plugin;
	private Set<BlockFacingPartion> annex = new HashSet<>();
	public static boolean shouldReload = false;
	
	public ItemTeleporterTaskTimer(MechMain plugin) {
		this.plugin = plugin;
		reload();
	}
	
	@Override
	public void run() {
		if (shouldReload) reload();
		for (BlockFacingPartion partion : annex) {
			Entity[] entities = partion.getEntitiesInPartion(plugin.blockData.getInt("block.itemTeleporter.radius"), true);
			for (Entity e : entities) {
				if (e.getType().equals(EntityType.DROPPED_ITEM)) e.teleport(WorldUtils.setBlockLocationToMiddle(partion.getSecondaryLocation()));
			}
		}
	}
	
	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.ITEM_TELEPORTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		annex.clear();
		for (String key : newKeys) {
			Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
			Location b = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".targetX"), plugin.placed.getInt(key + ".targetY"), plugin.placed.getInt(key + ".targetZ"));
			BlockFace c = BlockFacingPartion.getFaceFromName(plugin.placed.getString(key + ".face"));
			annex.add(new BlockFacingPartion(a, b, c));
		}
		shouldReload = false;
	}

}
