package com.mechanicals.plugin.item;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

/**
 * Method Utilities for decoding data from raw Strings
 * @author IballisticBoss
 * @since 1.2
 *
 */
public class ItemConfigurationEncoder {

	/**
	 * Decodes raw strings in format [char]=[id]; to Character and ItemStack Map
	 * @param decode the raw string
	 * @return the characters associated with the ItemStack Materials in a map
	 * @since 1.3
	 * @author IballisticBoss
	 * @see Items
	 */
	public static Map<Character, ItemStack> decodeStringItemParts(String decode) {
		Set<String> parts = new HashSet<>();
		String construct = "";
		for (char c : decode.toCharArray()) {
			if (c == ';') {
				parts.add(construct + "");
				construct = "";
			} else {
				construct += c;
			}
		}
		if (construct != "") {
			parts.add(construct);
		}
		
		Map<Character, ItemStack> output = new HashMap<>();
		
		for (String part : parts) {
			char c;
			if (part.length() > 3) {
				c = part.toCharArray()[0];
				if (part.toCharArray()[1] == '=') {
					part = part.substring(2);
					
					ItemStack item = tryAndGetItemFromString(part);
					if (item != null) {
						output.put(c, item);
					}
				}
			}
		}
		
		return output;
		
	}
	
	public static ItemStack tryAndGetItemFromString(String itemID) {
		try {
			return Items.getMaterial(itemID);
		} catch (Exception e) {
			return null;
		}
	}
	
}
