package org.devathon.contest2016.Utils;

import java.util.Random;

public class IntegerUtils {
	
	public static int getRandom(int max) {
		return new Random().nextInt(max);
	}
	
	public static int getRandom(int min, int max) {
		return new Random().nextInt(max - min) + min;
	}
	
}
