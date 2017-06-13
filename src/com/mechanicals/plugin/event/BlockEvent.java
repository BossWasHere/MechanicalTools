package com.mechanicals.plugin.event;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.utils.EntityUtils;
import com.mechanicals.plugin.utils.WorldUtils;

/**
 * The main handler for all Block Events related to this plugin
 * @author IballisticBoss
 * @since 1.0
 *
 */
public class BlockEvent implements Listener {

	final MechMain plugin;
	
	public BlockEvent(MechMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void blockPlaceEvent(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.AIR) return;
		ItemStack blockPlaced = event.getItemInHand();
		if (!blockPlaced.hasItemMeta()) return;
		if (!blockPlaced.getItemMeta().hasLore() || !blockPlaced.getItemMeta().hasDisplayName()) return;
		if (plugin.blockPlacer.matchesMeta(blockPlaced)) {
			plugin.blockPlacer.blockPlaceEvent(event);
		} else if (plugin.blockBreaker.matchesMeta(blockPlaced))  {
			plugin.blockBreaker.blockPlaceEvent(event);
		} else if (plugin.treeCutter.matchesMeta(blockPlaced)) {
			plugin.treeCutter.blockPlaceEvent(event);
		} else if (plugin.entityTeleporter.matchesMeta(blockPlaced)) {
			plugin.entityTeleporter.blockPlaceEvent(event);
		} else if (plugin.itemTeleporter.matchesMeta(blockPlaced)) {
			plugin.itemTeleporter.blockPlaceEvent(event);
		} else if (plugin.grinder.matchesMeta(blockPlaced)) {
			plugin.grinder.blockPlaceEvent(event);
		} else if (plugin.largeTeleporter.matchesMeta(blockPlaced)) {
			plugin.largeTeleporter.blockPlaceEvent(event);
		} else if (plugin.chunkLoader.matchesMeta(blockPlaced)) {
			plugin.chunkLoader.blockPlaceEvent(event);
		} else if (plugin.elevator.matchesMeta(blockPlaced)) {
			plugin.elevator.blockPlaceEvent(event);
		} else if (plugin.animalGrowth.matchesMeta(blockPlaced)) {
			plugin.animalGrowth.blockPlaceEvent(event);
		} else if (plugin.plantFarmer.matchesMeta(blockPlaced)) {
			plugin.plantFarmer.blockPlaceEvent(event);
		} else if (plugin.bedTeleporter.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (plugin.radio.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (plugin.iTool.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (plugin.dyeWand.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (plugin.flamethrower.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		}
		//ADD EVENTS
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void blockBreakEvent(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.DISPENSER) {
			for (String key : plugin.placed.getKeys(false)) {
				
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.BLOCK_PLACER.getId())) {
					plugin.blockPlacer.blockBreakEvent(event);
				} else if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ENTITY_TELEPORTER.getId())) {
					plugin.entityTeleporter.blockBreakEvent(event);
				} else if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ITEM_TELEPORTER.getId())) {
					plugin.itemTeleporter.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.DROPPER) {
			for (String key : plugin.placed.getKeys(false)) {
				
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.BLOCK_BREAKER.getId())) {
					plugin.blockBreaker.blockBreakEvent(event);
				} else if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.TREE_CUTTER.getId())) {
					plugin.treeCutter.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.PRISMARINE) {
			for (String key : plugin.placed.getKeys(false)) {
				
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.GRINDER.getId())) {
					plugin.grinder.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.BEACON) {
			for (String key : plugin.placed.getKeys(false)) {
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.LARGE_TELEPORTER.getId())) {
					plugin.largeTeleporter.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.ENCHANTMENT_TABLE) {
			for (String key : plugin.placed.getKeys(false)) {
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.CHUNK_LOADER.getId())) {
					plugin.chunkLoader.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.WOOL) {
			for (String key : plugin.placed.getKeys(false)) {
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ELEVATOR.getId())) {
					plugin.elevator.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.HAY_BLOCK) {
			for (String key : plugin.placed.getKeys(false)) {
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ANIMAL_GROWTH.getId())) {
					plugin.animalGrowth.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.MOSSY_COBBLESTONE) {
			for (String key : plugin.placed.getKeys(false)) {
				if (plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.PLANT_FARMER.getId())) {
					plugin.plantFarmer.blockBreakEvent(event);
				}
				
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void redstonePowerEvent(BlockRedstoneEvent event) {
		if (!plugin.largeTeleporterEnabled) return;
		Location b = null, a = null;
		String useKey = "";
		Set<String> newKeys = new HashSet<>();
		for (String key : plugin.placed.getKeys(false)) {
			if (MechanicalBlocks.LARGE_TELEPORTER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		Set<Player> players = null;
		Set<Player> outPlayers = new HashSet<>();
		boolean sender = true;
		for (Location l : WorldUtils.getBlocksSurrounding(event.getBlock().getLocation())) {
			if (!l.getBlock().getType().equals(MechanicalBlocks.LARGE_TELEPORTER.getItemBase().getType())) continue;
			for (String key : newKeys) {
				a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
				b = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".destWorld")), plugin.placed.getInt(key + ".destX"), plugin.placed.getInt(key + ".destY"), plugin.placed.getInt(key + ".destZ"));
				if (!WorldUtils.isSameLocation(a, l)) {
					if (WorldUtils.isSameLocation(b, l)) {
						sender = false;
					} else continue;
				}
				useKey = key;
				players = sender ? EntityUtils.getPlayersAboveBlock(a) : EntityUtils.getPlayersAboveBlock(b);
				break;
			}
			if (a == null || b == null || players == null || useKey == "") return;
			if (!l.getBlock().isBlockIndirectlyPowered()) {
				if (players.isEmpty()) return;
				for (Player p : players) {
					if (!p.hasPermission(plugin.permissions.largeTeleporter_use)) {
						p.sendMessage(plugin.texts.noPermissionUse);
						continue;
					}
					if (!plugin.placed.getString(useKey + ".player").equalsIgnoreCase(p.getUniqueId().toString())) {
						if (!p.hasPermission(plugin.permissions.largeTeleporter_useOther)) {
							p.sendMessage(plugin.texts.noPermissionUseOther);
							continue;
						}
					}
					outPlayers.add(p);
				}
			}
			for (Player p : outPlayers) {
				double x = sender ? b.getBlockX() + 0.5 : a.getBlockX() + 0.5;
				double y = sender ? b.getBlockY() + 1 : a.getBlockY() + 1;
				double z = sender ? b.getBlockZ() + 0.5 : a.getBlockZ() + 0.5;
				Location out = new Location(sender ? b.getWorld() : a.getWorld(), x, y, z);
				p.teleport(out);
				p.sendMessage(plugin.texts.teleportNow);
			}
		}
	}
}
