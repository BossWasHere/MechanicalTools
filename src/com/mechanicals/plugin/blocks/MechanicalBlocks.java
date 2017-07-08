package com.mechanicals.plugin.blocks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.mechanicals.plugin.MechMain;

/**
 * Enumeration of all registered MechanicalBlocks and their items
 *
 */
public enum MechanicalBlocks {

	BLOCK_PLACER("blockPlacer", new ItemStack(Material.DISPENSER)),
	BLOCK_BREAKER("blockBreaker", new ItemStack(Material.DROPPER)),
	TREE_CUTTER("treeCutter", new ItemStack(Material.DROPPER)),
	ENTITY_TELEPORTER("entityTeleporter", new ItemStack(Material.DISPENSER)),
	ITEM_TELEPORTER("itemTeleporter", new ItemStack(Material.DISPENSER)),
	GRINDER("grinder", new ItemStack(Material.PRISMARINE, 1, (short) 2)),
	LARGE_TELEPORTER("largeTeleporter", new ItemStack(Material.BEACON)),
	CHUNK_LOADER("chunkLoader", new ItemStack(Material.ENCHANTMENT_TABLE)), 
	ELEVATOR("elevator", new ItemStack(Material.WOOL)),
	ANIMAL_GROWTH("animalGrowth", new ItemStack(Material.HAY_BLOCK)),
	PLANT_FARMER("plantFarmer", new ItemStack(Material.MOSSY_COBBLESTONE)),
	GENERATOR("generator", new ItemStack(Material.FURNACE));
	
	private final String id;
	private final ItemStack item;
	
	private MechanicalBlocks(String id, ItemStack item) {
		this.id = id;
		this.item = item;
	}
	
	/**
	 * Gets the ItemStack object associated with the Mechanical Block Addition
	 * for use when giving the block as an item to the player
	 * @return the ItemStack object, associated with the block
	 * @since 1.1
	 * @see MechanicalBlocks
	 * @author IballisticBoss
	 */
	public ItemStack getItemBase() { return item; }
	
	/**
	 * Gets the ID associated with the Mechanical Block Addition
	 * @return the ID of the block
	 * @since 1.1
	 * @see MechanicalBlocks
	 * @author IballisticBoss
	 */
	public String getId() { return id; }
	
	public String getPlacePermission() { return MechMain.plugin.permissions.loaded.get(getId() + "_place"); }
	public String getBreakPermission() { return MechMain.plugin.permissions.loaded.get(getId() + "_break"); }
	public String getBreakOtherPermission() { return MechMain.plugin.permissions.loaded.get(getId() + "_breakOther"); }
	public String getCommandPermission() { return MechMain.plugin.permissions.loaded.get(getId() + "_command"); }
	public String getCommandOtherPermission() { return MechMain.plugin.permissions.loaded.get(getId() + "_commandOther"); }
}
