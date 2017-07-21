package com.mechanicals.plugin.items;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.mechanicals.plugin.IMechanicalItem;
import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.item.ItemConfigurationEncoder;

/**
 * Mechanical Item Base Implementation of {@link IMechanicalItem}
 * Pre-declared base methods included 
 * @author IballisticBoss
 * @since 2.0
 *
 */
public abstract class BaseMechanicalItem implements IMechanicalItem {

	public final MechMain plugin;
	
	public BaseMechanicalItem() {
		this.plugin = MechMain.plugin;
	}
	
	@Override
	public void giveItem(Player player) {
		player.getInventory().addItem(getItem());
	}
	
	@Override
	public ItemStack getItem() {
		String mechID = getMechItem().getId();
		ItemStack item = ItemConfigurationEncoder.tryAndGetItemFromString(plugin.itemData.getString("item." + mechID + ".item"));
		if (item == null) item = new ItemStack(Material.STONE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(plugin.itemData.getString("item." + mechID + ".name"));
		meta.setLore(plugin.itemData.getStringList("item." + mechID + ".lore"));
		item.setItemMeta(meta);
		return item;
	}
	
	@Override
	public boolean matchesMeta(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		return meta.getDisplayName().equals(plugin.itemData.getString("item." + getMechItem().getId() + ".name")) && meta.getLore().equals(plugin.itemData.getStringList("item." + getMechItem().getId() + ".lore"));
	}
	
	public String usePerm() { return getMechItem().getUsePermission(); }
	public String commandPerm() { return getMechItem().getCommandPermission(); }
	public String commandOtherPerm() { return getMechItem().getCommandOtherPermission(); }
}
