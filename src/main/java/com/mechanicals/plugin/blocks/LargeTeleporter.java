package com.mechanicals.plugin.blocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.mechanicals.plugin.task.ParticleSpawnerTaskTimer;
import com.mechanicals.plugin.utils.StringUtils;

public class LargeTeleporter extends BaseMechanicalBlock {
	
	private Map<String, String> placement = new HashMap<>();
	
	public LargeTeleporter() {
		super();
	}
	
	@Override
	public void blockPlaceEvent(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (!plugin.largeTeleporterEnabled) {
			player.sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (player.hasPermission(placePerm())) {
			if (placement.containsKey(player.getName())) {
				String pre = placement.get(player.getName());
				plugin.placed.set(pre + ".destWorld", event.getBlock().getWorld().getName());
				plugin.placed.set(pre + ".destX", event.getBlock().getX());
				plugin.placed.set(pre + ".destY", event.getBlock().getY());
				plugin.placed.set(pre + ".destZ", event.getBlock().getZ());
				plugin.placed.saveAndReload();
				player.sendMessage(ChatColor.LIGHT_PURPLE + "Linked teleporters successfully");
				placement.remove(player.getName());
				return;
			}
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
			
			player.sendMessage(ChatColor.LIGHT_PURPLE + "Please place your destination block");
			placement.put(player.getName(), i + "");
			player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
			updateRunnables();
		} else {
			player.sendMessage(plugin.texts.noPermission);
			event.getBlock().breakNaturally();
		}
	}

	@Override
	public MechanicalBlocks getMechBlock() {
		return MechanicalBlocks.LARGE_TELEPORTER;
	}

	@Override
	public void blockBreakEvent(BlockBreakEvent event) {
		if (!plugin.largeTeleporterEnabled) {
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
			} else if (plugin.placed.getString(key + ".destWorld").equalsIgnoreCase(event.getBlock().getWorld().getName())) {
				if (plugin.placed.getInt(key + ".destX") == event.getBlock().getX()) {
					if (plugin.placed.getInt(key + ".destY") == event.getBlock().getY()) {
						if (plugin.placed.getInt(key + ".destZ") == event.getBlock().getZ()) {
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
		super.updateRunnables();
		ParticleSpawnerTaskTimer.shouldReload = true;
	}
	
	public String getUsePerm() {
		return plugin.permissions.get(getMechBlock().getId() + "_use");
	}
	
	public String getUseOtherPerm() {
		return plugin.permissions.get(getMechBlock().getId() + "_useOther");
	}

}
