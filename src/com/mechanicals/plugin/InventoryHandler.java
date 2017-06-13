package com.mechanicals.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHandler {

	private final MechMain plugin;
	
	public InventoryHandler(MechMain plugin) {
		this.plugin = plugin;
	}
	
	public void saveInventoryForPlayer(Player player, Inventory inventory) {
		ItemStack[] contents = inventory.getContents();
		if (contents == null) return;
		String UUID = player.getUniqueId().toString();
		if (plugin.remoteStorage.isConfigurationSection(UUID)) {
			plugin.remoteStorage.set(UUID, null);
			plugin.remoteStorage.saveAndReload();
		}
		for (int i = 0; i < contents.length; i++) {
			if (contents[i] == null) continue;
			plugin.remoteStorage.set(UUID + "." + i, contents[i]);
		}
		plugin.remoteStorage.saveAndReload();
	}
	
	public Inventory loadInventoryForPlayer(Player player) {
		if (!player.hasPermission(plugin.permissions.iTool_useInventory)) return null;
		int sizePerm = 9;
		if (player.hasPermission(plugin.config.getString("remoteStoragePermission", "mechanicals") + ".6")) {
			sizePerm = 54;
		} else if (player.hasPermission(plugin.config.getString("remoteStoragePermission", "mechanicals") + ".5")) {
			sizePerm = 45;
		} else if (player.hasPermission(plugin.config.getString("remoteStoragePermission", "mechanicals") + ".4")) {
			sizePerm = 36;
		} else if (player.hasPermission(plugin.config.getString("remoteStoragePermission", "mechanicals") + ".3")) {
			sizePerm = 27;
		} else if (player.hasPermission(plugin.config.getString("remoteStoragePermission", "mechanicals") + ".2")) {
			sizePerm = 18;
		}
		ItemStack[] items = getInventoryContentsForPlayer(player);
		Inventory i = Bukkit.createInventory(null, sizePerm, ChatColor.BLUE + "[Mechanical] Inventory");
		int j = 0;
		for (ItemStack it : items) {
			if (j < sizePerm) i.addItem(it);
			j++;
		}
		return i;
	}
	
	public Inventory loadDyeInventoryForPlayer(Player player) {
		Inventory i = Bukkit.createInventory(null, 9, ChatColor.BLUE + "[Mechanical] Dyes");
		i.setItem(4, getDyeForPlayer(player));
		ItemStack filter = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
		ItemMeta meta = filter.getItemMeta();
		meta.setDisplayName(plugin.texts.placeDyes);
		filter.setItemMeta(meta);
		for (int j = 0; j < 8; j++) {
			if (j == 4) continue;
			i.setItem(j, filter);
		}
		return i;
	}
	
	public ItemStack getDyeForPlayer(Player player) {
		ItemStack item = new ItemStack(Material.AIR);
		String UUID = player.getUniqueId().toString();
		if (plugin.remoteStorage.isConfigurationSection(UUID)) {
			if (plugin.remoteStorage.isItemStack(UUID + ".dye")) {
				item = plugin.remoteStorage.getItemStack(UUID + ".dye");
			}
		}
		return item;
	}
	
	public void setDyeForPlayer(Player player, ItemStack dye) {
		if (dye == null) return;
		plugin.remoteStorage.set(player.getUniqueId().toString() + ".dye", dye);
		plugin.remoteStorage.saveAndReload();
	}
	
	public ItemStack[] getInventoryContentsForPlayer(Player player) {
		String UUID = player.getUniqueId().toString();
		List<ItemStack> items = new ArrayList<>();
		if (!plugin.remoteStorage.isConfigurationSection(UUID)) return new ItemStack[] {};
		for (String key : plugin.remoteStorage.getConfigurationSection(UUID).getKeys(false)) {
			if (key.equalsIgnoreCase("dye")) continue;
			if (plugin.remoteStorage.contains(UUID + "." + key)) {
				if (plugin.remoteStorage.isItemStack(UUID + "." + key)) {
					items.add(plugin.remoteStorage.getItemStack(UUID + "." + key));
				}
			}
		}
		return items.toArray(new ItemStack[items.size()]);
	}
}