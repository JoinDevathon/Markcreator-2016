package org.devathon.contest2016.Utils;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class RotationUtils {

	public static String getPlayerDirection(Player player) {
		float y = player.getLocation().getYaw();
		if(y < 0) {
			y += 360;
		}
		y %= 360;
		float i = (float) ((y + 8) / 90);

		if(i == 0) {
			return "S";
		} else if(i > 0.5 && i <= 1.5) {
			return "W";
		} else if(i > 1.5 && i <= 2.5) {
			return "N";
		} else if(i > 2.5 && i <= 3.5) {
			return "E";
		} else {
			return "S";
		}
	}
	
	public static byte directionToPistonRotation(String direction) {
		switch(direction) {
		case "S":
			return 2;
		case "N":
			return 3;
		case "E":
			return 4;
		case "W":
			return 5;
		}
		
		return 0;
	}
	
	public static Vector pistonRotationToDirectionVector(byte rotation) {
		switch(rotation) {
		case 2:
			return new Vector(0, 0, -1);
		case 3:
			return new Vector(0, 0, 1);
		case 4:
			return new Vector(-1, 0, 0);
		case 5:
			return new Vector(1, 0, 0);
		}
		
		return new Vector();
	}
	
	public static byte directionToStairRotation(String direction) {
		switch(direction) {
		case "S":
			return 3;
		case "N":
			return 2;
		case "E":
			return 1;
		case "W":
			return 0;
		}
		
		return 0;
	}
	
	public static Vector getStairOutputDirectionVector(byte rotation) {
		switch(rotation) {
		case 3:
			return new Vector(0, 0, -1);
		case 2:
			return new Vector(0, 0, 1);
		case 1:
			return new Vector(-1, 0, 0);
		case 0:
			return new Vector(1, 0, 0);
		}
		
		return new Vector();
	}
}
