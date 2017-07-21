package com.mechanicals.plugin.items;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mechanicals.plugin.InventoryHandler;

public class ITool extends BaseMechanicalItem {

	public ITool() {
		super();
	}

	@Override
	public MechanicalItems getMechItem() {
		return MechanicalItems.ITOOL;
	}

	@Override
	public void itemUseEvent(PlayerInteractEvent event) {
		if (!plugin.iToolEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (event.getPlayer().hasPermission(usePerm())) {
			openMainPage(event.getPlayer());
		} else {
			event.getPlayer().sendMessage(plugin.texts.noPermissionUse);
		}
	}
	
	public static void openMainPage(Player player) {
		Inventory i = Bukkit.createInventory(null, 9, ChatColor.BLUE + "[Mechanical] ITool");
		ItemStack exit = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta exitMeta = exit.getItemMeta();
		exitMeta.setDisplayName("Exit");
		exitMeta.setLore(Arrays.asList("Click to leave"));
		exit.setItemMeta(exitMeta);
		
		ItemStack craft = new ItemStack(Material.WORKBENCH);
		ItemMeta craftMeta = craft.getItemMeta();
		craftMeta.setDisplayName("Crafting");
		craftMeta.setLore(Arrays.asList("Click to enter crafting"));
		craft.setItemMeta(craftMeta);
		
		ItemStack enchant = new ItemStack(Material.ENCHANTMENT_TABLE);
		ItemMeta enchantMeta = enchant.getItemMeta();
		enchantMeta.setDisplayName("Enchanting");
		enchantMeta.setLore(Arrays.asList("Click to enter enchanting", ChatColor.RED + "Currently disabled due to errors"));
		enchant.setItemMeta(enchantMeta);
		
		ItemStack anvil = new ItemStack(Material.ANVIL);
		ItemMeta anvilMeta = anvil.getItemMeta();
		anvilMeta.setDisplayName("Anvil");
		anvilMeta.setLore(Arrays.asList("Click to use an anvil", ChatColor.RED + "Currently disabled due to errors"));
		anvil.setItemMeta(anvilMeta);
		
		ItemStack inv = new ItemStack(Material.CHEST);
		ItemMeta invMeta = inv.getItemMeta();
		invMeta.setDisplayName("Backpack");
		invMeta.setLore(Arrays.asList("Click to enter inventory"));
		inv.setItemMeta(invMeta);
		
		i.addItem(exit);
		i.addItem(craft);
		i.addItem(enchant);
		i.addItem(anvil);
		i.addItem(inv);
		player.openInventory(i);
	}
	
	public static void openCraftingPage(Player player) {
		player.openWorkbench(null, true);
	}
	
	//Disabled due to errors
	
	public static void openEnchantmentPage(Player player) {
		//player.openEnchanting(null, true);
	}
	
	public static void openAnvilPage(Player player) {
		//Inventory i = Bukkit.createInventory(player, InventoryType.ANVIL);
		//player.openInventory(i);
	}

	public void openPlayerInventory(Player player) {
		Inventory i = InventoryHandler.loadInventoryForPlayer(player);
		if (i == null) {
			player.sendMessage(ChatColor.RED + "You cannot use this!");
			return;
		}
		player.openInventory(i);
	}
	
	public String useCraftPerm() { return plugin.permissions.get(getMechItem().getId() + "_useCraft"); }
	public String useAnvilPerm() { return plugin.permissions.get(getMechItem().getId() + "_useAnvil"); }
	public String useEnchantPerm() { return plugin.permissions.get(getMechItem().getId() + "_useEnchant"); }
	public String useInventoryPerm() { return plugin.permissions.get(getMechItem().getId() + "_useInventory"); }

}
