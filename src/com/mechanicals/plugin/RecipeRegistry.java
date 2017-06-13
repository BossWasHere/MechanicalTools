package com.mechanicals.plugin;

import java.util.Iterator;
import java.util.Map;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import com.mechanicals.plugin.item.ItemConfigurationEncoder;
import com.mechanicals.plugin.server.ServerVersion;

public class RecipeRegistry {
	
	public static void registerRecipes(MechMain plugin) {
		
		//Blocks
		if (plugin.blockData.getBoolean("block.blockBreaker.crafting.enabled") && plugin.blockBreakerEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.blockBreaker.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.blockBreaker.getBlockItem(), plugin.blockBreaker.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.blockBreaker.crafting.a"), plugin.blockData.getString("block.blockBreaker.crafting.b"), plugin.blockData.getString("block.blockBreaker.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block BlockBreaker failed to load. (Check the item shape has declared all chars)");
			}
			
		}
		if (plugin.blockData.getBoolean("block.blockPlacer.crafting.enabled") && plugin.blockPlacerEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.blockPlacer.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.blockPlacer.getBlockItem(), plugin.blockPlacer.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.blockPlacer.crafting.a"), plugin.blockData.getString("block.blockPlacer.crafting.b"), plugin.blockData.getString("block.blockPlacer.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block BlockPlacer failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.treeCutter.crafting.enabled") && plugin.treeCutterEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.treeCutter.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.treeCutter.getBlockItem(), plugin.treeCutter.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.treeCutter.crafting.a"), plugin.blockData.getString("block.treeCutter.crafting.b"), plugin.blockData.getString("block.treeCutter.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block TreeCutter failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.entityTeleporter.crafting.enabled") && plugin.entityTeleporterEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.entityTeleporter.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.entityTeleporter.getBlockItem(), plugin.entityTeleporter.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.entityTeleporter.crafting.a"), plugin.blockData.getString("block.entityTeleporter.crafting.b"), plugin.blockData.getString("block.entityTeleporter.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block EntityTeleporter failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.itemTeleporter.crafting.enabled") && plugin.itemTeleporterEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.itemTeleporter.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.itemTeleporter.getBlockItem(), plugin.itemTeleporter.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.itemTeleporter.crafting.a"), plugin.blockData.getString("block.itemTeleporter.crafting.b"), plugin.blockData.getString("block.itemTeleporter.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block ItemTeleporter failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.grinder.crafting.enabled") && plugin.grinderEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.grinder.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.grinder.getBlockItem(), plugin.grinder.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.grinder.crafting.a"), plugin.blockData.getString("block.grinder.crafting.b"), plugin.blockData.getString("block.grinder.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block Grinder failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.largeTeleporter.crafting.enabled") && plugin.largeTeleporterEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.largeTeleporter.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.largeTeleporter.getBlockItem(), plugin.largeTeleporter.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.largeTeleporter.crafting.a"), plugin.blockData.getString("block.largeTeleporter.crafting.b"), plugin.blockData.getString("block.largeTeleporter.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block Large Teleporter failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.chunkLoader.crafting.enabled") && plugin.chunkLoaderEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.chunkLoader.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.chunkLoader.getBlockItem(), plugin.chunkLoader.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.chunkLoader.crafting.a"), plugin.blockData.getString("block.chunkLoader.crafting.b"), plugin.blockData.getString("block.chunkLoader.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block Chunk Loader failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.elevator.crafting.enabled") && plugin.elevatorEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.elevator.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.elevator.getBlockItem(), plugin.elevator.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.elevator.crafting.a"), plugin.blockData.getString("block.elevator.crafting.b"), plugin.blockData.getString("block.elevator.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block Elevator failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.animalGrowth.crafting.enabled") && plugin.animalGrowthEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.animalGrowth.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.animalGrowth.getBlockItem(), plugin.animalGrowth.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.animalGrowth.crafting.a"), plugin.blockData.getString("block.animalGrowth.crafting.b"), plugin.blockData.getString("block.animalGrowth.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block Animal Growth failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.blockData.getBoolean("block.plantFarmer.crafting.enabled") && plugin.plantFarmerEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.blockData.getString("block.plantFarmer.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.plantFarmer.getBlockItem(), plugin.plantFarmer.getMechBlock().getId());
			recipe.shape(plugin.blockData.getString("block.plantFarmer.crafting.a"), plugin.blockData.getString("block.plantFarmer.crafting.b"), plugin.blockData.getString("block.plantFarmer.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the block Plant Farmer failed to load. (Check the item shape has declared all chars)");
			}
		}
		
		//Items
		if (plugin.itemData.getBoolean("item.spawnPointTeleporter.crafting.enabled") && plugin.spawnPointTeleporterEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.itemData.getString("item.spawnPointTeleporter.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.bedTeleporter.getItem(), plugin.bedTeleporter.getMechItem().getId());
			recipe.shape(plugin.itemData.getString("item.spawnPointTeleporter.crafting.a"), plugin.itemData.getString("item.spawnPointTeleporter.crafting.b"), plugin.itemData.getString("item.spawnPointTeleporter.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the item Spawn Point Teleporter failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.itemData.getBoolean("item.radio.crafting.enabled") && plugin.radioEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.itemData.getString("item.radio.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.radio.getItem(), plugin.radio.getMechItem().getId());
			recipe.shape(plugin.itemData.getString("item.radio.crafting.a"), plugin.itemData.getString("item.radio.crafting.b"), plugin.itemData.getString("item.radio.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the item Radio failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.itemData.getBoolean("item.iTool.crafting.enabled") && plugin.iToolEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.itemData.getString("item.iTool.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.iTool.getItem(), plugin.iTool.getMechItem().getId());
			recipe.shape(plugin.itemData.getString("item.iTool.crafting.a"), plugin.itemData.getString("item.iTool.crafting.b"), plugin.itemData.getString("item.iTool.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the item ITool failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.itemData.getBoolean("item.dyeWand.crafting.enabled") && plugin.dyeWandEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.itemData.getString("item.dyeWand.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.dyeWand.getItem(), plugin.dyeWand.getMechItem().getId());
			recipe.shape(plugin.itemData.getString("item.dyeWand.crafting.a"), plugin.itemData.getString("item.dyeWand.crafting.b"), plugin.itemData.getString("item.dyeWand.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the item Dye Wand failed to load. (Check the item shape has declared all chars)");
			}
		}
		if (plugin.itemData.getBoolean("item.flamethrower.crafting.enabled") && plugin.flamethrowerEnabled) {
			Map<Character, ItemStack> items = ItemConfigurationEncoder.decodeStringItemParts(plugin.itemData.getString("item.flamethrower.crafting.items"));
			ShapedRecipe recipe = getRecipeHolder(plugin, plugin.flamethrower.getItem(), plugin.flamethrower.getMechItem().getId());
			recipe.shape(plugin.itemData.getString("item.flamethrower.crafting.a"), plugin.itemData.getString("item.flamethrower.crafting.b"), plugin.itemData.getString("item.flamethrower.crafting.c"));
			
			if (!register(plugin, recipe, items)) {
				plugin.logger.warning("The recipe for the item Flamethrower failed to load. (Check the item shape has declared all chars)");
			}
		}
	}

	@SuppressWarnings("deprecation")
	private static ShapedRecipe getRecipeHolder(MechMain plugin, ItemStack item, String namespace) {
		if (ServerVersion.isAtOrHigherThan("1.12")) {
			return new ShapedRecipe(new org.bukkit.NamespacedKey(plugin, namespace), item);
		} else {
			return new ShapedRecipe(item);
		}
	}
	
	private static boolean register(MechMain plugin, ShapedRecipe recipe, Map<Character, ItemStack> items) {
		Iterator<Character> charIt = items.keySet().iterator();
		Iterator<ItemStack> stackIt = items.values().iterator();
		while (charIt.hasNext()) {
			recipe.setIngredient(charIt.next(), stackIt.next().getType());
		}
		return plugin.getServer().addRecipe(recipe);
	}
}
