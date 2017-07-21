package com.mechanicals.plugin.configuration;

import java.util.HashMap;
import java.util.Map;

public class PermissionIndex {

	private final Map<String, String> loaded = new HashMap<>();
	
	private final String NOT_FOUND = "mechanical.omni";
	
	public PermissionIndex(ConfigurationUnit mainConfig, ConfigurationUnit blockConfig, ConfigurationUnit itemConfig) {
		loaded.put("blockBreaker_place", blockConfig.getString("block.blockBreaker.permissions.place"));
		loaded.put("blockBreaker_break", blockConfig.getString("block.blockBreaker.permissions.break"));
		loaded.put("blockBreaker_breakOther", blockConfig.getString("block.blockBreaker.permissions.breakOther"));
		loaded.put("blockBreaker_command", blockConfig.getString("block.blockBreaker.permissions.command"));
		loaded.put("blockBreaker_commandOther", blockConfig.getString("block.blockBreaker.permissions.commandOther"));
		
		loaded.put("blockPlacer_place", blockConfig.getString("block.blockPlacer.permissions.place"));
		loaded.put("blockPlacer_break", blockConfig.getString("block.blockPlacer.permissions.break"));
		loaded.put("blockPlacer_breakOther", blockConfig.getString("block.blockPlacer.permissions.breakOther"));
		loaded.put("blockPlacer_command", blockConfig.getString("block.blockPlacer.permissions.command"));
		loaded.put("blockPlacer_commandOther", blockConfig.getString("block.blockPlacer.permissions.commandOther"));
		
		loaded.put("treeCutter_place", blockConfig.getString("block.treeCutter.permissions.place"));
		loaded.put("treeCutter_break", blockConfig.getString("block.treeCutter.permissions.break"));
		loaded.put("treeCutter_breakOther", blockConfig.getString("block.treeCutter.permissions.breakOther"));
		loaded.put("treeCutter_command", blockConfig.getString("block.treeCutter.permissions.command"));
		loaded.put("treeCutter_commandOther", blockConfig.getString("block.treeCutter.permissions.commandOther"));
		
		loaded.put("entityTeleporter_place", blockConfig.getString("block.entityTeleporter.permissions.place"));
		loaded.put("entityTeleporter_break", blockConfig.getString("block.entityTeleporter.permissions.break"));
		loaded.put("entityTeleporter_breakOther", blockConfig.getString("block.entityTeleporter.permissions.breakOther"));
		loaded.put("entityTeleporter_command", blockConfig.getString("block.entityTeleporter.permissions.command"));
		loaded.put("entityTeleporter_commandOther", blockConfig.getString("block.entityTeleporter.permissions.commandOther"));
		
		loaded.put("itemTeleporter_place", blockConfig.getString("block.itemTeleporter.permissions.place"));
		loaded.put("itemTeleporter_break", blockConfig.getString("block.itemTeleporter.permissions.break"));
		loaded.put("itemTeleporter_breakOther", blockConfig.getString("block.itemTeleporter.permissions.breakOther"));
		loaded.put("itemTeleporter_command", blockConfig.getString("block.itemTeleporter.permissions.command"));
		loaded.put("itemTeleporter_commandOther", blockConfig.getString("block.itemTeleporter.permissions.commandOther"));
		
		loaded.put("grinder_place", blockConfig.getString("block.grinder.permissions.place"));
		loaded.put("grinder_break", blockConfig.getString("block.grinder.permissions.break"));
		loaded.put("grinder_breakOther", blockConfig.getString("block.grinder.permissions.breakOther"));
		loaded.put("grinder_command", blockConfig.getString("block.grinder.permissions.command"));
		loaded.put("grinder_commandOther", blockConfig.getString("block.grinder.permissions.commandOther"));
		
		loaded.put("largeTeleporter_place", blockConfig.getString("block.largeTeleporter.permissions.place"));
		loaded.put("largeTeleporter_break", blockConfig.getString("block.largeTeleporter.permissions.break"));
		loaded.put("largeTeleporter_breakOther", blockConfig.getString("block.largeTeleporter.permissions.breakOther"));
		loaded.put("largeTeleporter_command", blockConfig.getString("block.largeTeleporter.permissions.command"));
		loaded.put("largeTeleporter_commandOther", blockConfig.getString("block.largeTeleporter.permissions.commandOther"));
		loaded.put("largeTeleporter_use", blockConfig.getString("block.largeTeleporter.permissions.use"));
		loaded.put("largeTeleporter_useOther", blockConfig.getString("block.largeTeleporter.permissions.useOther"));
		
		loaded.put("chunkLoader_place", blockConfig.getString("block.chunkLoader.permissions.place"));
		loaded.put("chunkLoader_break", blockConfig.getString("block.chunkLoader.permissions.break"));
		loaded.put("chunkLoader_breakOther", blockConfig.getString("block.chunkLoader.permissions.breakOther"));
		loaded.put("chunkLoader_command", blockConfig.getString("block.chunkLoader.permissions.command"));
		loaded.put("chunkLoader_commandOther", blockConfig.getString("block.chunkLoader.permissions.commandOther"));
		
		loaded.put("elevator_place", blockConfig.getString("block.elevator.permissions.place"));
		loaded.put("elevator_break", blockConfig.getString("block.elevator.permissions.break"));
		loaded.put("elevator_breakOther", blockConfig.getString("block.elevator.permissions.breakOther"));
		loaded.put("elevator_command", blockConfig.getString("block.elevator.permissions.command"));
		loaded.put("elevator_commandOther", blockConfig.getString("block.elevator.permissions.commandOther"));
		
		loaded.put("animalGrowth_place", blockConfig.getString("block.animalGrowth.permissions.place"));
		loaded.put("animalGrowth_break", blockConfig.getString("block.animalGrowth.permissions.break"));
		loaded.put("animalGrowth_breakOther", blockConfig.getString("block.animalGrowth.permissions.breakOther"));
		loaded.put("animalGrowth_command", blockConfig.getString("block.animalGrowth.permissions.command"));
		loaded.put("animalGrowth_commandOther", blockConfig.getString("block.animalGrowth.permissions.commandOther"));
		
		loaded.put("plantFarmer_place", blockConfig.getString("block.plantFarmer.permissions.place"));
		loaded.put("plantFarmer_break", blockConfig.getString("block.plantFarmer.permissions.break"));
		loaded.put("plantFarmer_breakOther", blockConfig.getString("block.plantFarmer.permissions.breakOther"));
		loaded.put("plantFarmer_command", blockConfig.getString("block.plantFarmer.permissions.command"));
		loaded.put("plantFarmer_commandOther", blockConfig.getString("block.plantFarmer.permissions.commandOther"));
		
		loaded.put("generator_place", blockConfig.getString("block.generator.permissions.place"));
		loaded.put("generator_break", blockConfig.getString("block.generator.permissions.break"));
		loaded.put("generator_breakOther", blockConfig.getString("block.generator.permissions.breakOther"));
		loaded.put("generator_command", blockConfig.getString("block.generator.permissions.command"));
		loaded.put("generator_commandOther", blockConfig.getString("block.generator.permissions.commandOther"));
		
		loaded.put("miner_place", blockConfig.getString("block.miner.permissions.place"));
		loaded.put("miner_break", blockConfig.getString("block.miner.permissions.break"));
		loaded.put("miner_breakOther", blockConfig.getString("block.miner.permissions.breakOther"));
		loaded.put("miner_command", blockConfig.getString("block.miner.permissions.command"));
		loaded.put("miner_commandOther", blockConfig.getString("block.miner.permissions.commandOther"));
		
		loaded.put("autoCrafter_place", blockConfig.getString("block.autoCrafter.permissions.place"));
		loaded.put("autoCrafter_break", blockConfig.getString("block.autoCrafter.permissions.break"));
		loaded.put("autoCrafter_breakOther", blockConfig.getString("block.autoCrafter.permissions.breakOther"));
		loaded.put("autoCrafter_command", blockConfig.getString("block.autoCrafter.permissions.command"));
		loaded.put("autoCrafter_commandOther", blockConfig.getString("block.autoCrafter.permissions.commandOther"));
		
		loaded.put("spawnPointTeleporter_use", itemConfig.getString("item.spawnPointTeleporter.permissions.use"));
		loaded.put("spawnPointTeleporter_command", itemConfig.getString("item.spawnPointTeleporter.permissions.command"));
		loaded.put("spawnPointTeleporter_commandOther", itemConfig.getString("item.spawnPointTeleporter.permissions.commandOther"));
		
		loaded.put("radio_use", itemConfig.getString("item.radio.permissions.use"));
		loaded.put("radio_command", itemConfig.getString("item.radio.permissions.command"));
		loaded.put("radio_commandOther", itemConfig.getString("item.radio.permissions.commandOther"));
		
		loaded.put("iTool_use", itemConfig.getString("item.iTool.permissions.use"));
		loaded.put("iTool_useCraft", itemConfig.getString("item.iTool.permissions.useCraft"));
		loaded.put("iTool_useEnchant", itemConfig.getString("item.iTool.permissions.useEnchant"));
		loaded.put("iTool_useAnvil", itemConfig.getString("item.iTool.permissions.useAnvil"));
		loaded.put("iTool_useInventory", itemConfig.getString("item.iTool.permissions.useBackpack"));
		loaded.put("iTool_command", itemConfig.getString("item.iTool.permissions.command"));
		loaded.put("iTool_commandOther", itemConfig.getString("item.iTool.permissions.commandOther"));
		
		loaded.put("dyeWand_use", itemConfig.getString("item.dyeWand.permissions.use"));
		loaded.put("dyeWand_command", itemConfig.getString("item.dyeWand.permissions.command"));
		loaded.put("dyeWand_commandOther", itemConfig.getString("item.dyeWand.permissions.commandOther"));
		
		loaded.put("flamethrower_use", itemConfig.getString("item.flamethrower.permissions.use"));
		loaded.put("flamethrower_command", itemConfig.getString("item.flamethrower.permissions.command"));
		loaded.put("flamethrower_commandOther", itemConfig.getString("item.flamethrower.permissions.commandOther"));
		
		loaded.put("remote_inventory_see", mainConfig.getString("remoteInventoryCommand.see"));
		loaded.put("remote_inventory_edit", mainConfig.getString("remoteInventoryCommand.edit"));
		loaded.put("reload_config", mainConfig.getString("reloadConfigPermission"));
		
	}
	
	public String get(String key) {
		return loaded.get(key) == null ? NOT_FOUND : loaded.get(key);
	}
	
}
