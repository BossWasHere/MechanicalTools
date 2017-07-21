package com.mechanicals.plugin.command;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.mechanicals.plugin.RegisteredCommand;

public class MechanicalReload extends RegisteredCommand {

	public MechanicalReload() {
		super();
	}
	
	public void run(CommandSender sender, String[] args) {
		if (sender.hasPermission(plugin.permissions.get("reloadConfigPermission"))) {
			plugin.reloadConfig();
			sender.sendMessage(ChatColor.GREEN + "Reloaded all configuration!");
		}
	}
}
