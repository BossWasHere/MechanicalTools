package com.mechanicals.plugin.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.mechanicals.plugin.MechMain;
import com.mechanicals.plugin.obj.DebugLogger;
import com.mechanicals.plugin.web.security.HashKeys;

public class LibraryFetcher {

	public static boolean getResource(File output, String url, HashKeys compare, boolean ignoreDownloadHashes) {
		if (!downloadFile(output, url, false)) return false;

		HashKeys hashes = new HashKeys(output);
		String hashMD5 = hashes.getMD5Hash();
		String hashSHA256 = hashes.getSHA256Hash();

		boolean hashMarkup = true;
		
		if (!ignoreDownloadHashes) {
			if (hashMD5.equals(compare.getMD5Hash()))
				DebugLogger.info("Hashes match up (MD5): " + hashMD5);
			else {
				MechMain.plugin.logger.warning("Hashes don't match up! " + hashMD5 + ", expected " + compare.getMD5Hash());
				hashMarkup = false;
			}
			
			if (hashSHA256.equals(compare.getSHA256Hash()))
				DebugLogger.info("Hashes match up (SHA-256): " + hashSHA256);
			else {
				MechMain.plugin.logger.warning("Hashes don't match up! " + hashSHA256 + ", expected " + compare.getSHA256Hash());
				hashMarkup = false;
			}
		}
		
		return hashMarkup;
	}
	
	public static boolean downloadFile(File local, String url, boolean quiet) {
		try {
			URL web = new URL(url);
			ReadableByteChannel rbc = Channels.newChannel(web.openStream());
			if (!local.exists()) local.createNewFile();
			FileOutputStream fos = new FileOutputStream(local);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (IOException e) {
			DebugLogger.severe("Error downloading file from URL" + (quiet ? ": " + url : ""));
			return false;
		}
		DebugLogger.info("Successfully downloaded file" + (quiet ? ": " + url : ""));
		return true;
	}
}

