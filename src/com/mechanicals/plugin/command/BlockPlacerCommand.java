package com.mechanicals.plugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mechanicals.plugin.RegisteredCommand;

public class BlockPlacerCommand extends RegisteredCommand {
	
	public BlockPlacerCommand() {
		super();
	}

	public void run(CommandSender sender, String[] args) {
		if (args.length < 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission(plugin.permissions.blockPlacer_command)) {
					plugin.blockPlacer.giveBlockItem(player);
				} else {
					sender.sendMessage(plugin.texts.noPermission);
				}
			} else {
				sender.sendMessage(plugin.texts.onlyPlayers);
			}
		} else {
			if (sender.hasPermission(plugin.permissions.blockPlacer_commandOther)) {
				String t = args[0];
				Player target = null;
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getName().equalsIgnoreCase(t)) target = p;
				}
				if (target == null) {
					sender.sendMessage(plugin.texts.playerNotFound(t));
				} else {
					plugin.blockPlacer.giveBlockItem(target);
					sender.sendMessage(plugin.texts.givenBlockTo(target.getName()));
					target.sendMessage(plugin.texts.recievedBlockFrom(sender.getName()));
				}
			} else {
				sender.sendMessage(plugin.texts.noPermission);
			}
		}
	}
}
