package org.devathon.contest2016.Utils;

import org.bukkit.entity.Player;

public class RotationUtils {

	public static String getPlayerDirection(Player player) {
		float y = player.getLocation().getYaw();
		if (y < 0) {
			y += 360;
		}
		y %= 360;
		float i = (float) ((y + 8) / 90);

		if (i == 0) {
			return "S";
		} else if (i > 0.5 && i <= 1.5) {
			return "W";
		} else if (i > 1.5 && i <= 2.5) {
			return "N";
		} else if (i > 2.5 && i <= 3.5) {
			return "E";
		} else {
			return "S";
		}
	}
	
}
