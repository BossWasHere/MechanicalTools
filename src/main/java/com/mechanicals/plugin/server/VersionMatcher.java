package com.mechanicals.plugin.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

public class VersionMatcher {

	private static String version = null;
	
	public static void findVersion() {
		String setting = null;
		Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(Bukkit.getVersion());
		while(m.find()) {
			setting = m.group(1);
			break;
		}
		if (setting == null) {
			version = "1.11";
			return;
		}
		Matcher m1 = Pattern.compile("(\\d+(?:\\.\\d+))").matcher(setting + "");
		setting = null;
		while(m1.find()) {
			setting = m1.group(1);
		}
		if (setting == null) {
			version = "1.11";
			return;
		}
		version = setting;
	}
	
	public static boolean loadedVersion() {
		return version != null;
	}
	
	public static String version() {
		return version;
	}
	
	public static boolean isVersion(String ver) {
		return version.toLowerCase().contains(ver.toLowerCase());
	}
	
	public static boolean isAtOrHigherThan(final String ver) {
		return isAtOrHigherThan(version, ver);
	}
	
	public static boolean isLowerThan(final String first, final String checkLower) {
		String[] Tver_split;
		if (first.contains(".")) {
			Tver_split = first.split("\\.");
		} else Tver_split = new String[] { first };
		if (checkLower.contains(".")) {
			String[] d = checkLower.split("\\.");
			for (int i = 0; i < Tver_split.length && i < d.length; i++) {
				int a = Integer.parseInt(d[i]);
				int b = Integer.parseInt(Tver_split[i]);
				if (a > b) return false;
				if (a < b) return true;
			}
		}
		return false;
	}
	
	public static boolean isAtOrHigherThan(final String first, final String checkHigher) {
		if (first.equalsIgnoreCase(checkHigher)) return true;
		String[] Tver_split;
		if (first.contains(".")) {
			Tver_split = first.split("\\.");
		} else Tver_split = new String[] { first };
		if (checkHigher.contains(".")) {
			String[] d = checkHigher.split("\\.");
			for (int i = 0; i < Tver_split.length && i < d.length; i++) {
				int a = Integer.parseInt(d[i]);
				int b = Integer.parseInt(Tver_split[i]);
				if (a > b) return true;
				if (a < b) return false;
			}
		}
		return false;
	}
}
