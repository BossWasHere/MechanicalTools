package com.mechanicals.plugin.items;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.InventoryHandler;
import com.mechanicals.plugin.item.Items;
import com.mechanicals.plugin.task.extra.ConfirmCooldown;
import com.mechanicals.plugin.utils.BlockUtils;

public class DyeWand extends BaseMechanicalItem {

	public DyeWand() {
		super();
	}

	@Override
	public MechanicalItems getMechItem() {
		return MechanicalItems.DYE_WAND;
	}

	@Override
	public void itemUseEvent(PlayerInteractEvent event) {
		if (!plugin.dyeWandEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (event.getPlayer().hasPermission(usePerm())) {
			for (ConfirmCooldown c : plugin.cooldowns) {
				if (c.checkID(event.getPlayer().getName(), 14)) {
					if (!c.isCooled()) {
						return;
					}
					plugin.cooldowns.remove(c);
				}
			}
			if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_AIR) {
				event.getPlayer().openInventory(InventoryHandler.loadDyeInventoryForPlayer(event.getPlayer()));
				ConfirmCooldown c = new ConfirmCooldown(event.getPlayer().getName(), "", 14, plugin.itemData.getInt("item.dyeWand.cooldown", 3));
				c.runTaskTimerAsynchronously(plugin, 0, 20);
				plugin.cooldowns.add(c);
			} else {
				Block block = event.getClickedBlock();
				if (block != null) {
					if (Items.isColourable(block)) {
						ItemStack dye = InventoryHandler.getDyeForPlayer(event.getPlayer());
						if (dye.getType().equals(Material.AIR)) {
							event.getPlayer().sendMessage(plugin.texts.addDyeFirst);
							return;
						}
						if (!dye.getType().equals(Material.INK_SACK)) return;
						if (BlockUtils.getData(block) == (byte) Items.getReverseDyeColor(dye.getDurability())) return;
						
						switch (block.getType()) {
						case HARD_CLAY:
							BlockUtils.setBlockAndData(block, Material.STAINED_CLAY, (byte) Items.getReverseDyeColor(dye.getDurability()), false);
							break;
						case GLASS:
							BlockUtils.setBlockAndData(block, Material.STAINED_GLASS, (byte) Items.getReverseDyeColor(dye.getDurability()), false);
							break;
						case THIN_GLASS:
							BlockUtils.setBlockAndData(block, Material.STAINED_GLASS_PANE, (byte) Items.getReverseDyeColor(dye.getDurability()), false);
							break;
						case WOOL:
						case STAINED_CLAY:
						case STAINED_GLASS:
						case STAINED_GLASS_PANE:
						case CONCRETE:
						case CONCRETE_POWDER:
						//Issue with coloring beds; waiting for new metadata handling
/*						case BED:
						case BED_BLOCK:				*/
						case CARPET:
							BlockUtils.setData(block, (byte)Items.getReverseDyeColor(dye.getDurability()));
							break;
						default:
							return;
						}
						if (dye.getAmount() > 1) {
							dye.setAmount(dye.getAmount() - 1);
							InventoryHandler.setDyeForPlayer(event.getPlayer(), dye);
						} else {
							InventoryHandler.setDyeForPlayer(event.getPlayer(), new ItemStack(Material.AIR));
						}
					}
				}
			}
		} else {
			event.getPlayer().sendMessage(plugin.texts.noPermissionUse);
		}
	}
}
