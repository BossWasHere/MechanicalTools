package com.mechanicals.plugin.command;

import java.util.List;

import org.bukkit.command.CommandSender;

import com.mechanicals.plugin.MechMain;

/**
 * The main Command Handling System for this plugin
 *
 */
public class CommandHandler {

	/**
	 * Reads the incoming command and dispatches it to a secondary handler afterwards
	 * @param plugin the main class of the plugin
	 * @param sender the object which sent the command
	 * @param command the name of the command
	 * @param args the arguments associated with the command
	 * @return <b>true</b> if command was successfully handled, <b>false</b> otherwise
	 * @since 1.0
	 * @author IballisticBoss
	 */
	public static boolean parseIncomingCommand(MechMain plugin, CommandSender sender, String command, String[] args) {
		switch (command) {
		case "blockplacer":
			BlockPlacerCommand.run(plugin, sender, args);
			return true;
		case "blockbreaker":
			BlockBreakerCommand.run(plugin, sender, args);
			return true;
		case "treecutter":
			TreeCutterCommand.run(plugin, sender, args);
			return true;
		case "entityteleporter":
			EntityTeleporterCommand.run(plugin, sender, args);
			return true;
		case "itemteleporter":
			ItemTeleporterCommand.run(plugin, sender, args);
			return true;
		case "grinder":
			GrinderCommand.run(plugin, sender, args);
			return true;
		case "largeteleporter":
			LargeTeleporterCommand.run(plugin, sender, args);
			return true;
		case "chunkloader":
			ChunkLoaderCommand.run(plugin, sender, args);
			return true;
		case "elevator":
			ElevatorCommand.run(plugin, sender, args);
			return true;
		case "animalgrowth":
			AnimalGrowthCommand.run(plugin, sender, args);
			return true;
		case "plantfarmer":
			PlantFarmerCommand.run(plugin, sender, args);
			return true;
		case "spawnpointteleporter":
			SpawnPointTeleporterCommand.run(plugin, sender, args);
			return true;
		case "mechradio":
			RadioCommand.run(plugin, sender, args);
			return true;
		case "itool":
			IToolCommand.run(plugin, sender, args);
			return true;
		case "dyewand":
			DyeWandCommand.run(plugin, sender, args);
			return true;
		case "flamethrower":
			FlamethrowerCommand.run(plugin, sender, args);
			return true;
		case "mechanical":
			MechanicalCommand.run(plugin, sender, args);
			return true;
		case "mechanicalconfig":
			MechanicalConfigCommand.run(plugin, sender, args);
			return true;
		}
		
		return false;
	}

	/**
	 * Identifies data to return to the TabCompleter based on the command, arguments and sender
	 * @param plugin the main class of the plugin
	 * @param sender the object which sent the command
	 * @param command the name of the command 
	 * @param args args the arguments associated with the command
	 * @return a String List object with possible tab ideas
	 * @since 1.3
	 * @author IballisticBoss
	 */
	public static List<String> parseTabComplete(MechMain plugin, CommandSender sender, String command, String[] args) {
		return null;
	}
}