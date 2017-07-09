package com.mechanicals.plugin.event;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.utils.WorldUtils;

public class PlayerEvent implements Listener {
	
	public PlayerEvent() {
		MechMain.plugin.getServer().getPluginManager().registerEvents(this, MechMain.plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void playerInteractEvent(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		if (item == null) return;
		if (!item.hasItemMeta()) return;
		if (!item.getItemMeta().hasDisplayName() || !item.getItemMeta().hasLore()) return;
		if (MechMain.plugin.bedTeleporter.matchesMeta(item)) {
			MechMain.plugin.bedTeleporter.itemUseEvent(event);
		} else if (MechMain.plugin.radio.matchesMeta(item)) {
			MechMain.plugin.radio.itemUseEvent(event);
		} else if (MechMain.plugin.iTool.matchesMeta(item)) {
			MechMain.plugin.iTool.itemUseEvent(event);
		} else if (MechMain.plugin.iTool.matchesMeta(item)) {
			MechMain.plugin.iTool.itemUseEvent(event);
		} else if (MechMain.plugin.dyeWand.matchesMeta(item)) {
			MechMain.plugin.dyeWand.itemUseEvent(event);
		} else if (MechMain.plugin.flamethrower.matchesMeta(item)) {
			MechMain.plugin.flamethrower.itemUseEvent(event);
		} else if (event.getClickedBlock().getType().equals(Material.FURNACE) || event.getClickedBlock().getType().equals(Material.BURNING_FURNACE)) {
			MechMain.plugin.generator.openFuelWindow(event.getPlayer(), event.getClickedBlock().getLocation());
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerEnterBedEvent(PlayerBedEnterEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		if (item == null) return;
		if (!item.hasItemMeta()) return;
		if (!item.getItemMeta().hasDisplayName() || !item.getItemMeta().hasLore()) return;
		if (MechMain.plugin.dyeWand.matchesMeta(item)) {
			Bukkit.getServer().getPluginManager().callEvent(new PlayerInteractEvent(event.getPlayer(), Action.RIGHT_CLICK_BLOCK, item, event.getBed(), BlockFace.UP));
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void playerMoveEvent(PlayerMoveEvent event) {
		Location start = event.getFrom();
		Location end = event.getTo();
		Vector change = end.toVector().subtract(start.toVector());
		if (change.getY() > 0.2) {
			Location below = new Location(start.getWorld(), start.getBlockX(), start.getBlockY() - 1, start.getBlockZ());
			if (below.getBlock().getType().equals(MechanicalBlocks.ELEVATOR.getItemBase().getType())) {
				Set<String> newKeys = new HashSet<>();
				for (String key : MechMain.plugin.placed.getKeys(false)) {
					if (MechanicalBlocks.ELEVATOR.getId().equals(MechMain.plugin.placed.getString(key + ".id"))) {
						newKeys.add(key);
					}
				}
					
				for (String key : newKeys) {
					Location tp = null;
					if (WorldUtils.isSameLocation(new Location(Bukkit.getWorld(MechMain.plugin.placed.getString(key + ".world")), MechMain.plugin.placed.getInt(key + ".x"), MechMain.plugin.placed.getInt(key + ".y"), MechMain.plugin.placed.getInt(key + ".z")), below)) {
						List<Location> locs = WorldUtils.getBlocksUpY(below.getWorld(), below.getBlockX(), below.getBlockZ(), MechanicalBlocks.ELEVATOR.getItemBase().getType());
						for (String key2 : newKeys) {
							for (Location loc : locs) {
								Location o = new Location(Bukkit.getWorld(MechMain.plugin.placed.getString(key2 + ".world")), MechMain.plugin.placed.getInt(key2 + ".x"), MechMain.plugin.placed.getInt(key2 + ".y"), MechMain.plugin.placed.getInt(key2 + ".z"));
								if (WorldUtils.isSameLocation(below, loc)) continue;
								if (WorldUtils.isSameLocation(o, loc) && loc.getBlockY() > below.getBlockY()) {
									if (tp == null) tp = loc;
									else if (WorldUtils.isYCloser(below, loc, tp)) tp = loc;
								}
							}
						}
					}
					if (tp != null) teleport(event.getPlayer(), tp);
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void playerSneakEvent(PlayerToggleSneakEvent event) {
		if (event.isSneaking()) {
			Location below = event.getPlayer().getLocation();
			below.setY(below.getBlockY() - 1);
			if (below.getBlock().getType().equals(MechanicalBlocks.ELEVATOR.getItemBase().getType())) {
				Set<String> newKeys = new HashSet<>();
				for (String key : MechMain.plugin.placed.getKeys(false)) {
					if (MechanicalBlocks.ELEVATOR.getId().equals(MechMain.plugin.placed.getString(key + ".id"))) {
						newKeys.add(key);
					}
				}
				
				for (String key : newKeys) {
					Location tp = null;
					if (WorldUtils.isSameLocation(new Location(Bukkit.getWorld(MechMain.plugin.placed.getString(key + ".world")), MechMain.plugin.placed.getInt(key + ".x"), MechMain.plugin.placed.getInt(key + ".y"), MechMain.plugin.placed.getInt(key + ".z")), below)) {
						for (Location loc : WorldUtils.getBlocksUpY(below.getWorld(), below.getBlockX(), below.getBlockZ(), MechanicalBlocks.ELEVATOR.getItemBase().getType())) {
							for (String key2 : newKeys) {
								Location o = new Location(Bukkit.getWorld(MechMain.plugin.placed.getString(key2 + ".world")), MechMain.plugin.placed.getInt(key2 + ".x"), MechMain.plugin.placed.getInt(key2 + ".y"), MechMain.plugin.placed.getInt(key2 + ".z"));
								if (WorldUtils.isSameLocation(below, loc)) continue;
								if (WorldUtils.isSameLocation(o, loc) && loc.getBlockY() < below.getBlockY()) {
									if (tp == null) tp = loc;
									else if (WorldUtils.isYCloser(below, loc, tp)) tp = loc;
								}
							}
						}
					}
					if (tp != null) teleport(event.getPlayer(), tp);
				}
			}
		}
	}
	
	public static void teleport(Player player, Location location) {
		location.setY(location.getBlockY() + 1);
		if (!location.getBlock().getType().equals(Material.AIR)) return;
		if (!location.getWorld().getBlockAt(location.getBlockX(), location.getBlockY() + 1, location.getBlockZ()).getType().equals(Material.AIR)) return;
		location.setZ(location.getBlockZ() + 0.5);
		location.setX(location.getBlockX() + 0.5);
		player.teleport(location);
		player.playSound(player.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1, 1);
	}
}
