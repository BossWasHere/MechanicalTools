package com.mechanicals.plugin.utils;

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
}
