package com.mechanicals.plugin.server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.json.JSONObject;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.obj.DebugLogger;
import com.mechanicals.plugin.utils.FileUtils;
import com.mechanicals.plugin.web.LibraryFetcher;
import com.mechanicals.plugin.web.security.HashKeys;

public class MechanicalPluginManager {

	private final MechMain plugin;
	private final File resourceLocation;
	
	private final boolean tryNBAPI, tryWorldEdit, tryVault;
	private final boolean ignoreDownloadHashes;
	
	private JSONObject json = null;
	
	public MechanicalPluginManager(boolean tryNBAPI, boolean tryWorldEdit, boolean tryVault) {
		plugin = MechMain.plugin;
		this.resourceLocation = plugin.resourceLocation;
		this.tryNBAPI = tryNBAPI;
		this.tryWorldEdit = tryWorldEdit;
		this.tryVault = tryVault;
		ignoreDownloadHashes = plugin.config.getBoolean("ignoreDownloadHashes", false);
		if (ignoreDownloadHashes) {
			plugin.logger.warning("[Plugin Manager] {-=======================================================================-}");
			plugin.logger.warning("[Plugin Manager] Downloading plugins without checking hashes may cause risks to your server");
			plugin.logger.warning("[Plugin Manager] It is recommended that you disable ignoreDownloadHashes as soon as possible");
			plugin.logger.warning("[Plugin Manager] {-=======================================================================-}");
		}
		
	}
	
	public Runnable getPluginLoadTask() {
		return new Runnable() {
			
			@Override
			public void run() {
				fetchUpdates();
				if (tryNBAPI) enableNoteBlockAPI();
				if (tryWorldEdit) enableWorldEdit();
				if (tryVault) enableVault();
				
			}
		};
	}
	
	protected void fetchUpdates() {
		DebugLogger.info("[Plugin Manager] Fetching libraries.json...");
		try {
			File local = new File(resourceLocation, "libraries.json");
			LibraryFetcher.downloadFile(local, PluginLoadable.PLUGIN_MGR_JSON, true);
			DebugLogger.info("[Plugin Manager] Fetched libraries.json Successfully - Now loading...");
			json = new JSONObject(FileUtils.readFile(local));
			DebugLogger.info("[Plugin Manager] Loaded libraries.json Successfully!");
			
			JSONObject current = json.getJSONObject("current");
			String ver = current.getString("build");
			if (!VersionMatcher.isAtOrHigherThan(ver, plugin.getPluginVersion())) {
				plugin.logger.info("[Plugin Manager] There is an update for MechanicalTools: " + PluginLoadable.MECHANICALTOOLS_URL);
			}
			
		} catch (IOException e) {
			plugin.logger.severe("[Plugin Manager] Could not load libraries.json file! Some dependencies will not be enabled if not already manually downloaded!");
		}
		
	}
	
