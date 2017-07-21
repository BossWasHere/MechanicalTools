package com.mechanicals.plugin.obj;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.mechanicals.plugin.utils.EntityUtils;

/**
 * Object Type for holding information on facing sections based on coordinates
 * @author IballisticBoss
 *
 */
public class BlockFacingPartion {

	private final Location start;
	private final Location secondary;
	private final BlockFace face;
	
	/**
	 * Creates a {@link BlockFacingPartion} object with directional starting point from a location
	 * @param start the centre block location
	 * @param face the direction, NESW Up or Down
	 * @since 1.4
	 * @author IballisticBoss
	 */
	public BlockFacingPartion(Location start, BlockFace face) {
		this.start = start;
		secondary = null;
		this.face = face;
	}
	
	/**
	 * Creates a {@link BlockFacingPartion} object with directional starting point and secondary point from a location
	 * @param start the centre block location
	 * @param secondary secondary location for data storage and manipulation
	 * @param face the direction, NESW Up or Down
	 * @since 1.4
	 * @author IballisticBoss
	 */
	public BlockFacingPartion(Location start, Location secondary, BlockFace face) {
		this.start = start;
		this.secondary = secondary;
		this.face = face;
	}
	
	/**
	 * Gets the secondary location specified by the constructor
	 * @return The secondary location, or null if not specified
	 * @since 1.4
	 * @author IballisticBoss
	 */
	public Location getSecondaryLocation() {
		return secondary;
	}
	
	/**
	 * Gets all entities in a crescent-shaped partion, from a block facing in a specific direction
	 * @param radius the radius from the start location to the circumference of the semi-circle
	 * @param items if items should be included in the list of entities
	 * @return Entities found in the "D"-shaped area.
	 * @since 1.4
	 * @author IballisticBoss
	 */
	public Entity[] getEntitiesInPartion(int radius, boolean items) {
		Collection<Entity> entities = EntityUtils.getNearbyEntities(start, radius, false);
		Set<Entity> outEntities = new HashSet<>();
		switch (face) {
		case NORTH:
			for (Entity e : entities) {
				if (e.getLocation().getBlockZ() > start.getBlockZ()) {
					if (items) {
						outEntities.add(e);
					} else if (!e.getType().equals(EntityType.DROPPED_ITEM)) {
						outEntities.add(e);
					}
				}
			}
			break;
		case EAST:
			for (Entity e : entities) {
				if (e.getLocation().getBlockX() < start.getBlockX()) {
					if (items) {
						outEntities.add(e);
					} else if (!e.getType().equals(EntityType.DROPPED_ITEM)) {
						outEntities.add(e);
					}
				}
			}
			break;
		case SOUTH:
			for (Entity e : entities) {
				if (e.getLocation().getBlockZ() < start.getBlockZ()) {
					if (items) {
						outEntities.add(e);
					} else if (!e.getType().equals(EntityType.DROPPED_ITEM)) {
						outEntities.add(e);
					}
				}
			}
			break;
		case WEST:
			for (Entity e : entities) {
				if (e.getLocation().getBlockX() > start.getBlockX()) {
					if (items) {
						outEntities.add(e);
					} else if (!e.getType().equals(EntityType.DROPPED_ITEM)) {
						outEntities.add(e);
					}
				}
			}
			break;
		case UP:
			for (Entity e : entities) {
				if (e.getLocation().getBlockY() < start.getBlockY()) {
					if (items) {
						outEntities.add(e);
					} else if (!e.getType().equals(EntityType.DROPPED_ITEM)) {
						outEntities.add(e);
					}
				}
			}
			break;
		case DOWN:
			for (Entity e : entities) {
				if (e.getLocation().getBlockY() > start.getBlockY()) {
					if (items) {
						outEntities.add(e);
					} else if (!e.getType().equals(EntityType.DROPPED_ITEM)) {
						outEntities.add(e);
					}
				}
			}
			break;
		default:
			return entities.toArray(new Entity[entities.size()]);
		}
		return outEntities.toArray(new Entity[outEntities.size()]);
	}
	
	/**
	 * Gets BlockFace from name
	 * @param face the name of the BlockFace constant in the enum
	 * @return the enum constant related to the face specified, or BlockFace.UP if not recognised
	 */
	public static BlockFace getFaceFromName(String face) {
		try {
			return BlockFace.valueOf(face);
		} catch (Exception e) {
			return BlockFace.UP;
		}
	}
}
