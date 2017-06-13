package com.mechanicals.plugin.sound;

import java.io.File;

import org.bukkit.entity.Player;

import com.mechanicals.plugin.MechMain;
import com.xxmicloxx.NoteBlockAPI.NBSDecoder;
import com.xxmicloxx.NoteBlockAPI.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.Song;
import com.xxmicloxx.NoteBlockAPI.SongPlayer;

public class NoteBlockHandler {
	
	final MechMain plugin;
	
	public NoteBlockHandler(MechMain plugin) {
		this.plugin = plugin;
	}
	
	public boolean playNBS(Player player, String song) {
		File[] files = plugin.radio.getNoteBlockSongs();
		if (files == null) return false;
		for (File f : files) {
			Song s = NBSDecoder.parse(f);
			if (s == null) continue;
			if (f.getName().toLowerCase().startsWith(song.toLowerCase())) {
				SongPlayer sp = new RadioSongPlayer(s);
				sp.addPlayer(player);
				sp.setAutoDestroy(true);
				sp.setPlaying(true);
				return true;
			}
		}
		return false;
	}
}
