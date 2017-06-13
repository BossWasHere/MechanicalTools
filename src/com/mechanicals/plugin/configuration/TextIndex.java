package com.mechanicals.plugin.configuration;

import org.bukkit.ChatColor;

public class TextIndex {

	public final String onlyPlayers;
	public final String noPermission;
	public final String playerNotFound;
	public final String recievedBlockFrom;
	public final String givenBlockTo;
	public final String recievedItemFrom;
	public final String givenItemTo;
	public final String noPermissionToBreak;
	public final String noPermissionToBreakOther;
	public final String breakSuccess;
	public final String notEnabled;
	public final String noPermissionUse;
	public final String noPermissionUseOther;
	
	public final String bedTeleport_Use;
	public final String songIssue;
	public final String noSongs;
	public final String teleportNow;
	public final String placeDyes;
	public final String addDyeFirst;
	
	public TextIndex(ConfigurationUnit config) {
		onlyPlayers = translateAlternateColorCodes(config.getString("onlyPlayers"));
		noPermission = translateAlternateColorCodes(config.getString("noPermission"));
		playerNotFound = translateAlternateColorCodes(config.getString("playerNotFound"));
		recievedBlockFrom = translateAlternateColorCodes(config.getString("recievedBlockFrom"));
		givenBlockTo = translateAlternateColorCodes(config.getString("givenBlockTo"));
		recievedItemFrom = translateAlternateColorCodes(config.getString("recievedItemFrom"));
		givenItemTo = translateAlternateColorCodes(config.getString("givenItemTo"));
		noPermissionToBreak = translateAlternateColorCodes(config.getString("noPermissionToBreak"));
		noPermissionToBreakOther = translateAlternateColorCodes(config.getString("noPermissionToBreakOther"));
		breakSuccess = translateAlternateColorCodes(config.getString("brokenBlock"));
		notEnabled = translateAlternateColorCodes(config.getString("notEnabled"));
		noPermissionUse = translateAlternateColorCodes(config.getString("noPermissionUse"));
		noPermissionUseOther = translateAlternateColorCodes(config.getString("noPermissionUseOther"));
		teleportNow = translateAlternateColorCodes(config.getString("teleporting"));
		
		bedTeleport_Use = translateAlternateColorCodes(config.getString("spawnPointTeleport_Use"));
		songIssue = translateAlternateColorCodes(config.getString("songIssue"));
		noSongs = translateAlternateColorCodes(config.getString("noSongs"));
		placeDyes = translateAlternateColorCodes(config.getString("placeDyeHere"));
		addDyeFirst = translateAlternateColorCodes(config.getString("addDyeFirst"));
	}
	
	/**
	 * Replaces values within the string with the appropriate formatting
	 * <blockquote><b>Examples:</b><br/>~darkpurple~  -->>  ChatColor.DARK_PURPLE<br/>~italic~  -->>  ChatColor.ITALIC</blockquote>
	 * @param raw the string to format
	 * @return the formatted raw string
	 * @since 1.2
	 * @author IballisticBoss
	 */
	public static String translateAlternateColorCodes(String raw) {
		raw = raw.replaceAll("(?i)~black~", ChatColor.BLACK + "");
		raw = raw.replaceAll("(?i)~darkblue~", ChatColor.DARK_BLUE + "");
		raw = raw.replaceAll("(?i)~darkgreen~", ChatColor.DARK_GREEN + "");
		raw = raw.replaceAll("(?i)~darkaqua~", ChatColor.DARK_AQUA + "");
		raw = raw.replaceAll("(?i)~darkred~", ChatColor.DARK_RED + "");
		raw = raw.replaceAll("(?i)~darkpurple~", ChatColor.DARK_PURPLE + "");
		raw = raw.replaceAll("(?i)~gold~", ChatColor.GOLD + "");
		raw = raw.replaceAll("(?i)~gray~", ChatColor.GRAY + "");
		raw = raw.replaceAll("(?i)~darkgray~", ChatColor.DARK_GRAY + "");
		raw = raw.replaceAll("(?i)~blue~", ChatColor.BLUE + "");
		raw = raw.replaceAll("(?i)~green~", ChatColor.GREEN + "");
		raw = raw.replaceAll("(?i)~aqua~", ChatColor.AQUA + "");
		raw = raw.replaceAll("(?i)~red~", ChatColor.RED + "");
		raw = raw.replaceAll("(?i)~lightpurple~", ChatColor.LIGHT_PURPLE + "");
		raw = raw.replaceAll("(?i)~yellow~", ChatColor.YELLOW + "");
		raw = raw.replaceAll("(?i)~white~", ChatColor.WHITE + "");
		
		raw = raw.replaceAll("(?i)~obfuscated~", ChatColor.MAGIC + "");
		raw = raw.replaceAll("(?i)~bold~", ChatColor.BOLD + "");
		raw = raw.replaceAll("(?i)~strikethrough~", ChatColor.STRIKETHROUGH + "");
		raw = raw.replaceAll("(?i)~underline~", ChatColor.UNDERLINE + "");
		raw = raw.replaceAll("(?i)~italic~", ChatColor.ITALIC + "");
		raw = raw.replaceAll("(?i)~reset~", ChatColor.RESET + "");
		return raw;
	}
	
	public String playerNotFound(String player) {
		return playerNotFound.replaceAll("(?i)&player&", player);
	}
	
	public String recievedBlockFrom(String player) {
		return recievedBlockFrom.replaceAll("(?i)&player&", player);
	}
	
	public String givenBlockTo(String player) {
		return givenBlockTo.replaceAll("(?i)&player&", player);
	}
	
	public String recievedItemFrom(String player) {
		return recievedItemFrom.replaceAll("(?i)&player&", player);
	}
	
	public String givenItemTo(String player) {
		return givenItemTo.replaceAll("(?i)&player&", player);
	}
}
