package com.mechanicals.plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHandler {
	
	public static void saveInventoryForPlayer(Player player, Inventory inventory) {
		ItemStack[] contents = inventory.getContents();
		if (contents == null) return;
		String UUID = player.getUniqueId().toString();
		if (MechMain.plugin.remoteStorage.isConfigurationSection(UUID)) {
			MechMain.plugin.remoteStorage.set(UUID, null);
			MechMain.plugin.remoteStorage.saveAndReload();
		}
		for (int i = 0; i < contents.length; i++) {
			if (contents[i] == null) continue;
			MechMain.plugin.remoteStorage.set(UUID + "." + i, contents[i]);
		}
		MechMain.plugin.remoteStorage.saveAndReload();
	}
	
	public static Inventory loadInventoryForPlayer(Player player) {
		if (!player.hasPermission(MechMain.plugin.iTool.useInventoryPerm())) return null;
		int sizePerm = 9;
		if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".6")) {
			sizePerm = 54;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".5")) {
			sizePerm = 45;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".4")) {
			sizePerm = 36;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".3")) {
			sizePerm = 27;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".2")) {
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
	
	public static Inventory loadRemoteInventoryForPlayer(Player player) {
		int sizePerm = 9;
		if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".6")) {
			sizePerm = 54;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".5")) {
			sizePerm = 45;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".4")) {
			sizePerm = 36;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".3")) {
			sizePerm = 27;
		} else if (player.hasPermission(MechMain.plugin.config.getString("remoteStoragePermission", "mechanicals") + ".2")) {
			sizePerm = 18;
		}
		ItemStack[] items = getInventoryContentsForPlayer(player);
		Inventory i = Bukkit.createInventory(null, sizePerm, ChatColor.BLUE + "[Mechanical] " + player.getName() + (player.getName().endsWith("s") ? "' Inventory" : "'s Inventory"));
		int j = 0;
		for (ItemStack it : items) {
			if (j < sizePerm) i.addItem(it);
			j++;
		}
		return i;
	}
	
	public static Inventory loadDyeInventoryForPlayer(Player player) {
		Inventory i = Bukkit.createInventory(null, 9, ChatColor.BLUE + "[Mechanical] Dyes");
		i.setItem(4, getDyeForPlayer(player));
		ItemStack filter = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)8);
		ItemMeta meta = filter.getItemMeta();
		meta.setDisplayName(MechMain.plugin.texts.placeDyes);
		filter.setItemMeta(meta);
		for (int j = 0; j < 8; j++) {
			if (j == 4) continue;
			i.setItem(j, filter);
		}
		return i;
	}
	
	public static Inventory loadGeneratorInventory(String configKey) {
		Inventory i = Bukkit.createInventory(null, 9, ChatColor.BLUE + "[Mechanical] Generator");
		ItemStack filter = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)7);
		ItemMeta meta = filter.getItemMeta();
		meta.setDisplayName(MechMain.plugin.texts.placeFuel);
		filter.setItemMeta(meta);
		ItemStack locData = new ItemStack(Material.PAPER);
		ItemMeta locMeta = locData.getItemMeta();
		locMeta.setDisplayName("BlockPos");
		locMeta.setLore(Arrays.asList(new String[] {"ConfigKey", configKey}));
		locData.setItemMeta(locMeta);
		ItemStack fuelData = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta fuelMeta = fuelData.getItemMeta();
		fuelMeta.setDisplayName("Fuel: " + MechMain.plugin.placed.getDouble(configKey + ".fuel"));
		fuelMeta.setLore(Arrays.asList(new String[] {"How much fuel this generator has"}));
		fuelData.setItemMeta(fuelMeta);
		ItemStack powerData = new ItemStack(Material.DAYLIGHT_DETECTOR);
		ItemMeta powerMeta = powerData.getItemMeta();
		powerMeta.setDisplayName("Power: " + MechMain.plugin.placed.getDouble(configKey + ".power"));
		powerMeta.setLore(Arrays.asList(new String[] {"How much energy this generator has"}));
		powerData.setItemMeta(powerMeta);
		i.setItem(0, locData);
		i.setItem(1, fuelData);
		i.setItem(2, powerData);
		for (int j = 3; j < 9; j++) {
			if (j == 4) continue;
			i.setItem(j, filter);
		}
		if (MechMain.plugin.vault) {
			ItemStack coalEconomy = new ItemStack(Material.COAL);
			ItemStack lavaEconomy = new ItemStack(Material.LAVA_BUCKET);
			ItemMeta coalEconomyMeta = coalEconomy.getItemMeta();
			coalEconomyMeta.setDisplayName("Add Coal from Economy");
			coalEconomyMeta.setLore(Arrays.asList(new String[] {"\"Buy\" a piece of coal", "" + MechMain.plugin.generator.energyMultiplier}));
			ItemMeta lavaEconomyMeta = lavaEconomy.getItemMeta();
			lavaEconomyMeta.setDisplayName("Add Lava from Economy");
			lavaEconomyMeta.setLore(Arrays.asList(new String[] {"\"Buy\" a bucket of lava", "" + (double) Math.round(MechMain.plugin.generator.energyMultiplier * 12.5 * 100.0) / 100.0}));
			coalEconomy.setItemMeta(coalEconomyMeta);
			lavaEconomy.setItemMeta(lavaEconomyMeta);
			i.setItem(7, coalEconomy);
			i.setItem(8, lavaEconomy);
		}
		
		return i;
	}
	
	public static ItemStack getDyeForPlayer(Player player) {
		ItemStack item = new ItemStack(Material.AIR);
		String UUID = player.getUniqueId().toString();
		if (MechMain.plugin.remoteStorage.isConfigurationSection(UUID)) {
			if (MechMain.plugin.remoteStorage.isItemStack(UUID + ".dye")) {
				item = MechMain.plugin.remoteStorage.getItemStack(UUID + ".dye");
			}
		}
		return item;
	}
	
	public static void setDyeForPlayer(Player player, ItemStack dye) {
		if (dye == null) return;
		MechMain.plugin.remoteStorage.set(player.getUniqueId().toString() + ".dye", dye);
		MechMain.plugin.remoteStorage.saveAndReload();
	}
	
	public static ItemStack[] getInventoryContentsForPlayer(Player player) {
		String UUID = player.getUniqueId().toString();
		List<ItemStack> items = new ArrayList<>();
		if (!MechMain.plugin.remoteStorage.isConfigurationSection(UUID)) return new ItemStack[] {};
		for (String key : MechMain.plugin.remoteStorage.getConfigurationSection(UUID).getKeys(false)) {
			if (key.equalsIgnoreCase("dye")) continue;
			if (MechMain.plugin.remoteStorage.contains(UUID + "." + key)) {
				if (MechMain.plugin.remoteStorage.isItemStack(UUID + "." + key)) {
					items.add(MechMain.plugin.remoteStorage.getItemStack(UUID + "." + key));
				}
			}
		}
		return items.toArray(new ItemStack[items.size()]);
	}
}
