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

	public final MechMain plugin;
	
	public PlayerEvent(MechMain plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.HIGH)
	public void playerInteractEvent(PlayerInteractEvent event) {
		ItemStack item = event.getItem();
		if (item == null) return;
		if (!item.hasItemMeta()) return;
		if (!item.getItemMeta().hasDisplayName() || !item.getItemMeta().hasLore()) return;
		if (plugin.bedTeleporter.matchesMeta(item)) {
			plugin.bedTeleporter.itemUseEvent(event);
		} else if (plugin.radio.matchesMeta(item)) {
			plugin.radio.itemUseEvent(event);
		} else if (plugin.iTool.matchesMeta(item)) {
			plugin.iTool.itemUseEvent(event);
		} else if (plugin.iTool.matchesMeta(item)) {
			plugin.iTool.itemUseEvent(event);
		} else if (plugin.dyeWand.matchesMeta(item)) {
			plugin.dyeWand.itemUseEvent(event);
		} else if (plugin.flamethrower.matchesMeta(item)) {
			plugin.flamethrower.itemUseEvent(event);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void playerEnterBedEvent(PlayerBedEnterEvent event) {
		ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
		if (item == null) return;
		if (!item.hasItemMeta()) return;
		if (!item.getItemMeta().hasDisplayName() || !item.getItemMeta().hasLore()) return;
		if (plugin.dyeWand.matchesMeta(item)) {
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
				for (String key : plugin.placed.getKeys(false)) {
					if (MechanicalBlocks.ELEVATOR.getId().equals(plugin.placed.getString(key + ".id"))) {
						newKeys.add(key);
					}
				}
					
				for (String key : newKeys) {
					Location tp = null;
					if (WorldUtils.isSameLocation(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z")), below)) {
						List<Location> locs = WorldUtils.getBlocksUpY(below.getWorld(), below.getBlockX(), below.getBlockZ(), MechanicalBlocks.ELEVATOR.getItemBase().getType());
						for (String key2 : newKeys) {
							for (Location loc : locs) {
								Location o = new Location(Bukkit.getWorld(plugin.placed.getString(key2 + ".world")), plugin.placed.getInt(key2 + ".x"), plugin.placed.getInt(key2 + ".y"), plugin.placed.getInt(key2 + ".z"));
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
				for (String key : plugin.placed.getKeys(false)) {
					if (MechanicalBlocks.ELEVATOR.getId().equals(plugin.placed.getString(key + ".id"))) {
						newKeys.add(key);
					}
				}
				
				for (String key : newKeys) {
					Location tp = null;
					if (WorldUtils.isSameLocation(new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z")), below)) {
						for (Location loc : WorldUtils.getBlocksUpY(below.getWorld(), below.getBlockX(), below.getBlockZ(), MechanicalBlocks.ELEVATOR.getItemBase().getType())) {
							for (String key2 : newKeys) {
								Location o = new Location(Bukkit.getWorld(plugin.placed.getString(key2 + ".world")), plugin.placed.getInt(key2 + ".x"), plugin.placed.getInt(key2 + ".y"), plugin.placed.getInt(key2 + ".z"));
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
