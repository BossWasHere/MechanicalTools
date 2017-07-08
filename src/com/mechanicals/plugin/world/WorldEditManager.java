package com.mechanicals.plugin.world;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.yaml.snakeyaml.error.YAMLException;

import com.mechanicals.plugin.MechMain;
import com.sk89q.worldedit.CuboidClipboard;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.LocalPlayer;
import com.sk89q.worldedit.LocalSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.data.DataException;
import com.sk89q.worldedit.schematic.SchematicFormat;

@SuppressWarnings("deprecation")
public class WorldEditManager {

	MechMain plugin;
	
	public WorldEditManager(MechMain plugin) {
		this.plugin = plugin;
	}
	
	public void saveSchematic(String filename, Player caller, Location start, Location end) throws InvalidLocationException, YAMLException {
		if (!start.getWorld().getName().equalsIgnoreCase(end.getWorld().getName())) throw new InvalidLocationException(start, end);
		
		try {
			new File(plugin.fileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation"))).mkdirs();
		} catch (Exception e) {
			throw new YAMLException("Invalid configuration for MechanicalTools>config.yml");
		}
		File file = new File(plugin.fileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation")), filename + ".schematic");
		int i = 1;
		while (file.exists()) {
			file = new File(plugin.fileUtils.replaceDirectoryKeys(plugin.config.getString("schematicLocation")), filename + "(" + i + ").schematic");
			i++;
			plugin.logger.warning("Schematic already exists under that name! Updating to " + file.getName());
			
		}
		
		WorldEditPlugin wep = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
		WorldEdit we = wep.getWorldEdit();
		LocalPlayer localPlayer = wep.wrapPlayer(caller);
		LocalSession localSession = we.getSession(localPlayer);
		EditSession editSession = localSession.createEditSession(localPlayer);
		CuboidSelection selection = new CuboidSelection(start.getWorld(), start, end);
		
		Vector min = selection.getNativeMinimumPoint();
		Vector max = selection.getNativeMaximumPoint();
		
		editSession.enableQueue();
		CuboidClipboard clip = new CuboidClipboard(max.subtract(min).add(new Vector(1, 1, 1)), min);
		clip.copy(editSession);
		
		try {
			SchematicFormat.MCEDIT.save(clip, file);
		} catch (DataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			editSession.flushQueue();
		}
		
	}
}
