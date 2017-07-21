package com.mechanicals.plugin.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;

public class VersionMatcher {

	private static String version = null;
	private static String[] ver_split = null;
	
	public static void findVersion() {
		String setting = null;
		Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(Bukkit.getVersion());
		while(m.find()) {
			setting = m.group(1);
			break;
		}
		if (setting == null) {
			version = "1.11";
			splitVersion();
			return;
		}
		Matcher m1 = Pattern.compile("(\\d+(?:\\.\\d+))").matcher(setting + "");
		setting = null;
		while(m1.find()) {
			setting = m1.group(1);
		}
		if (setting == null) {
			version = "1.11";
			splitVersion();
			return;
		}
		version = setting;
		splitVersion();
	}
	
	private static void splitVersion() {
		if (version.contains(".")) {
			ver_split = version.split("\\.");
		} else {
			ver_split = new String[] { version };
		}
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
		if (ver_split == null) return false;
		boolean larger = false;
		if (ver.contains(".")) {
			String[] d = ver.split("\\.");
			for (int i = 0; i < ver_split.length && i < d.length; i++) {
				larger = Integer.parseInt(d[i]) >= Integer.parseInt(ver_split[i]);
			}
		}
		return larger;
	}
	
	public static boolean isAtOrHigherThan(final String first, final String checkHigher) {
		String[] Tver_split;
		if (first.contains(".")) {
			Tver_split = first.split("\\.");
		} else Tver_split = new String[] { first };
		boolean larger = false;
		if (checkHigher.contains(".")) {
			String[] d = checkHigher.split("\\.");
			for (int i = 0; i < Tver_split.length && i < d.length; i++) {
				larger = Integer.parseInt(d[i]) >= Integer.parseInt(Tver_split[i]);
			}
		}
		return larger;
	}
}
