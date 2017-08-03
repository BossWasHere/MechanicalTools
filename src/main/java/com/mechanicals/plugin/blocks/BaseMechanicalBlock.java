package com.mechanicals.plugin.blocks;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mechanicals.plugin.IMechanicalBlock;
import com.mechanicals.plugin.MechMain;

/**
 * Mechanical Block Base Implementation of {@link IMechanicalBlock}
 * Pre-declared base methods included 
 * @author IballisticBoss
 * @since 1.6
 *
 */
public abstract class BaseMechanicalBlock implements IMechanicalBlock {
	
	public final MechMain plugin;
	
	public BaseMechanicalBlock() {
		this.plugin = MechMain.plugin;
	}
	
	@Override
	public void giveBlockItem(Player player) {
		player.getInventory().addItem(getBlockItem());
	}
	
	@Override
	public ItemStack getBlockItem() {
		ItemStack item = getMechBlock().getItemBase();
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(plugin.blockData.getString("block." + getMechBlock().getId() + ".name"));
		meta.setLore(plugin.blockData.getStringList("block." + getMechBlock().getId() + ".lore"));
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public boolean matchesMeta(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		return meta.getDisplayName().equals(plugin.blockData.getString("block." + getMechBlock().getId() + ".name")) && meta.getLore().equals(plugin.blockData.getStringList("block." + getMechBlock().getId() + ".lore"));
	}
	
	public String placePerm() { return getMechBlock().getPlacePermission(); }
	public String breakPerm() { return getMechBlock().getBreakPermission(); }
	public String breakOtherPerm() { return getMechBlock().getBreakOtherPermission(); }
	public String commandPerm() { return getMechBlock().getCommandPermission(); }
	public String commandOtherPerm() { return getMechBlock().getCommandOtherPermission(); }
	
	@Override
	public void updateRunnables() {
		plugin.reloadPlacedBlocks();
	}
}
