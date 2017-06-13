package com.mechanicals.plugin.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.items.ITool;
import com.xxmicloxx.NoteBlockAPI.NoteBlockPlayerMain;

public class InventoryEvent implements Listener {

	public final MechMain plugin;
	
	public InventoryEvent(MechMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void inventoryClickEvent(InventoryClickEvent event) {
		ItemStack current = event.getCurrentItem();
		if (!(event.getWhoClicked() instanceof Player)) return;
		if (current == null) return;
		if (!current.hasItemMeta()) return;
		Player player = (Player) event.getWhoClicked();
		String inventoryName = event.getInventory().getName();
		if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical Blocks]") || inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical Items]")) {
			player.getInventory().addItem(current);
			event.setCancelled(true);
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Radio") && plugin.nbapi) {
			if (NoteBlockPlayerMain.isReceivingSong(player)) NoteBlockPlayerMain.stopPlaying(player);
			if (current.getItemMeta().getDisplayName().equalsIgnoreCase("Stop Song") && current.getType().equals(Material.REDSTONE_BLOCK)) {
				event.setCancelled(true);
				return;
			}
			if (!plugin.noteBlockHandler.playNBS(player, current.getItemMeta().getDisplayName())) {
				player.sendMessage(plugin.texts.songIssue);
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
				if (player.hasPermission(plugin.permissions.iTool_useCraft)) {
					ITool.openCraftingPage(player);
				} else {
					player.sendMessage(plugin.texts.noPermissionUse);
				}
				break;
			case ENCHANTMENT_TABLE:
				player.closeInventory();
				if (player.hasPermission(plugin.permissions.iTool_useEnchant)) {
					ITool.openEnchantmentPage(player);
				} else {
					player.sendMessage(plugin.texts.noPermissionUse);
				}
				break;
			case ANVIL:
				player.closeInventory();
				if (player.hasPermission(plugin.permissions.iTool_useAnvil)) {
					ITool.openAnvilPage(player);
				} else {
					player.sendMessage(plugin.texts.noPermissionUse);
				}
				break;
			case CHEST:
				player.closeInventory();
				if (player.hasPermission(plugin.permissions.iTool_useInventory)) {
					plugin.iTool.openPlayerInventory(player);
				} else {
					player.sendMessage(plugin.texts.noPermissionUse);
				}
				break;
			default:
				break;
			}
		} else if (inventoryName.equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Dyes")) {
			if (!current.getType().equals(Material.INK_SACK)) event.setCancelled(true);
		} else if (event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Inventory")) {
			if (plugin.iTool.matchesMeta(current)) event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void inventoryCloseEvent(InventoryCloseEvent event) {
		if (!(event.getPlayer() instanceof Player)) return;
		if (event.getInventory() == null) return;
		if (event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Inventory")) {
			Player player = (Player) event.getPlayer();
			plugin.inventoryHandler.saveInventoryForPlayer(player, event.getInventory());
		} else if (event.getInventory().getName().equalsIgnoreCase(ChatColor.BLUE + "[Mechanical] Dyes")) {
			Player player = (Player) event.getPlayer();
			ItemStack i = event.getInventory().getItem(4);
			if (i == null) return;
			if (i.getType().equals(Material.INK_SACK)) plugin.inventoryHandler.setDyeForPlayer(player, i);
		}
	}
}