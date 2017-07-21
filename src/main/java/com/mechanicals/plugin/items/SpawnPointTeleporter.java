package com.mechanicals.plugin.items;

import org.bukkit.ChatColor;
import org.bukkit.event.player.PlayerInteractEvent;

import com.mechanicals.plugin.task.extra.ConfirmCooldown;

public class SpawnPointTeleporter extends BaseMechanicalItem {

	public SpawnPointTeleporter() {
		super();
	}

	@Override
	public MechanicalItems getMechItem() {
		return MechanicalItems.BED_TELEPORT;
	}

	@Override
	public void itemUseEvent(PlayerInteractEvent event) {
		if (!plugin.spawnPointTeleporterEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (event.getPlayer().hasPermission(usePerm())) {
			boolean cooldown = false;
			for (ConfirmCooldown c : plugin.cooldowns) {
				if (c.checkID(event.getPlayer().getName(), 11)) {
					if (!c.isCooled()) {
						return;
					}
					synchronized (plugin.cooldowns) {
						plugin.cooldowns.remove(c);
					}
				}
				if (c.checkID(event.getPlayer().getName(), 12)) {
					cooldown = true;
					if (!c.isCooled()) {
						event.getPlayer().sendMessage(plugin.texts.bedTeleport_Use);
						event.getPlayer().teleport(event.getPlayer().getBedSpawnLocation() == null ? event.getPlayer().getWorld().getSpawnLocation() : event.getPlayer().getBedSpawnLocation());
						ConfirmCooldown cd = new ConfirmCooldown(event.getPlayer().getName(), "", 11, plugin.itemData.getInt("item.spawnPointTeleporter.cooldown", 3));
						cd.runTaskTimerAsynchronously(plugin, 0, 20);
						synchronized (plugin.cooldowns) {
							plugin.cooldowns.remove(c);
							plugin.cooldowns.add(cd);
						}
					} else {
						event.getPlayer().sendMessage(ChatColor.AQUA + "Your request has expired!");
						synchronized (plugin.cooldowns) {
							plugin.cooldowns.remove(c);
						}
					}
					break;
				}
			}
			if (!cooldown) {
				event.getPlayer().sendMessage(ChatColor.AQUA + "Please click with this item again to confirm (5s left)");
				ConfirmCooldown cd = new ConfirmCooldown(event.getPlayer().getName(), "", 12, 5);
				cd.runTaskTimerAsynchronously(plugin, 0, 20);
				plugin.cooldowns.add(cd);
			}
		} else {
			event.getPlayer().sendMessage(plugin.texts.noPermissionUse);
		}
	}

}
