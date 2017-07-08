package com.mechanicals.plugin.server;

import java.io.File;
import java.nio.file.Files;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.utils.FileUtils;

public class MechanicalPluginManager {

	private final MechMain plugin;
	private final File resourceLocation;
	private final Logger logger;
	
	public MechanicalPluginManager() {
		plugin = MechMain.plugin;
		this.resourceLocation = plugin.resourceLocation;
		this.logger = plugin.logger;
	}
	
	public boolean enableNoteBlockAPI() {
		boolean enabled = false;
		if (Bukkit.getServer().getPluginManager().isPluginEnabled(PluginLoadable.NOTEBLOCKAPI)) {
			try {
				if (!(new File(resourceLocation + File.separator + "dependencies").isDirectory())) {
					new File(resourceLocation + File.separator + "dependencies").mkdirs();
				}
				if (!(new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("songLocation"))).isDirectory())) {
					new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("songLocation"))).mkdirs();
				}
				File resource = new File(resourceLocation + File.separator + "dependencies", PluginLoadable.NOTEBLOCKAPI_JAR);
				if (!resource.exists()) Files.copy(plugin.getResourceAsStream("lib/" + PluginLoadable.NOTEBLOCKAPI_JAR), resource.getAbsoluteFile().toPath());
				Plugin p = Bukkit.getPluginManager().loadPlugin(resource);
				Bukkit.getPluginManager().enablePlugin(p);
				enabled = true;
			} catch (Exception e) {
				logger.severe("Could not load active dependency - " + PluginLoadable.NOTEBLOCKAPI);
				logger.severe("Functions requiring this will be disabled!");
			}
		}
		return enabled;
	}
	
	public boolean enableWorldEdit() {
		boolean enabled = false;
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled(PluginLoadable.WORLDEDIT)) {
			try {
				if (!(new File(resourceLocation + File.separator + "dependencies").isDirectory())) {
					new File(resourceLocation + File.separator + "dependencies").mkdirs();
				}
				if (!(new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation"))).isDirectory())) {
					new File(FileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation"))).mkdirs();
				}
				File resource = new File(resourceLocation + File.separator + "dependencies", PluginLoadable.WORLDEDIT_JAR);
				if (!resource.exists()) Files.copy(plugin.getResourceAsStream("lib/" + PluginLoadable.WORLDEDIT_JAR), resource.getAbsoluteFile().toPath());
				Plugin p = Bukkit.getPluginManager().loadPlugin(resource);
				Bukkit.getPluginManager().enablePlugin(p);
				enabled = true;
			} catch (Exception e) {
				logger.severe("Could not load active dependency - " + PluginLoadable.WORLDEDIT);
				logger.severe("Functions requiring this will be disabled!");
			}
		}
		return enabled;
	}
	
	public boolean enableVault() {
		boolean enabled = false;
		if (!Bukkit.getServer().getPluginManager().isPluginEnabled(PluginLoadable.ECONOMY_PROVIDER)) {
			try {
				if (!(new File(resourceLocation + File.separator + "dependencies").isDirectory())) {
					new File(resourceLocation + File.separator + "dependencies").mkdirs();
				}
				File resource = new File(resourceLocation + File.separator + "dependencies", PluginLoadable.ECONOMY_PROVIDER_JAR);
				if (!resource.exists()) Files.copy(plugin.getResourceAsStream("lib/" + PluginLoadable.ECONOMY_PROVIDER_JAR), resource.getAbsoluteFile().toPath());
				Plugin p = Bukkit.getPluginManager().loadPlugin(resource);
				Bukkit.getPluginManager().enablePlugin(p);
				enabled = true;
			} catch (Exception e) {
				logger.severe("Could not load active dependency - " + PluginLoadable.ECONOMY_PROVIDER);
				logger.severe("Functions requiring this will be disabled!");
			}
		}
		return enabled;
	}
}

interface PluginLoadable {
	
	String NOTEBLOCKAPI = "NoteBlockAPI";
	String NOTEBLOCKAPI_JAR = "NoteBlockAPI-1.1.6.jar";
	
	String WORLDEDIT = "WorldEdit";
	String WORLDEDIT_JAR = "worldedit-bukkit-6.1.7.2.jar";
	
	String ECONOMY_PROVIDER = "Vault";
	String ECONOMY_PROVIDER_JAR = "Vault.jar";
}
