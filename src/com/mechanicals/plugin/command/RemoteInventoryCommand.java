package com.mechanicals.plugin.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.mechanicals.plugin.InventoryHandler;
import com.mechanicals.plugin.RegisteredCommand;
import com.mechanicals.plugin.utils.EntityUtils;

public class RemoteInventoryCommand extends RegisteredCommand {
	
	public RemoteInventoryCommand() {
		super();
	}

	public void run(CommandSender sender, String[] args) {
		if (args.length > 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission(plugin.permissions.loaded.get("remote_inventory_see"))) {
					Player target = EntityUtils.getPlayer(args[0]);
					if (target != null) player.openInventory(InventoryHandler.loadRemoteInventoryForPlayer(target));
					else player.sendMessage(plugin.texts.playerNotFound(args[0]));
				} else {
					sender.sendMessage(plugin.texts.noPermission);
				}
			} else {
				sender.sendMessage(plugin.texts.onlyPlayers);
			}
		} else {
			sender.sendMessage(plugin.texts.specifyPlayer);
		}
	}
}
