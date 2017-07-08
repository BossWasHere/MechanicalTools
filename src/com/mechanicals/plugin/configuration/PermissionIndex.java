package com.mechanicals.plugin.configuration;

public class PermissionIndex {

	public final String remote_inventory_see;
	public final String remote_inventory_edit;
	
	public final String blockBreaker_place;
	public final String blockBreaker_break;
	public final String blockBreaker_breakOther;
	public final String blockBreaker_command;
	public final String blockBreaker_commandOther;
	
	public final String blockPlacer_place; 
	public final String blockPlacer_break;
	public final String blockPlacer_breakOther;
	public final String blockPlacer_command;
	public final String blockPlacer_commandOther;
	
	public final String treeCutter_place; 
	public final String treeCutter_break;
	public final String treeCutter_breakOther;
	public final String treeCutter_command;
	public final String treeCutter_commandOther;
	
	public final String entityTeleporter_place; 
	public final String entityTeleporter_break;
	public final String entityTeleporter_breakOther;
	public final String entityTeleporter_command;
	public final String entityTeleporter_commandOther;
	
	public final String itemTeleporter_place; 
	public final String itemTeleporter_break;
	public final String itemTeleporter_breakOther;
	public final String itemTeleporter_command;
	public final String itemTeleporter_commandOther;
	
	public final String grinder_place; 
	public final String grinder_break;
	public final String grinder_breakOther;
	public final String grinder_command;
	public final String grinder_commandOther;
	
	public final String largeTeleporter_place; 
	public final String largeTeleporter_break;
	public final String largeTeleporter_breakOther;
	public final String largeTeleporter_command;
	public final String largeTeleporter_commandOther;
	public final String largeTeleporter_use;
	public final String largeTeleporter_useOther;
	
	public final String chunkLoader_place; 
	public final String chunkLoader_break;
	public final String chunkLoader_breakOther;
	public final String chunkLoader_command;
	public final String chunkLoader_commandOther;
	
	public final String elevator_place; 
	public final String elevator_break;
	public final String elevator_breakOther;
	public final String elevator_command;
	public final String elevator_commandOther;
	
	public final String animalGrowth_place; 
	public final String animalGrowth_break;
	public final String animalGrowth_breakOther;
	public final String animalGrowth_command;
	public final String animalGrowth_commandOther;
	
	public final String plantFarmer_place; 
	public final String plantFarmer_break;
	public final String plantFarmer_breakOther;
	public final String plantFarmer_command;
	public final String plantFarmer_commandOther;
	
	public final String bedTeleport_use;
	public final String bedTeleport_command;
	public final String bedTeleport_commandOther;
	
	public final String radio_use;
	public final String radio_command;
	public final String radio_commandOther;
	
	public final String iTool_use;
	public final String iTool_useCraft;
	public final String iTool_useEnchant;
	public final String iTool_useInventory;
	public final String iTool_command;
	public final String iTool_commandOther;
	public final String iTool_useAnvil;
	
	public final String dyeWand_use;
	public final String dyeWand_command;
	public final String dyeWand_commandOther;
	
	public final String flamethrower_use;
	public final String flamethrower_command;
	public final String flamethrower_commandOther;
	
