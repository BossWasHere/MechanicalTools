package com.mechanicals.plugin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mechanicals.plugin.RegisteredCommand;
import com.mechanicals.plugin.utils.EntityUtils;

public class EntityTeleporterCommand extends RegisteredCommand {
	
	public EntityTeleporterCommand() {
		super();
	}

	public void run(CommandSender sender, String[] args) {
		if (args.length < 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission(plugin.entityTeleporter.commandPerm())) {
					plugin.entityTeleporter.giveBlockItem(player);
				} else {
					sender.sendMessage(plugin.texts.noPermission);
				}
			} else {
				sender.sendMessage(plugin.texts.onlyPlayers);
			}
		} else {
			if (sender.hasPermission(plugin.entityTeleporter.commandOtherPerm())) {
				Player target = EntityUtils.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(plugin.texts.playerNotFound(args[0]));
				} else {
					plugin.entityTeleporter.giveBlockItem(target);
					sender.sendMessage(plugin.texts.givenBlockTo(target.getName()));
					target.sendMessage(plugin.texts.recievedBlockFrom(sender.getName()));
				}
			} else {
				sender.sendMessage(plugin.texts.noPermission);
			}
		}
	}
}
