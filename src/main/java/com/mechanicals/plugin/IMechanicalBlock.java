package com.mechanicals.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.blocks.MechanicalBlocks;

/**
 * Base Interface for all Mechanical Blocks
 * @author IballisticBoss
 *
 */
public interface IMechanicalBlock {
	
	/**
	 * Gives a block to the specified player
	 * @param player the player to give the block to
	 * @since 1.1
	 */
	public void giveBlockItem(Player player);
	
	/**
	 * Gets the block item of the current mechanical block
	 * @return The block item associated with the current mechanical block
	 * @since 1.1
	 */
	public ItemStack getBlockItem();
	
	/**
	 * Mechanical Block Handler for the {@link BlockPlaceEvent}
	 * @param event the event called for when the block is placed
	 * @since 1.1
	 */
	public void blockPlaceEvent(BlockPlaceEvent event);
	
	/**
	 * Mechanical Block Handler for the {@link BlockBreakEvent}
	 * @param event the event called for when the block is placed
	 * @since 1.1
	 */
	public void blockBreakEvent(BlockBreakEvent event);
	
	/**
	 * Gets the appropriate MechanicalBlocks constant for this block
	 * @return A {@link MechanicalBlocks} enum constant for this block
	 * @since 1.1
	 */
	public MechanicalBlocks getMechBlock();
	
	/**
	 * Checks if a particular item matches the ItemMeta for this particular block
	 * @param item the {@link ItemStack} to check against
	 * @return <b>true</b> if the item has the same item meta as the Mechanical Block, else <b>false</b>
	 * @since 1.1
	 */
	public boolean matchesMeta(ItemStack item);
	
	/**
	 * A method which can be called to force an update to runnable classes
	 * @since 1.3
	 */
	public void updateRunnables();
}
