package com.mechanicals.plugin.blocks;

import java.util.Set;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.task.ParticleSpawnerTaskTimer;
import com.mechanicals.plugin.utils.StringUtils;

public class Elevator extends BaseMechanicalBlock {
	
	public Elevator(MechMain plugin) {
		super(plugin);
	}
	
	@Override
	public void blockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (!plugin.elevatorEnabled) {
			player.sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (player.hasPermission(plugin.permissions.elevator_place)) {
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
		return MechanicalBlocks.ELEVATOR;
	}

	@Override
	public void blockBreakEvent(BlockBreakEvent event) {
		if (!plugin.elevatorEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (!event.getPlayer().hasPermission(plugin.permissions.elevator_break)) {
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
								if (!event.getPlayer().hasPermission(plugin.permissions.elevator_breakOther)) {
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
		ParticleSpawnerTaskTimer.shouldReload = true;
	}

}
