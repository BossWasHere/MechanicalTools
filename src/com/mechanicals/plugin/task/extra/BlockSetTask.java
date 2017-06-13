package com.mechanicals.plugin.task.extra;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class BlockSetTask implements Runnable {

	Block location;
	Material type;
	byte data;
	
	public BlockSetTask(Block location, Material type, byte data) {
		this.location = location;
		this.type = type;
		this.data = data;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		location.setType(type, true);
		location.setData(data, true);
	}
	
	
}
