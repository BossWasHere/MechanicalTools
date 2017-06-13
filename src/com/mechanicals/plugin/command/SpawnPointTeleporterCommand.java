package com.mechanicals.plugin.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mechanicals.plugin.MechMain;

public class SpawnPointTeleporterCommand {

	public static void run(MechMain plugin, CommandSender sender, String[] args) {
		if (args.length < 1) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission(plugin.permissions.bedTeleport_command)) {
					plugin.bedTeleporter.giveItem(player);
				} else {
					sender.sendMessage(plugin.texts.noPermission);
				}
			} else {
				sender.sendMessage(plugin.texts.onlyPlayers);
			}
		} else {
			if (sender.hasPermission(plugin.permissions.bedTeleport_commandOther)) {
				String t = args[0];
				Player target = null;
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (p.getName().equalsIgnoreCase(t)) target = p;
				}
				if (target == null) {
					sender.sendMessage(plugin.texts.playerNotFound(t));
				} else {
					plugin.bedTeleporter.giveItem(target);
					sender.sendMessage(plugin.texts.givenItemTo(target.getName()));
					target.sendMessage(plugin.texts.recievedItemFrom(sender.getName()));
				}
			} else {
				sender.sendMessage(plugin.texts.noPermission);
			}
		}
	}
}