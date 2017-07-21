package com.mechanicals.plugin.blocks;

import java.util.Set;

import javax.management.modelmbean.InvalidTargetObjectTypeException;

import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Dispenser;
import org.bukkit.material.MaterialData;

import com.mechanicals.plugin.task.BlockPlaceTaskTimer;
import com.mechanicals.plugin.task.ParticleSpawnerTaskTimer;
import com.mechanicals.plugin.utils.StringUtils;

public class BlockPlacer extends BaseMechanicalBlock {
	
	public BlockPlacer() {
		super();
	}
	
	@Override
	public void blockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (!plugin.blockPlacerEnabled) {
			player.sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (player.hasPermission(placePerm())) {
			Set<String> keys = plugin.placed.getKeys(false);
			int i = 0;
			for (String key : keys) {
				int j = StringUtils.parseInt(key);
				if (i < j) i = j;
			}
			i++;
			
			plugin.placed.set(i + ".id", getMechBlock().getId());
			plugin.placed.set(i + ".world", event.getBlock().getWorld().getName());
			plugin.placed.set(i + ".player", event.getPlayer().getUniqueId().toString());
			plugin.placed.set(i + ".x", event.getBlock().getX());
			plugin.placed.set(i + ".y", event.getBlock().getY());
			plugin.placed.set(i + ".z", event.getBlock().getZ());
			
			MaterialData data = event.getBlock().getState().getData();
			if (!(data instanceof Dispenser)) {
				new InvalidTargetObjectTypeException("Block Placement has encountered a severe error");
				return;
			}
			BlockFace facing = ((Dispenser)data).getFacing();
			int targetX = event.getBlock().getX(), targetY = event.getBlock().getY(), targetZ = event.getBlock().getZ();
			switch (facing) {
			default:
			case UP:
				targetY++;
				break;
			case DOWN:
				targetY--;
				break;
			case NORTH:
				targetZ--;
				break;
			case EAST:
				targetX++;
				break;
			case SOUTH:
				targetZ++;
				break;
			case WEST:
				targetX--;
				break;
			}
			
			plugin.placed.set(i + ".targetX", targetX);
			plugin.placed.set(i + ".targetY", targetY);
			plugin.placed.set(i + ".targetZ", targetZ);
			
			plugin.placed.saveAndReload();
			
			player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
			updateRunnables();
		} else {
			player.sendMessage(plugin.texts.noPermission);
			event.getBlock().breakNaturally();
		}
	}

	@Override
	public MechanicalBlocks getMechBlock() {
		return MechanicalBlocks.BLOCK_PLACER;
	}

	@Override
	public void blockBreakEvent(BlockBreakEvent event) {
		if (!plugin.blockPlacerEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (!event.getPlayer().hasPermission(breakPerm())) {
			event.getPlayer().sendMessage(plugin.texts.noPermissionToBreak);
			event.setCancelled(true);
			return;
		}
		Set<String> keys = plugin.placed.getKeys(false);
		boolean reload = false;
		for (String key : keys) {
			if (plugin.placed.getString(key + ".world").equalsIgnoreCase(event.getBlock().getWorld().getName())) {
				if (plugin.placed.getInt(key + ".x") == event.getBlock().getX()) {
					if (plugin.placed.getInt(key + ".y") == event.getBlock().getY()) {
						if (plugin.placed.getInt(key + ".z") == event.getBlock().getZ()) {
							if (!plugin.placed.getString(key + ".player").equalsIgnoreCase(event.getPlayer().getUniqueId().toString())) {
								if (!event.getPlayer().hasPermission(breakOtherPerm())) {
									event.getPlayer().sendMessage(plugin.texts.noPermissionToBreakOther);
									return;
								}
							}
							plugin.placed.set(key, null);
							event.getPlayer().sendMessage(plugin.texts.breakSuccess);
							reload = true;
						}
					}
				}
			}
		}
		if (reload) {
			plugin.placed.saveAndReload();
			updateRunnables();
		}
	}

	@Override
	public void updateRunnables() {
		BlockPlaceTaskTimer.shouldReload = true;
		ParticleSpawnerTaskTimer.shouldReload = true;
	}
	
}
