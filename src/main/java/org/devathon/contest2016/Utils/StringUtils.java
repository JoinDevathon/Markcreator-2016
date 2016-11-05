package org.devathon.contest2016.Utils;

import org.bukkit.ChatColor;

public class StringUtils {

	public static String colorize(String input) {
		return input.replace("&", ChatColor.COLOR_CHAR + "");
	}
	
}
