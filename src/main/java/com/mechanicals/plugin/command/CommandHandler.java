package com.mechanicals.plugin.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.mechanicals.plugin.utils.EntityUtils;

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
	public static boolean parseIncomingCommand(CommandSender sender, String command, String[] args) {
		switch (command) {
		case "blockplacer":
			new BlockPlacerCommand().run(sender, args);
			return true;
		case "blockbreaker":
			new BlockBreakerCommand().run(sender, args);
			return true;
		case "treecutter":
			new TreeCutterCommand().run(sender, args);
			return true;
		case "entityteleporter":
			new EntityTeleporterCommand().run(sender, args);
			return true;
		case "itemteleporter":
			new ItemTeleporterCommand().run(sender, args);
			return true;
		case "grinder":
			new GrinderCommand().run(sender, args);
			return true;
		case "largeteleporter":
			new LargeTeleporterCommand().run(sender, args);
			return true;
		case "chunkloader":
			new ChunkLoaderCommand().run(sender, args);
			return true;
		case "elevator":
			new ElevatorCommand().run(sender, args);
			return true;
		case "animalgrowth":
			new AnimalGrowthCommand().run(sender, args);
			return true;
		case "plantfarmer":
			new PlantFarmerCommand().run(sender, args);
			return true;
		case "generator":
			new GeneratorCommand().run(sender, args);
			return true;
		case "miner":
			new MinerCommand().run(sender, args);
			return true;
		case "autocrafter":
			new AutoCrafterCommand().run(sender, args);
			return true;
		case "spawnpointteleporter":
			new SpawnPointTeleporterCommand().run(sender, args);
			return true;
		case "mechradio":
			new RadioCommand().run(sender, args);
			return true;
		case "itool":
			new IToolCommand().run(sender, args);
			return true;
		case "dyewand":
			new DyeWandCommand().run(sender, args);
			return true;
		case "flamethrower":
			new FlamethrowerCommand().run(sender, args);
			return true;
		case "mechanical":
			new MechanicalCommand().run(sender, args);
			return true;
		case "mechanicalconfig":
			new MechanicalConfigCommand().run(sender, args);
			return true;
		case "remotemechanicalinventory":
		case "rmi":
			new RemoteInventoryCommand().run(sender, args);
			return true;
		case "schemnow":
			new SchemCommand().run(sender, args);
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
	 * @since 1.3 (impl. 2.4.1)
	 * @author IballisticBoss
	 */
	public static List<String> parseTabComplete(CommandSender sender, String command, String[] args) {
		switch (command) {
		case "blockplacer":
		case "blockbreaker":
		case "treecutter":
		case "entityteleporter":
		case "itemteleporter":
		case "grinder":
		case "largeteleporter":
		case "chunkloader":
		case "elevator":
		case "animalgrowth":
		case "plantfarmer":
		case "generator":
		case "miner":
		case "autocrafter":
		case "spawnpointteleporter":
		case "mechradio":
		case "itool":
		case "dyewand":
		case "flamethrower":
			return EntityUtils.getOnlinePlayersWithPriority(sender);
		case "mechanical":
			return Arrays.asList(new String[] {"blocks", "items"});
		case "mechanicalconfig":
			return Arrays.asList(new String[] {"view", "set", "confirm", "reset"});
		default:
			return null;
		}
	}
}