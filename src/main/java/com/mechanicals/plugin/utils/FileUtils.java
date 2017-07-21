package com.mechanicals.plugin.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.mechanicals.plugin.MechMain;

public class FileUtils {
	
	public static File[] getFiles(String directory) throws IOException {
		File f = new File(replaceDirectoryKeys(directory));
		File[] files = f.listFiles();
		if (files == null) return new File[] {};
		Set<File> output = new HashSet<>(); 
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) output.add(files[i]);
		}
		return output.toArray(new File[output.size()]);
	}
	
	public static File[] filterFilesWithExtension(File[] files, String extension) {
		if (files == null) return new File[] {};
		Set<File> output = new HashSet<>();
		for (File in : files) {
			if (in.isDirectory()) continue;
			String ext = "";
			
			int i = in.getName().lastIndexOf('.');
			if (i > 0) {
				ext = in.getName().substring(i+1);
			}
			if (extension.equalsIgnoreCase(ext)) {
				output.add(in);
			}
		}
		return output.toArray(new File[output.size()]);
	}
	
	public static String replaceDirectoryKeys(String formatted) {
		formatted = formatted.replaceAll("(?i)~pluginfolder~", MechMain.plugin.resourceLocation.getParentFile().getPath());
		return formatted;
	}
	
	public static String readFile(final File file) throws IOException {
		FileReader reader = new FileReader(file);
		BufferedReader buffer = new BufferedReader(reader);
		
		String output = "";
		String line;
		while ((line = buffer.readLine()) != null) {
			output += line + "\n";
		}
		
		buffer.close();
		
		return output;
	}
}
