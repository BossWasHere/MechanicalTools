package com.mechanicals.plugin.server;

import org.bukkit.scheduler.BukkitRunnable;

import com.mechanicals.plugin.MechMain;

public abstract class MechRunnable extends BukkitRunnable {

	protected final MechMain plugin;
	
	public MechRunnable() {
		plugin = MechMain.plugin;
	}
}
