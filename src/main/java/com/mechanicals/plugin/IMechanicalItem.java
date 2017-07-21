package com.mechanicals.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.items.MechanicalItems;

/**
 * Base Interface for all Mechanical Items
 * @author IballisticBoss
 *
 */
public interface IMechanicalItem {

	/**
	 * Gives an item to the specified player
	 * @param player the player to give the item to
	 * @since 2.0
	 */
	public void giveItem(Player player);
	
	/**
	 * Gets the item form of the current mechanical item
	 * @return The item associated with the current mechanical item
	 * @since 2.0
	 */
	public ItemStack getItem();
	
	/**
	 * Gets the appropriate MechanicalItems constant for this block
	 * @return A {@link MechanicalItems} enum constant for this block
	 * @since 2.0
	 */
	public MechanicalItems getMechItem();
	
	/**
	 * Mechanical Item Handler for the {@link PlayerInteractEvent}
	 * @param event the event called for when the item is right or left clicked
	 * @since 2.0
	 */
	public void itemUseEvent(PlayerInteractEvent event);
	
	/**
	 * Checks if a particular item matches the ItemMeta for this particular item
	 * @param item the {@link ItemStack} to check against
	 * @return <b>true</b> if the item has the same item meta as the Mechanical Item, else <b>false</b>
	 * @since 2.0
	 * @see IMechanicalBlock
	 */
	public boolean matchesMeta(ItemStack item);
	
}
