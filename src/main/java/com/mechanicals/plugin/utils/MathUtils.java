package com.mechanicals.plugin.utils;

import java.util.Random;

public class MathUtils {

	public static int getNextMultipleOf(int starting, int multiple) {
		if (starting < multiple) return multiple;
		int remaining = starting % multiple;
		while (remaining != 0) {
			starting++;
			remaining = starting % multiple;
		}
		return starting;
	}
	
	public static int getRandom(int min, int max) {
		Random rand = new Random();
		return rand.nextInt((max - min) + 1) + min;
	}
}
