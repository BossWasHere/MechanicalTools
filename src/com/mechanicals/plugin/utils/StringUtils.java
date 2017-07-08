package com.mechanicals.plugin.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringUtils {

	public static int parseInt(String in) {
		int out = 0;
		String one = "";
		for (char i : in.toCharArray()) {
			try {
				one += Integer.parseInt(i + "");
			} catch (NumberFormatException e) {}
		}
		
		try {
			out = Integer.parseInt(one);
		} catch (NumberFormatException e) {}
		return out;
	}
	
	public static String[] putKeysWithValues(Map<String, String> map) {
		Iterator<String> i = map.keySet().iterator();
		Iterator<String> j = map.values().iterator();
		
		String[] output = new String[map.size()];
		int k = 0;
		while (i.hasNext() && j.hasNext()) {
			output[k] = i.next() + ": " + j.next();
			k++;
		}
		
		return output;
	}
	
	public static boolean isNumeric(String in) {
		in = in.replace("0", "");
		in = in.replace("1", "");
		in = in.replace("2", "");
		in = in.replace("3", "");
		in = in.replace("4", "");
		in = in.replace("5", "");
		in = in.replace("6", "");
		in = in.replace("7", "");
		in = in.replace("8", "");
		in = in.replace("9", "");
		return in.length() < 1;
	}
	
	public static int getNumber(String in) {
		try {
			return Integer.parseInt(in);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static boolean isStringList(String in) {
		if (in.startsWith("[") && in.endsWith("]")) {
			return in.substring(1, in.length() - 1).length() > 0;
		}
		return false;
	}
	
	public static List<String> getStringList(String in) {
		in = in.substring(1, in.length() - 1);
		List<String> first = new ArrayList<>();;
		boolean section = false;
		if (in.contains("\"")) {
			String build = "";
			for (char c : in.toCharArray()) {
				if (c == '"') {
					if (section) {
						if (build.length() > 0) first.add(build + "");
						build = "";
						section = false;
					} else {
						section = true;
					}
				} else {
					if (c == ',') {
						first.add(build + "");
						build = "";
						section = false;
						continue;
					}
					build += c;
				}
			}
			if (build.length() > 0) first.add(build);
		} else {
			String filtered = filterNumbers(in);
			if (filtered.length() > 0) {
				if (filtered.contains(",")) {
					for (String split : filtered.split(",")) {
						first.add(split);
					}
				} else {
					first.add(filtered);
				}
			}
		}
		List<String> output = new ArrayList<>();
		for (String element : first) {
			if (element.length() > 0) {
				output.add(element);
			}
		}
		return output;
	}
	
	public static String filterNumbers(String in) {
		String out = "";
		for (char c : in.toCharArray()) {
			if (Character.isDigit(c)) out += c;
			else if (c == ',') out += c;
		}
		return out;
	}
	
	public static int countOccurances(String template, String search) {
		if (template == null) return -1;
		int index = template.indexOf(search);
		int count = 0;
		while (index != -1) {
		    count++;
		    template = template.substring(index + 1);
		    index = template.indexOf(search);
		}
		return count;
	}
}
