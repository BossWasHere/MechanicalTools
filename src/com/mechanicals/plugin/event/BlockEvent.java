package com.mechanicals.plugin.event;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockRedstoneEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.utils.EntityUtils;
import com.mechanicals.plugin.utils.WorldUtils;

/**
 * The main handler for all Block Events related to this MechMain.plugin
 * @author IballisticBoss
 * @since 1.0
 *
 */
public class BlockEvent implements Listener {
	
	public BlockEvent() {
		MechMain.plugin.getServer().getPluginManager().registerEvents(this, MechMain.plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void blockPlaceEvent(BlockPlaceEvent event) {
		if (event.getBlock().getType() == Material.AIR) return;
		ItemStack blockPlaced = event.getItemInHand();
		if (!blockPlaced.hasItemMeta()) return;
		if (!blockPlaced.getItemMeta().hasLore() || !blockPlaced.getItemMeta().hasDisplayName()) return;
		if (MechMain.plugin.blockPlacer.matchesMeta(blockPlaced)) {
			MechMain.plugin.blockPlacer.blockPlaceEvent(event);
		} else if (MechMain.plugin.blockBreaker.matchesMeta(blockPlaced))  {
			MechMain.plugin.blockBreaker.blockPlaceEvent(event);
		} else if (MechMain.plugin.treeCutter.matchesMeta(blockPlaced)) {
			MechMain.plugin.treeCutter.blockPlaceEvent(event);
		} else if (MechMain.plugin.entityTeleporter.matchesMeta(blockPlaced)) {
			MechMain.plugin.entityTeleporter.blockPlaceEvent(event);
		} else if (MechMain.plugin.itemTeleporter.matchesMeta(blockPlaced)) {
			MechMain.plugin.itemTeleporter.blockPlaceEvent(event);
		} else if (MechMain.plugin.grinder.matchesMeta(blockPlaced)) {
			MechMain.plugin.grinder.blockPlaceEvent(event);
		} else if (MechMain.plugin.largeTeleporter.matchesMeta(blockPlaced)) {
			MechMain.plugin.largeTeleporter.blockPlaceEvent(event);
		} else if (MechMain.plugin.chunkLoader.matchesMeta(blockPlaced)) {
			MechMain.plugin.chunkLoader.blockPlaceEvent(event);
		} else if (MechMain.plugin.elevator.matchesMeta(blockPlaced)) {
			MechMain.plugin.elevator.blockPlaceEvent(event);
		} else if (MechMain.plugin.animalGrowth.matchesMeta(blockPlaced)) {
			MechMain.plugin.animalGrowth.blockPlaceEvent(event);
		} else if (MechMain.plugin.plantFarmer.matchesMeta(blockPlaced)) {
			MechMain.plugin.plantFarmer.blockPlaceEvent(event);
		} else if (MechMain.plugin.generator.matchesMeta(blockPlaced)) {
			MechMain.plugin.generator.blockPlaceEvent(event);
		} else if (MechMain.plugin.bedTeleporter.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (MechMain.plugin.radio.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (MechMain.plugin.iTool.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (MechMain.plugin.dyeWand.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		} else if (MechMain.plugin.flamethrower.matchesMeta(blockPlaced)) {
			event.setCancelled(true);
		}
		//ADD EVENTS
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void blockBreakEvent(BlockBreakEvent event) {
		if (event.getBlock().getType() == Material.DISPENSER) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.BLOCK_PLACER.getId())) {
					MechMain.plugin.blockPlacer.blockBreakEvent(event);
				} else if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ENTITY_TELEPORTER.getId())) {
					MechMain.plugin.entityTeleporter.blockBreakEvent(event);
				} else if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ITEM_TELEPORTER.getId())) {
					MechMain.plugin.itemTeleporter.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.DROPPER) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.BLOCK_BREAKER.getId())) {
					MechMain.plugin.blockBreaker.blockBreakEvent(event);
				} else if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.TREE_CUTTER.getId())) {
					MechMain.plugin.treeCutter.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.PRISMARINE) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.GRINDER.getId())) {
					MechMain.plugin.grinder.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.BEACON) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.LARGE_TELEPORTER.getId())) {
					MechMain.plugin.largeTeleporter.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.ENCHANTMENT_TABLE) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.CHUNK_LOADER.getId())) {
					MechMain.plugin.chunkLoader.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.WOOL) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ELEVATOR.getId())) {
					MechMain.plugin.elevator.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.HAY_BLOCK) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.ANIMAL_GROWTH.getId())) {
					MechMain.plugin.animalGrowth.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.MOSSY_COBBLESTONE) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.PLANT_FARMER.getId())) {
					MechMain.plugin.plantFarmer.blockBreakEvent(event);
				}
				
			}
		} else if (event.getBlock().getType() == Material.FURNACE || event.getBlock().getType() == Material.BURNING_FURNACE) {
			for (String key : MechMain.plugin.placed.getKeys(false)) {
				if (MechMain.plugin.placed.getString(key + ".id", "").equals(MechanicalBlocks.GENERATOR.getId())) {
					MechMain.plugin.generator.blockBreakEvent(event);
				}
				
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void redstonePowerEvent(BlockRedstoneEvent event) {
		if (!MechMain.plugin.largeTeleporterEnabled) return;
		Location b = null, a = null;
		String useKey = "";
		Set<String> newKeys = new HashSet<>();
		for (String key : MechMain.plugin.placed.getKeys(false)) {
			if (MechanicalBlocks.LARGE_TELEPORTER.getId().equals(MechMain.plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		Set<Player> players = null;
		Set<Player> outPlayers = new HashSet<>();
		boolean sender = true;
		for (Location l : WorldUtils.getBlocksSurrounding(event.getBlock().getLocation())) {
			if (!l.getBlock().getType().equals(MechanicalBlocks.LARGE_TELEPORTER.getItemBase().getType())) continue;
			for (String key : newKeys) {
				a = new Location(Bukkit.getWorld(MechMain.plugin.placed.getString(key + ".world")), MechMain.plugin.placed.getInt(key + ".x"), MechMain.plugin.placed.getInt(key + ".y"), MechMain.plugin.placed.getInt(key + ".z"));
				b = new Location(Bukkit.getWorld(MechMain.plugin.placed.getString(key + ".destWorld")), MechMain.plugin.placed.getInt(key + ".destX"), MechMain.plugin.placed.getInt(key + ".destY"), MechMain.plugin.placed.getInt(key + ".destZ"));
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
					if (!p.hasPermission(MechMain.plugin.largeTeleporter.getUsePerm())) {
						p.sendMessage(MechMain.plugin.texts.noPermissionUse);
						continue;
					}
					if (!MechMain.plugin.placed.getString(useKey + ".player").equalsIgnoreCase(p.getUniqueId().toString())) {
						if (!p.hasPermission(MechMain.plugin.largeTeleporter.getUseOtherPerm())) {
							p.sendMessage(MechMain.plugin.texts.noPermissionUseOther);
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
				p.sendMessage(MechMain.plugin.texts.teleportNow);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void hopperFeedEvent(InventoryMoveItemEvent event) {
		if (event.getSource().getHolder() instanceof Hopper) {
			if (event.getDestination().getHolder() instanceof Furnace) {
				//Stuff
			}
		}
	}
}
