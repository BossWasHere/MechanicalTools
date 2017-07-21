package com.mechanicals.plugin.world;

import org.bukkit.Location;

public class InvalidLocationException extends Exception {

	private static final long serialVersionUID = 4487957496643777199L;

	public InvalidLocationException() {
		System.err.println("Generic location error!");
		printStackTrace();
	}
	
	public InvalidLocationException(Location location) {
		if (location == null) {
			System.err.println("Invalid location: null");
		} else {
			System.err.println("Invalid location: " + location.getWorld().getName() + ", " + location.getX() + ", " + location.getBlockY() + ", " + location.getZ());
		}
		printStackTrace();
	}
	
	public InvalidLocationException(Location start, Location end) {
		new InvalidLocationException(start, end, "unspecified");
	}
	
	public InvalidLocationException(Location start, Location end, String reason) {
		if (start == null || end == null) {
			System.err.println("Invalid location bounds: null; reason:" + reason);
		} else {
			System.err.println("Invalid location: " + start.getWorld().getName() + ", " + start.getX() + ", " + start.getBlockY() + ", " + start.getZ() + " to "
					+ end.getWorld().getName() + ", " + end.getX() + ", " + end.getBlockY() + ", " + end.getZ() + "; reason:" + reason);
		}
		printStackTrace();
	}
}
