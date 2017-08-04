package com.mechanicals.plugin.items;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mechanicals.plugin.item.Items;
import com.mechanicals.plugin.task.extra.ConfirmCooldown;
import com.mechanicals.plugin.utils.FileUtils;
import com.mechanicals.plugin.utils.MathUtils;
import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.Song;

public class Radio extends BaseMechanicalItem {

	public Radio() {
		super();
	}

	@Override
	public MechanicalItems getMechItem() {
		return MechanicalItems.RADIO;
	}

	@Override
	public void itemUseEvent(PlayerInteractEvent event) {
		if (!plugin.radioEnabled) {
			event.getPlayer().sendMessage(plugin.texts.notEnabled);
			event.setCancelled(true);
			return;
		}
		if (event.getPlayer().hasPermission(usePerm())) {
			for (ConfirmCooldown c : plugin.cooldowns) {
				if (c.checkID(event.getPlayer().getName(), 13)) {
					if (!c.isCooled()) {
						return;
					}
					plugin.cooldowns.remove(c);
				}
			}
			openNoteBlockWindow(event.getPlayer());
			ConfirmCooldown c = new ConfirmCooldown(event.getPlayer().getName(), "", 13, plugin.itemData.getInt("item.radio.cooldown", 5));
			c.runTaskTimerAsynchronously(plugin, 0, 20);
			plugin.cooldowns.add(c);
		} else {
			event.getPlayer().sendMessage(plugin.texts.noPermissionUse);
		}
	}
	
	public void openNoteBlockWindow(Player player) {
		File[] songs = getNoteBlockSongs();
		if (songs == null) {
			player.sendMessage(plugin.texts.noSongs);
			return;
		}
		int size = MathUtils.getNextMultipleOf(songs.length, 9);
		if (size > 54) size = 54;
		Inventory i = Bukkit.createInventory(null, size, ChatColor.BLUE + "[Mechanical] Radio");
		ItemStack stop = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta meta1 = stop.getItemMeta();
		meta1.setDisplayName("Stop Song");
		meta1.setLore(Arrays.asList("Click to stop"));
		stop.setItemMeta(meta1);
		i.addItem(stop);
		int slot = 1;
		for (File song : songs) {
			if (slot >= size) break;
			Song s = NBSDecoder.parse(song);
			if (s == null) continue;
			ItemStack item = Items.getRandomItem(Items.getDiscVariants());
			ItemMeta meta = item.getItemMeta();
			meta.setDisplayName(song.getName().substring(0, song.getName().length() - 4));
			meta.setLore(Arrays.asList("Click to play"));
			item.setItemMeta(meta);
			i.setItem(slot, item);
			slot++;
		}
		player.openInventory(i);
	}
	
	public File[] getNoteBlockSongs() {
		try {
			return FileUtils.filterFilesWithExtension(FileUtils.getFiles(plugin.config.getString("songLocation")), "nbs");
		} catch (IOException e) {
			return null;
		}
	}

}
