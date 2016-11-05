package org.devathon.contest2016.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class StringUtils {

	public static String colorize(String input) {
		return input.replace("&", ChatColor.COLOR_CHAR + "");
	}
	
	public static String formatMaterialName(Material mat) {
		return mat.name().toLowerCase().replace("_", " ");
	}
}
