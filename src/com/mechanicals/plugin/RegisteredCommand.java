package com.mechanicals.plugin;

import org.bukkit.command.CommandSender;

public abstract class RegisteredCommand {

	protected final MechMain plugin;
	
	public RegisteredCommand() {
		this.plugin = MechMain.plugin;
	}
	
	public abstract void run(CommandSender sender, String[] args);
}
