package com.mechanicals.plugin.items;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.mechanicals.plugin.task.extra.ConfirmCooldown;
import com.mechanicals.plugin.utils.EntityUtils;

public class Flamethrower extends BaseMechanicalItem {

	public Flamethrower() {
		super();
	}

	@Override
	public MechanicalItems getMechItem() {
		return MechanicalItems.FLAMETHROWER;
	}

	@Override
	public void itemUseEvent(PlayerInteractEvent event) {
		if (!plugin.flamethrowerEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (event.getPlayer().hasPermission(usePerm())) {
			for (ConfirmCooldown c : plugin.cooldowns) {
				if (c.checkID(event.getPlayer().getName(), 16)) {
					if (!c.isCooled()) {
						return;
					}
					plugin.cooldowns.remove(c);
				}
			}
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
				EntityUtils.shootFireballProjectile(event.getPlayer());
				
				ConfirmCooldown c = new ConfirmCooldown(event.getPlayer().getName(), "", 16, plugin.itemData.getInt("item.flamethrower.cooldown", 2));
				c.runTaskTimerAsynchronously(plugin, 0, 20);
				plugin.cooldowns.add(c);
			} else {
				
			}
		} else {
			event.getPlayer().sendMessage(plugin.texts.noPermissionUse);
		}
	}
}
