package com.mechanicals.plugin.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.mechanicals.plugin.RegisteredCommand;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.items.MechanicalItems;

public class MechanicalCommand extends RegisteredCommand {

	public MechanicalCommand() {
		super();
	}
	
	public void run(CommandSender sender, String[] args) {
		if (args.length > 0 && sender instanceof Player) {
			if (args[0].equalsIgnoreCase("blocks")) {
				Inventory i = Bukkit.createInventory(null, 18, ChatColor.BLUE + "[Mechanical Blocks]");
				if (plugin.blockBreakerEnabled && sender.hasPermission(MechanicalBlocks.BLOCK_BREAKER.getCommandPermission())) i.addItem(plugin.blockBreaker.getBlockItem());
				if (plugin.blockPlacerEnabled && sender.hasPermission(MechanicalBlocks.BLOCK_PLACER.getCommandPermission())) i.addItem(plugin.blockPlacer.getBlockItem());
				if (plugin.treeCutterEnabled && sender.hasPermission(MechanicalBlocks.TREE_CUTTER.getCommandPermission())) i.addItem(plugin.treeCutter.getBlockItem());
				if (plugin.entityTeleporterEnabled && sender.hasPermission(MechanicalBlocks.ENTITY_TELEPORTER.getCommandPermission())) i.addItem(plugin.entityTeleporter.getBlockItem());
				if (plugin.itemTeleporterEnabled && sender.hasPermission(MechanicalBlocks.ITEM_TELEPORTER.getCommandPermission())) i.addItem(plugin.itemTeleporter.getBlockItem());
				if (plugin.grinderEnabled && sender.hasPermission(MechanicalBlocks.GRINDER.getCommandPermission())) i.addItem(plugin.grinder.getBlockItem());
				if (plugin.largeTeleporterEnabled && sender.hasPermission(MechanicalBlocks.LARGE_TELEPORTER.getCommandPermission())) i.addItem(plugin.largeTeleporter.getBlockItem());
				if (plugin.chunkLoaderEnabled && sender.hasPermission(MechanicalBlocks.CHUNK_LOADER.getCommandPermission())) i.addItem(plugin.chunkLoader.getBlockItem());
				if (plugin.elevatorEnabled && sender.hasPermission(MechanicalBlocks.ELEVATOR.getCommandPermission())) i.addItem(plugin.elevator.getBlockItem());
				if (plugin.animalGrowthEnabled && sender.hasPermission(MechanicalBlocks.ANIMAL_GROWTH.getCommandPermission())) i.addItem(plugin.animalGrowth.getBlockItem());
				if (plugin.plantFarmerEnabled && sender.hasPermission(MechanicalBlocks.PLANT_FARMER.getCommandPermission())) i.addItem(plugin.plantFarmer.getBlockItem());
				if (plugin.generatorEnabled && sender.hasPermission(MechanicalBlocks.GENERATOR.getCommandPermission())) i.addItem(plugin.generator.getBlockItem());
				
				if (i.getContents().length < 1) {
					sender.sendMessage(ChatColor.RED + "There are no Mechanical Blocks enabled, or you don't have any permissions!");
					return;
				}
				((Player)sender).openInventory(i);
				return;
			}
			if (args[0].equalsIgnoreCase("items")) {
				Inventory i = Bukkit.createInventory(null, 9, ChatColor.BLUE + "[Mechanical Items]");
				if (plugin.spawnPointTeleporterEnabled && sender.hasPermission(MechanicalItems.BED_TELEPORT.getCommandPermission())) i.addItem(plugin.bedTeleporter.getItem());
				if (plugin.radioEnabled && sender.hasPermission(MechanicalItems.RADIO.getCommandPermission())) i.addItem(plugin.radio.getItem());
				if (plugin.iToolEnabled && sender.hasPermission(MechanicalItems.ITOOL.getCommandPermission())) i.addItem(plugin.iTool.getItem());
				if (plugin.dyeWandEnabled && sender.hasPermission(MechanicalItems.DYE_WAND.getCommandPermission())) i.addItem(plugin.dyeWand.getItem());
				if (plugin.flamethrowerEnabled && sender.hasPermission(MechanicalItems.FLAMETHROWER.getCommandPermission())) i.addItem(plugin.flamethrower.getItem());
				
				if (i.getContents().length < 1) {
					sender.sendMessage(ChatColor.RED + "There are no Mechanical Items enabled, or you don't have any permissions!");
					return;
				}
				((Player)sender).openInventory(i);
				return;
			}
		}
		sender.sendMessage(ChatColor.GOLD + "Mechanical Blocks and Tools Plugin" + ChatColor.AQUA + " By IballisticBoss " + ChatColor.GOLD + "Version: " + plugin.getDescription().getVersion());
		sender.sendMessage(ChatColor.BLUE + "/mechanical [blocks/items] - " + ChatColor.AQUA + "Shows this help screen or shows all blocks or shows all items");
		sender.sendMessage(ChatColor.BLUE + "/mechanicalconfig <view/set/confirm/reset> [...] - " + ChatColor.AQUA + " Allows you to adjust the configuration for this plugin");
		sender.sendMessage(ChatColor.BLUE + "/mechanicalreload - " + ChatColor.AQUA + " Reloads the configuration for the plugin");
		sender.sendMessage(ChatColor.BLUE + "/blockbreaker (player) - " + ChatColor.AQUA + " Give yourself (or another player) a block breaker item");			
		sender.sendMessage(ChatColor.BLUE + "/blockplacer (player) - " + ChatColor.AQUA + " Give yourself (or another player) a block placer item");
		sender.sendMessage(ChatColor.BLUE + "/treecutter (player) - " + ChatColor.AQUA + " Give yourself (or another player) a tree cutter item");
		sender.sendMessage(ChatColor.BLUE + "/entityteleporter (player) - " + ChatColor.AQUA + " Give yourself (or another player) an entity teleporter item");
		sender.sendMessage(ChatColor.BLUE + "/itemteleporter (player) - " + ChatColor.AQUA + " Give yourself (or another player) an item teleporter item");
		sender.sendMessage(ChatColor.BLUE + "/grinder (player) - " + ChatColor.AQUA + " Give yourself (or another player) a grinder item");
		sender.sendMessage(ChatColor.BLUE + "/largeteleporter (player) - " + ChatColor.AQUA + " Give yourself (or another player) a large teleporter item");
		sender.sendMessage(ChatColor.BLUE + "/chunkloader (player) - " + ChatColor.AQUA + " Give yourself (or another player) a chunk loader item");
		sender.sendMessage(ChatColor.BLUE + "/elevator (player) - " + ChatColor.AQUA + " Give yourself (or another player) an elevator item");
		sender.sendMessage(ChatColor.BLUE + "/animalgrowth (player) - " + ChatColor.AQUA + " Give yourself (or another player) an animal growth item");
		sender.sendMessage(ChatColor.BLUE + "/plantfarmer (player) - " + ChatColor.AQUA + " Give yourself (or another player) a plant farmer item");
		sender.sendMessage(ChatColor.BLUE + "/generator (player) - " + ChatColor.AQUA + " Give yourself (or another player) a generator item");
		sender.sendMessage(ChatColor.BLUE + "/spawnpointteleporter (player) - " + ChatColor.AQUA + " Give yourself (or another player) an spawn point teleporter item");
		sender.sendMessage(ChatColor.BLUE + "/mechradio (player) - " + ChatColor.AQUA + " Give yourself (or another player) an radio item");
		sender.sendMessage(ChatColor.BLUE + "/itool (player) - " + ChatColor.AQUA + " Give yourself (or another player) an ITool item");
		sender.sendMessage(ChatColor.BLUE + "/dyewand (player) - " + ChatColor.AQUA + " Give yourself (or another player) a dye wand item");
		sender.sendMessage(ChatColor.BLUE + "/flamethrower (player) - " + ChatColor.AQUA + " Give yourself (or another player) a flamethrower item");
	}
}
