package com.mechanicals.plugin.task;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Dispenser;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.blocks.MechanicalBlocks;
import com.mechanicals.plugin.server.MechRunnable;

public class BlockPlaceTaskTimer extends MechRunnable {

	private Map<Location, Location> dispensable = new HashMap<>();
	public static boolean shouldReload = false;
	
	public BlockPlaceTaskTimer() {
		super();
		reload();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		if (shouldReload) reload();
		Iterator<Location> dispenser = dispensable.keySet().iterator();
		Iterator<Location> output = dispensable.values().iterator();
		
		while (dispenser.hasNext()) {
			Location d = dispenser.next();
			Location o = output.next();
			if (d.getBlock().getState() instanceof Dispenser && o.getBlock().getType() == Material.AIR) {
				Dispenser disp = (Dispenser) d.getBlock().getState();
				ItemStack[] inventory = disp.getInventory().getContents();
				int i = 0;
				for (ItemStack items : inventory) {
					if (items == null) {
						continue;
					}
					if (items.getType() == Material.AIR) {
						continue;
					}
					
					if (items.getType().isBlock()) {
						if (items.getAmount() == 1) {
							inventory[i] = new ItemStack(Material.AIR);
						} else {
							items.setAmount(items.getAmount() - 1);
							inventory[i] = items;
						}
						o.getBlock().setTypeIdAndData(items.getTypeId(), (byte)items.getDurability(), true);
						disp.getInventory().setContents(inventory);
						break;
					}
					i++;
				}
			}
		}
	}
	
	public void reload() {
		Set<String> keys = plugin.placed.getKeys(false);
		Set<String> newKeys = new HashSet<>();
		for (String key : keys) {
			if (MechanicalBlocks.BLOCK_PLACER.getId().equals(plugin.placed.getString(key + ".id"))) {
				newKeys.add(key);
			}
		}
		
		dispensable.clear();
		for (String key : newKeys) {
			Location a = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".x"), plugin.placed.getInt(key + ".y"), plugin.placed.getInt(key + ".z"));
			Location b = new Location(Bukkit.getWorld(plugin.placed.getString(key + ".world")), plugin.placed.getInt(key + ".targetX"), plugin.placed.getInt(key + ".targetY"), plugin.placed.getInt(key + ".targetZ"));
			dispensable.put(a, b);
		}
		
		shouldReload = false;
		
	}
	
}
