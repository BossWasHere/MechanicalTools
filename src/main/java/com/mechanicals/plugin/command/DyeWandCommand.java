package com.mechanicals.plugin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mechanicals.plugin.RegisteredCommand;
import com.mechanicals.plugin.utils.EntityUtils;

public class DyeWandCommand extends RegisteredCommand {

	public DyeWandCommand() {
		super();
	}
	
	public void run(CommandSender sender, String[] args) {
		if (args.length < 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission(plugin.dyeWand.commandPerm())) {
					plugin.dyeWand.giveItem(player);
				} else {
					sender.sendMessage(plugin.texts.noPermission);
				}
			} else {
				sender.sendMessage(plugin.texts.onlyPlayers);
			}
		} else {
			if (sender.hasPermission(plugin.dyeWand.commandOtherPerm())) {
				Player target = EntityUtils.getPlayer(args[0]);
				if (target == null) {
					sender.sendMessage(plugin.texts.playerNotFound(args[0]));
				} else {
					plugin.dyeWand.giveItem(target);
					sender.sendMessage(plugin.texts.givenItemTo(target.getName()));
					target.sendMessage(plugin.texts.recievedItemFrom(sender.getName()));
				}
			} else {
				sender.sendMessage(plugin.texts.noPermission);
			}
		}
	}
}
