package com.mechanicals.plugin.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mechanicals.plugin.InventoryHandler;
import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.items.ITool;
import com.mechanicals.plugin.server.NoteBlockHandler;
import com.mechanicals.plugin.utils.StringUtils;
import com.xxmicloxx.NoteBlockAPI.NoteBlockPlayerMain;

public class InventoryEvent implements Listener {
	
	public InventoryEvent() {
		MechMain.plugin.getServer().getPluginManager().registerEvents(this, MechMain.plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void inventoryClickEvent(InventoryClickEvent event) {
		ItemStack current = event.getCurrentItem();
		if (!(event.getWhoClicked() instanceof Player)) return;
		if (current == null) return;
		if (!current.hasItemMeta()) {
			inventoryClickEventNoMeta(event);
			return;
		}
		Player player = (Player) event.getWhoClicked();
		String inventoryName = event.getInventory().getName();
		if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical Blocks]") || inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical Items]")) {
			player.getInventory().addItem(current);
			event.setCancelled(true);
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Radio") && MechMain.plugin.nbapi) {
			if (NoteBlockPlayerMain.isReceivingSong(player)) NoteBlockPlayerMain.stopPlaying(player);
			if (current.getItemMeta().getDisplayName().equalsIgnoreCase("Stop Song") && current.getType().equals(Material.REDSTONE_BLOCK)) {
				event.setCancelled(true);
				return;
			}
			if (!NoteBlockHandler.playNBS(player, current.getItemMeta().getDisplayName())) {
				player.sendMessage(MechMain.plugin.texts.songIssue);
			}
			event.setCancelled(true);
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] ITool")) {
			event.setCancelled(true);
			switch (current.getType()) {
			case REDSTONE_BLOCK:
				player.closeInventory();
				break;
			case WORKBENCH:
				player.closeInventory();
				if (player.hasPermission(MechMain.plugin.iTool.useCraftPerm())) {
					ITool.openCraftingPage(player);
				} else {
					player.sendMessage(MechMain.plugin.texts.noPermissionUse);
				}
				break;
			case ENCHANTMENT_TABLE:
				player.closeInventory();
				if (player.hasPermission(MechMain.plugin.iTool.useEnchantPerm())) {
					ITool.openEnchantmentPage(player);
				} else {
					player.sendMessage(MechMain.plugin.texts.noPermissionUse);
				}
				break;
			case ANVIL:
				player.closeInventory();
				if (player.hasPermission(MechMain.plugin.iTool.useAnvilPerm())) {
					ITool.openAnvilPage(player);
				} else {
					player.sendMessage(MechMain.plugin.texts.noPermissionUse);
				}
				break;
			case CHEST:
				player.closeInventory();
				if (player.hasPermission(MechMain.plugin.iTool.useInventoryPerm())) {
					MechMain.plugin.iTool.openPlayerInventory(player);
				} else {
					player.sendMessage(MechMain.plugin.texts.noPermissionUse);
				}
				break;
			default:
				break;
			}
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Dyes")) {
			if (!current.getType().equals(Material.INK_SACK)) event.setCancelled(true);
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Inventory")) {
			if (MechMain.plugin.iTool.matchesMeta(current)) event.setCancelled(true);
		} else if (StringUtils.countOccurances(inventoryName, " ") == 2 && inventoryName.endsWith("Inventory")) {
			if (!player.hasPermission(MechMain.plugin.permissions.get("remote_inventory_edit"))) {
				event.setCancelled(true);
				player.sendMessage(MechMain.plugin.texts.noPermissionRemoteEdit);
			}
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Generator") && event.getInventory().getItem(0).hasItemMeta()) {
			if (current.getItemMeta().hasLore() && current.getItemMeta().hasDisplayName() && event.getInventory().getItem(0).getItemMeta().hasLore()) {
				int configKey = StringUtils.getNumber(event.getInventory().getItem(0).getItemMeta().getLore().get(1));
				event.setCancelled(true);
				if (configKey == -1 || !MechMain.plugin.vault) {
					return;
				}
				switch (current.getType()) {
				case LAVA_BUCKET:
					if (current.getItemMeta().getDisplayName().equalsIgnoreCase("Add Lava from Economy")) {
						double d = MechMain.plugin.generator.energyMultiplier * 12.5;
						if (!(d < 0.0)) {
							if (MechMain.plugin.economyManager.getBalanceForUser(player) < d) {
								player.sendMessage(MechMain.plugin.texts.notEnoughEconomy);
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
								return;
							}
							double output = MechMain.plugin.placed.getDouble(configKey + ".fuel") + d;
							MechMain.plugin.placed.set(configKey + ".fuel", output);
							MechMain.plugin.placed.saveAndReload();
							MechMain.plugin.economyManager.withdrawUserBalance(player, output);
							player.sendMessage(MechMain.plugin.texts.purchaseSuccess("coal", "" + output));
							player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.5f);
							
							Inventory updated = event.getInventory();
							ItemStack item = updated.getItem(1);
							ItemMeta meta = item.getItemMeta();
							meta.setDisplayName("Fuel: " + output);
							item.setItemMeta(meta);
							updated.setItem(1, item);
							player.openInventory(updated);
						}
					}
					break;
				case COAL:
					if (current.getItemMeta().getDisplayName().equalsIgnoreCase("Add Coal from Economy")) {
						double d = MechMain.plugin.generator.energyMultiplier;
						if (!(d < 0.0)) {
							if (MechMain.plugin.economyManager.getBalanceForUser(player) < d) {
								player.sendMessage(MechMain.plugin.texts.notEnoughEconomy);
								player.getLocation().getWorld().playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1.0f, 1.0f);
								return;
							}
							double output = MechMain.plugin.placed.getDouble(configKey + ".fuel") + d;
							MechMain.plugin.placed.set(configKey + ".fuel", output);
							MechMain.plugin.placed.saveAndReload();
							MechMain.plugin.economyManager.withdrawUserBalance(player, output);
							player.sendMessage(MechMain.plugin.texts.purchaseSuccess("lava", "" + output));
							player.getLocation().getWorld().playSound(player.getLocation(), Sound.BLOCK_NOTE_PLING, 1.0f, 1.5f);
							
							Inventory updated = event.getInventory();
							ItemStack item = updated.getItem(1);
							ItemMeta meta = item.getItemMeta();
							meta.setDisplayName("Fuel: " + output);
							item.setItemMeta(meta);
							updated.setItem(1, item);
							player.openInventory(updated);
						}
					}
					break;
				default:
					break;
				}
			} else event.setCancelled(true);
		}
	}
	
	private void inventoryClickEventNoMeta(InventoryClickEvent event) {
		ItemStack current = event.getCurrentItem();
		Player player = (Player) event.getWhoClicked();
		if (event.getInventory().getItem(0) == null) return;
		if (!event.getInventory().getItem(0).hasItemMeta()) return;
		if (!event.getInventory().getItem(0).getItemMeta().hasLore()) return;
		if (current.getType().equals(Material.COAL) || current.getType().equals(Material.LAVA_BUCKET)) {
			int configKey = StringUtils.getNumber(event.getInventory().getItem(0).getItemMeta().getLore().get(1));
			if (configKey == -1) {
				return;
			}
			ItemStack fuel = event.getInventory().getItem(4);
			if (fuel == null) return;
			double d, output;
			switch (fuel.getType()) {
			case COAL:
				 d = MechMain.plugin.generator.energyMultiplier * fuel.getAmount();
				 output = MechMain.plugin.placed.getDouble(configKey + ".fuel") + d;
				 MechMain.plugin.placed.set(configKey + ".fuel", output);
				 MechMain.plugin.placed.saveAndReload();
				 Inventory updated = event.getInventory();
				 ItemStack item = updated.getItem(1);
				 ItemMeta meta = item.getItemMeta();
				 meta.setDisplayName("Fuel: " + output);
				 item.setItemMeta(meta);
				 ItemStack itemb = updated.getItem(2);
				 ItemMeta metab = itemb.getItemMeta();
				 metab.setDisplayName("Power: " + MechMain.plugin.placed.getDouble(configKey + ".power"));
				 itemb.setItemMeta(metab);
				 updated.setItem(1, item);
				 updated.setItem(2, itemb);
				 updated.setItem(4, new ItemStack(Material.AIR));
				 player.openInventory(updated);
				 break;
			case LAVA_BUCKET:
				 d = MechMain.plugin.generator.energyMultiplier * 12.5 * fuel.getAmount();
				 output = MechMain.plugin.placed.getDouble(configKey + ".fuel") + d;
				 MechMain.plugin.placed.set(configKey + ".fuel", output);
				 MechMain.plugin.placed.saveAndReload();
				 Inventory updated2 = event.getInventory();
				 ItemStack item2 = updated2.getItem(1);
				 ItemMeta meta2 = item2.getItemMeta();
				 meta2.setDisplayName("Fuel: " + output);
				 item2.setItemMeta(meta2);
				 ItemStack itemb2 = updated2.getItem(2);
				 ItemMeta metab2 = itemb2.getItemMeta();
				 metab2.setDisplayName("Power: " + MechMain.plugin.placed.getDouble(configKey + ".power"));
				 itemb2.setItemMeta(metab2);
				 updated2.setItem(1, item2);
				 updated2.setItem(2, itemb2);
				 updated2.setItem(4, new ItemStack(Material.AIR));
				 player.openInventory(updated2);
				 break;
				 
			default:
				return;
			}
		} 
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void inventoryCloseEvent(InventoryCloseEvent event) {
		if (!(event.getPlayer() instanceof Player)) return;
		if (event.getInventory() == null) return;
		if (event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Inventory")) {
			Player player = (Player) event.getPlayer();
			InventoryHandler.saveInventoryForPlayer(player, event.getInventory());
		} else if (event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Dyes")) {
			Player player = (Player) event.getPlayer();
			ItemStack i = event.getInventory().getItem(4);
			if (i == null) return;
			if (i.getType().equals(Material.INK_SACK)) InventoryHandler.setDyeForPlayer(player, i);
		} else if (StringUtils.countOccurances(event.getInventory().getName(), " ") == 2 && event.getInventory().getName().endsWith("Inventory")) {
			String split = event.getInventory().getName().split(" ")[1];
			Player player = Bukkit.getPlayer(split);
			if (player != null) InventoryHandler.saveInventoryForPlayer(player, event.getInventory());
			else event.getPlayer().sendMessage(ChatColor.AQUA + "You may have lost some items because the player data for the player with this name can no longer be found!");
		} else if (event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Generator")) {
			int configKey = StringUtils.getNumber(event.getInventory().getItem(0).getItemMeta().getLore().get(1));
			if (configKey == -1) {
				return;
			}
			ItemStack fuel = event.getInventory().getItem(4);
			if (fuel == null) return;
			double d, output;
			switch (fuel.getType()) {
			case COAL:
				 d = MechMain.plugin.generator.energyMultiplier * fuel.getAmount();
				 output = MechMain.plugin.placed.getDouble(configKey + ".fuel") + d;
				 MechMain.plugin.placed.set(configKey + ".fuel", output);
				 MechMain.plugin.placed.saveAndReload();
				 break;
			case LAVA_BUCKET:
				 d = MechMain.plugin.generator.energyMultiplier * 12.5 * fuel.getAmount();
				 output = MechMain.plugin.placed.getDouble(configKey + ".fuel") + d;
				 MechMain.plugin.placed.set(configKey + ".fuel", output);
				 MechMain.plugin.placed.saveAndReload();
				 break;
				 
			default:
				return;
			}
		}
	}
}
