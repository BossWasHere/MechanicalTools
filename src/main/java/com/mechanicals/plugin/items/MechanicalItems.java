package com.mechanicals.plugin.items;

import com.mechanicals.plugin.MechMain;

public enum MechanicalItems {
	BED_TELEPORT("spawnPointTeleporter"),
	RADIO("radio"),
	ITOOL("iTool"),
	DYE_WAND("dyeWand"),
	FLAMETHROWER("flamethrower");

	private final String id;
	
	private MechanicalItems(String id) {
		this.id = id;
	}
	
	/**
	 * Gets the ID associated with the Mechanical Item Addition
	 * @return the ID of the item
	 * @since 2.0
	 * @see MechanicalItems
	 * @author IballisticBoss
	 */
	public String getId() { return id; }
	
	public String getUsePermission() { return MechMain.plugin.permissions.get(getId() + "_use"); }
	public String getCommandPermission() { return MechMain.plugin.permissions.get(getId() + "_command"); }
	public String getCommandOtherPermission() { return MechMain.plugin.permissions.get(getId() + "_commandOther"); }
}
