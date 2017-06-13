package com.mechanicals.plugin.task.extra;

import org.bukkit.scheduler.BukkitRunnable;

public class ConfirmCooldown extends BukkitRunnable {

	private final String name;
	private final int id;
	private final String data;
	private int time = 0;
	private final int cap;
	private boolean cancelled = false;
	
	public ConfirmCooldown(String name, String data, int id, int cap) {
		this.name = name;
		this.data = data;
		this.id = id;
		this.cap = cap;
	}

	@Override
	public synchronized void run() {
		if (time >= cap) {
			cancel();
		}
		time++;
	}
	
	@Override
	public synchronized void cancel() throws IllegalStateException {
		if (!cancelled) super.cancel();
		cancelled = true;
	}
	
	public synchronized boolean isCooled() {
		return cancelled;
	}
	
	public synchronized boolean isCancelled() {
		return cancelled;
	}
	
	public String getData() {
		return data;
	}
	
	public synchronized int getCap() {
		return cap;
	}
	
	public synchronized int getElapsed() {
		return time;
	}
	
	public boolean checkID(String name, int id) {
		return name.equalsIgnoreCase(this.name) && id == this.id;
	}
	
}
