package com.mechanicals.plugin.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.item.Items;
import com.mechanicals.plugin.task.extra.ConfirmCooldown;

public class DyeWand extends BaseMechanicalItem {

	public DyeWand(MechMain plugin) {
		super(plugin);
	}

	@Override
	public MechanicalItems getMechItem() {
		return MechanicalItems.DYE_WAND;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void itemUseEvent(PlayerInteractEvent event) {
		if (!plugin.dyeWandEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (event.getPlayer().hasPermission(plugin.permissions.dyeWand_use)) {
			for (ConfirmCooldown c : plugin.cooldowns) {
				if (c.checkID(event.getPlayer().getName(), 14)) {
					if (!c.isCooled()) {
						return;
					}
					synchronized (plugin.cooldowns) {
						plugin.cooldowns.remove(c);
					}
				}
			}
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
				event.getPlayer().openInventory(plugin.inventoryHandler.loadDyeInventoryForPlayer(event.getPlayer()));
				ConfirmCooldown c = new ConfirmCooldown(event.getPlayer().getName(), "", 14, plugin.itemData.getInt("item.dyeWand.cooldown", 3));
				c.runTaskTimerAsynchronously(plugin, 0, 20);
				synchronized (plugin.cooldowns) {
					plugin.cooldowns.add(c);
				}
			} else {
				Block block = event.getClickedBlock();
				if (block != null) {
					if (Items.isColourable(block)) {
						ItemStack dye = plugin.inventoryHandler.getDyeForPlayer(event.getPlayer());
						if (dye.getType().equals(Material.AIR)) {
							event.getPlayer().sendMessage(plugin.texts.addDyeFirst);
							return;
						}
						if (!dye.getType().equals(Material.INK_SACK)) return;
						if (block.getData() == (byte) Items.getReverseDyeColor(dye.getDurability())) return;
						
						switch (block.getType()) {
						case HARD_CLAY:
							block.setTypeIdAndData(Material.STAINED_CLAY.getId(), (byte) Items.getReverseDyeColor(dye.getDurability()), false);
							break;
						case GLASS:
							block.setTypeIdAndData(Material.STAINED_GLASS.getId(), (byte) Items.getReverseDyeColor(dye.getDurability()), false);
							break;
						case THIN_GLASS:
							block.setTypeIdAndData(Material.STAINED_GLASS_PANE.getId(), (byte) Items.getReverseDyeColor(dye.getDurability()), false);
							break;
						case WOOL:
						case STAINED_CLAY:
						case STAINED_GLASS:
						case STAINED_GLASS_PANE:
						case CONCRETE:
						case CONCRETE_POWDER:
						//Issue with coloring beds; waiting for new metadata handling
						/*case BED:
						case BED_BLOCK:*/
						case CARPET:
							block.setData((byte)Items.getReverseDyeColor(dye.getDurability()));
							break;
						default:
							return;
						}
						if (dye.getAmount() > 1) {
							dye.setAmount(dye.getAmount() - 1);
							plugin.inventoryHandler.setDyeForPlayer(event.getPlayer(), dye);
						} else {
							plugin.inventoryHandler.setDyeForPlayer(event.getPlayer(), new ItemStack(Material.AIR));
						}
					}
				}
			}
		} else {
			event.getPlayer().sendMessage(plugin.texts.noPermissionUse);
		}
	}
}
