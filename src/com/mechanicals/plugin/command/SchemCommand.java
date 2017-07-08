package com.mechanicals.plugin.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.world.InvalidLocationException;

public class SchemCommand {

	public static void run(MechMain plugin, CommandSender sender, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length >= 7) {
				String name = args[0];
				int x1 = Integer.parseInt(args[1]);
				int y1 = Integer.parseInt(args[2]);
				int z1 = Integer.parseInt(args[3]);
				int x2 = Integer.parseInt(args[4]);
				int y2 = Integer.parseInt(args[5]);
				int z2 = Integer.parseInt(args[6]);
				Location start = new Location(player.getLocation().getWorld(), x1, y1, z1);
				Location end = new Location(player.getLocation().getWorld(), x2, y2, z2);
				
				try {
					plugin.worldEditManager.saveSchematic(name, player, start, end);
				} catch (YAMLException e) {
					e.printStackTrace();
				} catch (InvalidLocationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
