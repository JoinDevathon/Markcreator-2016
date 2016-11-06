package org.devathon.contest2016.Utils;

import java.util.Arrays;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class LocationUtils {

	public static Location getTargetBlock(Player player) {
		return player.getTargetBlock(new HashSet<Material>(Arrays.asList(Material.AIR)), 6).getLocation();
	}

	public static void resetBlock(Location loc) {
		if(loc.getBlockY() == 3) {
			loc.getBlock().setType(Material.GRASS);
		} else {
			loc.getBlock().setType(Material.AIR);
		}
	}
}