	protected boolean enableNoteBlockAPI() {
		boolean enabled = false;
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled(PluginLoadable.NOTEBLOCKAPI)) {
			try {
				if (!(new File(resourceLocation + File.separator + "dependencies").isDirectory())) {
					DebugLogger.info("[Plugin Manager] Creating plugin depencency folder...");
					new File(resourceLocation + File.separator + "dependencies").mkdirs();
				}
				if (!(new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("songLocation"))).isDirectory())) {
					DebugLogger.info("[Plugin Manager] Creating plugin song folder (NoteBlockAPI)...");
					new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("songLocation"))).mkdirs();
				}
				DebugLogger.info("[Plugin Manager] Trying to enable plugin: " + PluginLoadable.NOTEBLOCKAPI + "...");
				File resource = new File(resourceLocation + File.separator + "dependencies", PluginLoadable.NOTEBLOCKAPI_JAR);
				if (!resource.exists()) Files.copy(plugin.getResourceAsStream(PluginLoadable.NOTEBLOCKAPI_JAR), resource.getAbsoluteFile().toPath());
				Plugin p = Bukkit.getPluginManager().loadPlugin(resource);
				Bukkit.getPluginManager().enablePlugin(p);
				enabled = true;
			} catch (Exception e) {
				DebugLogger.severe("[Plugin Manager] Could not load active dependency - " + PluginLoadable.NOTEBLOCKAPI);
				DebugLogger.severe("[Plugin Manager] Functions requiring this will be disabled!");
			}
		}
		return enabled;
	}
	
	protected boolean enableWorldEdit() {
		boolean enabled = false;
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled(PluginLoadable.WORLDEDIT)) {
			if (json == null) return false;
			try {
				if (!(new File(resourceLocation + File.separator + "dependencies").isDirectory())) {
					DebugLogger.info("[Plugin Manager] Creating plugin depencency folder...");
					new File(resourceLocation + File.separator + "dependencies").mkdirs();
				}
				if (!(new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation"))).isDirectory())) {
					DebugLogger.info("[Plugin Manager] Creating plugin schematic folder (WorldEdit)...");
					new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation"))).mkdirs();
				}
				DebugLogger.info("[Plugin Manager] Trying to enable plugin: " + PluginLoadable.WORLDEDIT + "...");
				JSONObject data = json.getJSONObject("libraries");
				data = data.getJSONObject("worldedit");
				File resource = new File(resourceLocation + File.separator + "dependencies", PluginLoadable.WORLDEDIT_JAR + data.getString("version") + ".jar");
				
				if (!resource.exists()) {
					String hashA = data.getString("md5");
					String hashB = data.getString("sha-256");
					HashKeys hash = new HashKeys(hashA, hashB);
					if (!LibraryFetcher.getResource(resource, data.getString("downloadUrl"), hash, ignoreDownloadHashes)) {
						plugin.logger.warning("[Plugin Manager] Could not prepare plugin " + PluginLoadable.WORLDEDIT + ", requiring features disabled");
						return false;
					}
				} else {
					File[] files = FileUtils.getFiles(resourceLocation + File.separator + "dependencies");
					for (File file : files) {
						if (file.getName().toLowerCase().startsWith(PluginLoadable.WORLDEDIT_JAR.toLowerCase())) {
							String ver = file.getName().substring(PluginLoadable.WORLDEDIT_JAR.length(), file.getName().length() - 4);
							String newVer = data.getString("version");
							boolean state = VersionMatcher.isAtOrHigherThan(ver, newVer);
							if (state) {
								DebugLogger.info("[Plugin Manager] Trying to update " + PluginLoadable.WORLDEDIT + " from version " + ver + " to " + newVer);
								String hashA = data.getString("md5");
								String hashB = data.getString("sha-256");
								HashKeys hash = new HashKeys(hashA, hashB);
								if (!LibraryFetcher.getResource(resource, data.getString("downloadUrl"), hash, ignoreDownloadHashes)) {
									plugin.logger.warning("Could not update plugin " + PluginLoadable.WORLDEDIT + ", requiring features disabled");
									return false;
								}
							}
							break;
						}
					}
				}
				
				Plugin p = Bukkit.getPluginManager().loadPlugin(resource);
				Bukkit.getPluginManager().enablePlugin(p);
				enabled = true;
			} catch (Exception e) {
				DebugLogger.severe("[Plugin Manager] Could not load active dependency - " + PluginLoadable.WORLDEDIT);
				DebugLogger.severe("[Plugin Manager] Functions requiring this will be disabled!");
			}
		}
		return enabled;
	}
	
	protected boolean enableVault() {
		boolean enabled = false;
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled(PluginLoadable.ECONOMY_PROVIDER)) {
			if (json == null) return false;
			try {
				if (!(new File(resourceLocation + File.separator + "dependencies").isDirectory())) {
					DebugLogger.info("[Plugin Manager] Creating plugin depencency folder...");
					new File(resourceLocation + File.separator + "dependencies").mkdirs();
				}
				DebugLogger.info("[Plugin Manager] Trying to enable plugin: " + PluginLoadable.ECONOMY_PROVIDER + "...");
				JSONObject data = json.getJSONObject("libraries");
				data = data.getJSONObject("vault");
				File resource = new File(resourceLocation + File.separator + "dependencies", PluginLoadable.ECONOMY_PROVIDER_JAR + data.getString("version") + ".jar");
				
				if (!resource.exists()) {
					String hashA = data.getString("md5");
					String hashB = data.getString("sha-256");
					HashKeys hash = new HashKeys(hashA, hashB);
					if (!LibraryFetcher.getResource(resource, data.getString("downloadUrl"), hash, ignoreDownloadHashes)) {
						plugin.logger.warning("[Plugin Manager] Could not prepare plugin " + PluginLoadable.WORLDEDIT + ", requiring features disabled");
						return false;
					}
				} else {
					File[] files = FileUtils.getFiles(resourceLocation + File.separator + "dependencies");
					for (File file : files) {
						if (file.getName().toLowerCase().startsWith(PluginLoadable.ECONOMY_PROVIDER_JAR.toLowerCase())) {
							String ver = file.getName().substring(PluginLoadable.ECONOMY_PROVIDER_JAR.length(), file.getName().length() - 4);
							String newVer = data.getString("version");
							boolean state = VersionMatcher.isAtOrHigherThan(ver, newVer);
							if (state) {
								DebugLogger.info("[Plugin Manager] Trying to update " + PluginLoadable.ECONOMY_PROVIDER + " from version " + ver + " to " + newVer);
								String hashA = data.getString("md5");
								String hashB = data.getString("sha-256");
								HashKeys hash = new HashKeys(hashA, hashB);
								if (!LibraryFetcher.getResource(resource, data.getString("downloadUrl"), hash, ignoreDownloadHashes)) {
									plugin.logger.warning("Could not update plugin " + PluginLoadable.ECONOMY_PROVIDER + ", requiring features disabled");
									return false;
								}
							}
							break;
						}
					}
				}
				
				Plugin p = Bukkit.getPluginManager().loadPlugin(resource);
				Bukkit.getPluginManager().enablePlugin(p);
				enabled = true;
			} catch (Exception e) {
				DebugLogger.severe("[Plugin Manager] Could not load active dependency - " + PluginLoadable.WORLDEDIT);
				DebugLogger.severe("[Plugin Manager] Functions requiring this will be disabled!");
			}
		}
		return enabled;
	}
}

interface PluginLoadable {
	
	//Maybe change the save location?
	String PLUGIN_MGR_JSON = "https://www.dropbox.com/s/jov3oxk7f5qb4hy/libraries.json?dl=1";
	
	String NOTEBLOCKAPI = "NoteBlockAPI";
	String NOTEBLOCKAPI_JAR = "NoteBlockAPI-1.1.7.jar";
	
	String WORLDEDIT = "WorldEdit";
	String WORLDEDIT_JAR = "worldedit-bukkit-";
	
	String ECONOMY_PROVIDER = "Vault";
	String ECONOMY_PROVIDER_JAR = "Vault-";
	
	String MECHANICALTOOLS_URL = "<add_url>";
}
