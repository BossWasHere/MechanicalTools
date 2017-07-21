package com.mechanicals.plugin.command;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import com.mechanicals.plugin.RegisteredCommand;
import com.mechanicals.plugin.world.InvalidLocationException;
import com.mechanicals.plugin.world.WorldEditManager;

public class SchemCommand extends RegisteredCommand {

	public SchemCommand() {
		super();
	}
	
	public void run(CommandSender sender, String[] args) {
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
					WorldEditManager.saveSchematic(name, player, start, end);
				} catch (YAMLException e) {
					e.printStackTrace();
				} catch (InvalidLocationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