	public PermissionIndex(ConfigurationUnit mainConfig, ConfigurationUnit blockConfig, ConfigurationUnit itemConfig) {
		blockBreaker_place = blockConfig.getString("block.blockBreaker.permissions.place");
		blockBreaker_break = blockConfig.getString("block.blockBreaker.permissions.break");
		blockBreaker_breakOther = blockConfig.getString("block.blockBreaker.permissions.breakOther");
		blockBreaker_command = blockConfig.getString("block.blockBreaker.permissions.command");
		blockBreaker_commandOther = blockConfig.getString("block.blockBreaker.permissions.commandOther");
		
		blockPlacer_place = blockConfig.getString("block.blockPlacer.permissions.place");
		blockPlacer_break = blockConfig.getString("block.blockPlacer.permissions.break");
		blockPlacer_breakOther = blockConfig.getString("block.blockPlacer.permissions.breakOther");
		blockPlacer_command = blockConfig.getString("block.blockPlacer.permissions.command");
		blockPlacer_commandOther = blockConfig.getString("block.blockPlacer.permissions.commandOther");
		
		treeCutter_place = blockConfig.getString("block.treeCutter.permissions.place");
		treeCutter_break = blockConfig.getString("block.treeCutter.permissions.break");
		treeCutter_breakOther = blockConfig.getString("block.treeCutter.permissions.breakOther");
		treeCutter_command = blockConfig.getString("block.treeCutter.permissions.command");
		treeCutter_commandOther = blockConfig.getString("block.treeCutter.permissions.commandOther");
		
		entityTeleporter_place = blockConfig.getString("block.entityTeleporter.permissions.place");
		entityTeleporter_break = blockConfig.getString("block.entityTeleporter.permissions.break");
		entityTeleporter_breakOther = blockConfig.getString("block.entityTeleporter.permissions.breakOther");
		entityTeleporter_command = blockConfig.getString("block.entityTeleporter.permissions.command");
		entityTeleporter_commandOther = blockConfig.getString("block.entityTeleporter.permissions.commandOther");
		
		itemTeleporter_place = blockConfig.getString("block.itemTeleporter.permissions.place");
		itemTeleporter_break = blockConfig.getString("block.itemTeleporter.permissions.break");
		itemTeleporter_breakOther = blockConfig.getString("block.itemTeleporter.permissions.breakOther");
		itemTeleporter_command = blockConfig.getString("block.itemTeleporter.permissions.command");
		itemTeleporter_commandOther = blockConfig.getString("block.itemTeleporter.permissions.commandOther");
		
		grinder_place = blockConfig.getString("block.grinder.permissions.place");
		grinder_break = blockConfig.getString("block.grinder.permissions.break");
		grinder_breakOther = blockConfig.getString("block.grinder.permissions.breakOther");
		grinder_command = blockConfig.getString("block.grinder.permissions.command");
		grinder_commandOther = blockConfig.getString("block.grinder.permissions.commandOther");
		
		largeTeleporter_place = blockConfig.getString("block.largeTeleporter.permissions.place");
		largeTeleporter_break = blockConfig.getString("block.largeTeleporter.permissions.break");
		largeTeleporter_breakOther = blockConfig.getString("block.largeTeleporter.permissions.breakOther");
		largeTeleporter_command = blockConfig.getString("block.largeTeleporter.permissions.command");
		largeTeleporter_commandOther = blockConfig.getString("block.largeTeleporter.permissions.commandOther");
		largeTeleporter_use = blockConfig.getString("block.largeTeleporter.permissions.use");
		largeTeleporter_useOther = blockConfig.getString("block.largeTeleporter.permissions.useOther");
		
		chunkLoader_place = blockConfig.getString("block.chunkLoader.permissions.place");
		chunkLoader_break = blockConfig.getString("block.chunkLoader.permissions.break");
		chunkLoader_breakOther = blockConfig.getString("block.chunkLoader.permissions.breakOther");
		chunkLoader_command = blockConfig.getString("block.chunkLoader.permissions.command");
		chunkLoader_commandOther = blockConfig.getString("block.chunkLoader.permissions.commandOther");
		
		elevator_place = blockConfig.getString("block.elevator.permissions.place");
		elevator_break = blockConfig.getString("block.elevator.permissions.break");
		elevator_breakOther = blockConfig.getString("block.elevator.permissions.breakOther");
		elevator_command = blockConfig.getString("block.elevator.permissions.command");
		elevator_commandOther = blockConfig.getString("block.elevator.permissions.commandOther");
		
		animalGrowth_place = blockConfig.getString("block.animalGrowth.permissions.place");
		animalGrowth_break = blockConfig.getString("block.animalGrowth.permissions.break");
		animalGrowth_breakOther = blockConfig.getString("block.animalGrowth.permissions.breakOther");
		animalGrowth_command = blockConfig.getString("block.animalGrowth.permissions.command");
		animalGrowth_commandOther = blockConfig.getString("block.animalGrowth.permissions.commandOther");
		
		plantFarmer_place = blockConfig.getString("block.plantFarmer.permissions.place");
		plantFarmer_break = blockConfig.getString("block.plantFarmer.permissions.break");
		plantFarmer_breakOther = blockConfig.getString("block.plantFarmer.permissions.breakOther");
		plantFarmer_command = blockConfig.getString("block.plantFarmer.permissions.command");
		plantFarmer_commandOther = blockConfig.getString("block.plantFarmer.permissions.commandOther");
		
		bedTeleport_use = itemConfig.getString("item.spawnPointTeleporter.permissions.use");
		bedTeleport_command = itemConfig.getString("item.spawnPointTeleporter.permissions.command");
		bedTeleport_commandOther = itemConfig.getString("item.spawnPointTeleporter.permissions.commandOther");
		
		radio_use = itemConfig.getString("item.radio.permissions.use");
		radio_command = itemConfig.getString("item.radio.permissions.command");
		radio_commandOther = itemConfig.getString("item.radio.permissions.commandOther");
		
		iTool_use = itemConfig.getString("item.iTool.permissions.use");
		iTool_useCraft = itemConfig.getString("item.iTool.permissions.useCraft");
		iTool_useEnchant = itemConfig.getString("item.iTool.permissions.useEnchant");
		iTool_useAnvil = itemConfig.getString("item.iTool.permissions.useAnvil");
		iTool_useInventory = itemConfig.getString("item.iTool.permissions.useBackpack");
		iTool_command = itemConfig.getString("item.iTool.permissions.command");
		iTool_commandOther = itemConfig.getString("item.iTool.permissions.commandOther");
		
		dyeWand_use = itemConfig.getString("item.dyeWand.permissions.use");
		dyeWand_command = itemConfig.getString("item.dyeWand.permissions.command");
		dyeWand_commandOther = itemConfig.getString("item.dyeWand.permissions.commandOther");
		
		flamethrower_use = itemConfig.getString("item.flamethrower.permissions.use");
		flamethrower_command = itemConfig.getString("item.flamethrower.permissions.command");
		flamethrower_commandOther = itemConfig.getString("item.flamethrower.permissions.commandOther");
		
		remote_inventory_see = mainConfig.getString("remoteInventoryCommand.see");
		remote_inventory_edit = mainConfig.getString("remoteInventoryCommand.edit");
	}
	
}
