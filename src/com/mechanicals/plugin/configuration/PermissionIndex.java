package com.mechanicals.plugin.configuration;

import java.util.HashMap;
import java.util.Map;

public class PermissionIndex {

	public final Map<String, String> loaded = new HashMap<>();
	
	private final String NOT_FOUND = "mechanical.omni";
	
	public PermissionIndex(ConfigurationUnit mainConfig, ConfigurationUnit blockConfig, ConfigurationUnit itemConfig) {
		loaded.put("blockBreaker_place", blockConfig.getString("block.blockBreaker.permissions.place", NOT_FOUND));
		loaded.put("blockBreaker_break", blockConfig.getString("block.blockBreaker.permissions.break", NOT_FOUND));
		loaded.put("blockBreaker_breakOther", blockConfig.getString("block.blockBreaker.permissions.breakOther", NOT_FOUND));
		loaded.put("blockBreaker_command", blockConfig.getString("block.blockBreaker.permissions.command", NOT_FOUND));
		loaded.put("blockBreaker_commandOther", blockConfig.getString("block.blockBreaker.permissions.commandOther", NOT_FOUND));
		
		loaded.put("blockPlacer_place", blockConfig.getString("block.blockPlacer.permissions.place", NOT_FOUND));
		loaded.put("blockPlacer_break", blockConfig.getString("block.blockPlacer.permissions.break", NOT_FOUND));
		loaded.put("blockPlacer_breakOther", blockConfig.getString("block.blockPlacer.permissions.breakOther", NOT_FOUND));
		loaded.put("blockPlacer_command", blockConfig.getString("block.blockPlacer.permissions.command", NOT_FOUND));
		loaded.put("blockPlacer_commandOther", blockConfig.getString("block.blockPlacer.permissions.commandOther", NOT_FOUND));
		
		loaded.put("treeCutter_place", blockConfig.getString("block.treeCutter.permissions.place", NOT_FOUND));
		loaded.put("treeCutter_break", blockConfig.getString("block.treeCutter.permissions.break", NOT_FOUND));
		loaded.put("treeCutter_breakOther", blockConfig.getString("block.treeCutter.permissions.breakOther", NOT_FOUND));
		loaded.put("treeCutter_command", blockConfig.getString("block.treeCutter.permissions.command", NOT_FOUND));
		loaded.put("treeCutter_commandOther", blockConfig.getString("block.treeCutter.permissions.commandOther", NOT_FOUND));
		
		loaded.put("entityTeleporter_place", blockConfig.getString("block.entityTeleporter.permissions.place", NOT_FOUND));
		loaded.put("entityTeleporter_break", blockConfig.getString("block.entityTeleporter.permissions.break", NOT_FOUND));
		loaded.put("entityTeleporter_breakOther", blockConfig.getString("block.entityTeleporter.permissions.breakOther", NOT_FOUND));
		loaded.put("entityTeleporter_command", blockConfig.getString("block.entityTeleporter.permissions.command", NOT_FOUND));
		loaded.put("entityTeleporter_commandOther", blockConfig.getString("block.entityTeleporter.permissions.commandOther", NOT_FOUND));
		
		loaded.put("itemTeleporter_place", blockConfig.getString("block.itemTeleporter.permissions.place", NOT_FOUND));
		loaded.put("itemTeleporter_break", blockConfig.getString("block.itemTeleporter.permissions.break", NOT_FOUND));
		loaded.put("itemTeleporter_breakOther", blockConfig.getString("block.itemTeleporter.permissions.breakOther", NOT_FOUND));
		loaded.put("itemTeleporter_command", blockConfig.getString("block.itemTeleporter.permissions.command", NOT_FOUND));
		loaded.put("itemTeleporter_commandOther", blockConfig.getString("block.itemTeleporter.permissions.commandOther", NOT_FOUND));
		
		loaded.put("grinder_place", blockConfig.getString("block.grinder.permissions.place", NOT_FOUND));
		loaded.put("grinder_break", blockConfig.getString("block.grinder.permissions.break", NOT_FOUND));
		loaded.put("grinder_breakOther", blockConfig.getString("block.grinder.permissions.breakOther", NOT_FOUND));
		loaded.put("grinder_command", blockConfig.getString("block.grinder.permissions.command", NOT_FOUND));
		loaded.put("grinder_commandOther", blockConfig.getString("block.grinder.permissions.commandOther", NOT_FOUND));
		
		loaded.put("largeTeleporter_place", blockConfig.getString("block.largeTeleporter.permissions.place", NOT_FOUND));
		loaded.put("largeTeleporter_break", blockConfig.getString("block.largeTeleporter.permissions.break", NOT_FOUND));
		loaded.put("largeTeleporter_breakOther", blockConfig.getString("block.largeTeleporter.permissions.breakOther", NOT_FOUND));
		loaded.put("largeTeleporter_command", blockConfig.getString("block.largeTeleporter.permissions.command", NOT_FOUND));
		loaded.put("largeTeleporter_commandOther", blockConfig.getString("block.largeTeleporter.permissions.commandOther", NOT_FOUND));
		loaded.put("largeTeleporter_use", blockConfig.getString("block.largeTeleporter.permissions.use", NOT_FOUND));
		loaded.put("largeTeleporter_useOther", blockConfig.getString("block.largeTeleporter.permissions.useOther", NOT_FOUND));
		
		loaded.put("chunkLoader_place", blockConfig.getString("block.chunkLoader.permissions.place", NOT_FOUND));
		loaded.put("chunkLoader_break", blockConfig.getString("block.chunkLoader.permissions.break", NOT_FOUND));
		loaded.put("chunkLoader_breakOther", blockConfig.getString("block.chunkLoader.permissions.breakOther", NOT_FOUND));
		loaded.put("chunkLoader_command", blockConfig.getString("block.chunkLoader.permissions.command", NOT_FOUND));
		loaded.put("chunkLoader_commandOther", blockConfig.getString("block.chunkLoader.permissions.commandOther", NOT_FOUND));
		
		loaded.put("elevator_place", blockConfig.getString("block.elevator.permissions.place", NOT_FOUND));
		loaded.put("elevator_break", blockConfig.getString("block.elevator.permissions.break", NOT_FOUND));
		loaded.put("elevator_breakOther", blockConfig.getString("block.elevator.permissions.breakOther", NOT_FOUND));
		loaded.put("elevator_command", blockConfig.getString("block.elevator.permissions.command", NOT_FOUND));
		loaded.put("elevator_commandOther", blockConfig.getString("block.elevator.permissions.commandOther", NOT_FOUND));
		
		loaded.put("animalGrowth_place", blockConfig.getString("block.animalGrowth.permissions.place", NOT_FOUND));
		loaded.put("animalGrowth_break", blockConfig.getString("block.animalGrowth.permissions.break", NOT_FOUND));
		loaded.put("animalGrowth_breakOther", blockConfig.getString("block.animalGrowth.permissions.breakOther", NOT_FOUND));
		loaded.put("animalGrowth_command", blockConfig.getString("block.animalGrowth.permissions.command", NOT_FOUND));
		loaded.put("animalGrowth_commandOther", blockConfig.getString("block.animalGrowth.permissions.commandOther", NOT_FOUND));
		
		loaded.put("plantFarmer_place", blockConfig.getString("block.plantFarmer.permissions.place", NOT_FOUND));
		loaded.put("plantFarmer_break", blockConfig.getString("block.plantFarmer.permissions.break", NOT_FOUND));
		loaded.put("plantFarmer_breakOther", blockConfig.getString("block.plantFarmer.permissions.breakOther", NOT_FOUND));
		loaded.put("plantFarmer_command", blockConfig.getString("block.plantFarmer.permissions.command", NOT_FOUND));
		loaded.put("plantFarmer_commandOther", blockConfig.getString("block.plantFarmer.permissions.commandOther", NOT_FOUND));
		
		loaded.put("generator_place", blockConfig.getString("block.generator.permissions.place", NOT_FOUND));
		loaded.put("generator_break", blockConfig.getString("block.generator.permissions.break", NOT_FOUND));
		loaded.put("generator_breakOther", blockConfig.getString("block.generator.permissions.breakOther", NOT_FOUND));
		loaded.put("generator_command", blockConfig.getString("block.generator.permissions.command", NOT_FOUND));
		loaded.put("generator_commandOther", blockConfig.getString("block.generator.permissions.commandOther", NOT_FOUND));
		
		loaded.put("miner_place", blockConfig.getString("block.miner.permissions.place", NOT_FOUND));
		loaded.put("miner_break", blockConfig.getString("block.miner.permissions.break", NOT_FOUND));
		loaded.put("miner_breakOther", blockConfig.getString("block.miner.permissions.breakOther", NOT_FOUND));
		loaded.put("miner_command", blockConfig.getString("block.miner.permissions.command", NOT_FOUND));
		loaded.put("miner_commandOther", blockConfig.getString("block.miner.permissions.commandOther", NOT_FOUND));
		
		loaded.put("spawnPointTeleporter_use", itemConfig.getString("item.spawnPointTeleporter.permissions.use", NOT_FOUND));
		loaded.put("spawnPointTeleporter_command", itemConfig.getString("item.spawnPointTeleporter.permissions.command", NOT_FOUND));
		loaded.put("spawnPointTeleporter_commandOther", itemConfig.getString("item.spawnPointTeleporter.permissions.commandOther", NOT_FOUND));
		
		loaded.put("radio_use", itemConfig.getString("item.radio.permissions.use", NOT_FOUND));
		loaded.put("radio_command", itemConfig.getString("item.radio.permissions.command", NOT_FOUND));
		loaded.put("radio_commandOther", itemConfig.getString("item.radio.permissions.commandOther", NOT_FOUND));
		
		loaded.put("iTool_use", itemConfig.getString("item.iTool.permissions.use", NOT_FOUND));
		loaded.put("iTool_useCraft", itemConfig.getString("item.iTool.permissions.useCraft", NOT_FOUND));
		loaded.put("iTool_useEnchant", itemConfig.getString("item.iTool.permissions.useEnchant", NOT_FOUND));
		loaded.put("iTool_useAnvil", itemConfig.getString("item.iTool.permissions.useAnvil", NOT_FOUND));
		loaded.put("iTool_useInventory", itemConfig.getString("item.iTool.permissions.useBackpack", NOT_FOUND));
		loaded.put("iTool_command", itemConfig.getString("item.iTool.permissions.command", NOT_FOUND));
		loaded.put("iTool_commandOther", itemConfig.getString("item.iTool.permissions.commandOther", NOT_FOUND));
		
		loaded.put("dyeWand_use", itemConfig.getString("item.dyeWand.permissions.use", NOT_FOUND));
		loaded.put("dyeWand_command", itemConfig.getString("item.dyeWand.permissions.command", NOT_FOUND));
		loaded.put("dyeWand_commandOther", itemConfig.getString("item.dyeWand.permissions.commandOther", NOT_FOUND));
		
		loaded.put("flamethrower_use", itemConfig.getString("item.flamethrower.permissions.use", NOT_FOUND));
		loaded.put("flamethrower_command", itemConfig.getString("item.flamethrower.permissions.command", NOT_FOUND));
		loaded.put("flamethrower_commandOther", itemConfig.getString("item.flamethrower.permissions.commandOther", NOT_FOUND));
		
		loaded.put("remote_inventory_see", mainConfig.getString("remoteInventoryCommand.see", NOT_FOUND));
		loaded.put("remote_inventory_edit", mainConfig.getString("remoteInventoryCommand.edit", NOT_FOUND));
	}
	
}
