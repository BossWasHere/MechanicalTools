package com.mechanicals.plugin.item;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.obj.IntegerInterchange;

public class ClickHoldData {

	final MechMain plugin;
	
	public ClickHoldData(MechMain plugin) {
		this.plugin = plugin;
	}
	
	private Map<String, IntegerInterchange> holdPlayers = new HashMap<>();
	private Map<String, Runnable> playerRunnable = new HashMap<>();
	
	public void addEntry(String playerName, int repeats) {
		holdPlayers.put(playerName, new IntegerInterchange(repeats, 0));
	}
	
	public void addEntry(String playerName, int repeats, Runnable maxTask) {
		holdPlayers.put(playerName, new IntegerInterchange(repeats, 0));
		playerRunnable.put(playerName, maxTask);
	}
	
	public int getRunRepeats(String player) {
		return holdPlayers.get(player) == null ? -1 : holdPlayers.get(player).getMax();
	}
	
	public int getCurrentRuns(String player) {
		return holdPlayers.get(player) == null ? -1 : holdPlayers.get(player).getCurrent();
	}
	
	public boolean addRun(String player) {
		if (holdPlayers.containsKey(player)) {
			IntegerInterchange i = holdPlayers.get(player);
			i.add(1);
			holdPlayers.put(player, i);
			return true;
		}
		return false;
	}
	
	public boolean isMax(String player) {
		if (holdPlayers.containsKey(player)) {
			return holdPlayers.get(player).isMax();
		}
		return false;
	}
	
	public synchronized boolean callMaxTask(String player) {
		if (holdPlayers.containsKey(player) && playerRunnable.containsKey(player)) {
			if (isMax(player)) {
				if (playerRunnable.get(player) == null) return false;
				Bukkit.getServer().getScheduler().runTask(plugin, playerRunnable.get(player));
				return true;
			}
		}
		return false;
	}
	
}
