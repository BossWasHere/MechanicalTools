package com.mechanicals.plugin.obj;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.mechanicals.plugin.MechMain;

public class DebugLogger {

	private static Logger logger = null;
	
	public static void init(boolean enabled) {
		if (enabled) logger = MechMain.plugin.logger;
	}
	
	public static void log(Level level, String msg) {
		if (logger != null) logger.log(level, msg);
	}
	
	public static void info(String msg) {
		if (logger != null) logger.info(msg);
	}
	
	public static void warning(String msg) {
		if (logger != null) logger.warning(msg);
	}
	
	public static void severe(String msg) {
		if (logger != null) logger.severe(msg);
	}
}
