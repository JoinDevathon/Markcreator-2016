package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.RotationUtils;

public class Inserter extends BuildingType {

	@SuppressWarnings("deprecation")
	public Inserter() {
		super(new MaterialData(Material.INK_SACK, (byte) 2));
	}

	public boolean canPlace(Location loc) {
		return loc.getBlock().getType() == Material.GRASS && loc.getBlockY() == 3;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void build(Player player, Location loc) {
		String direction = RotationUtils.getPlayerDirection(player);
		
		loc.getBlock().getRelative(0, 1, 0).setType(Material.WOOD_STAIRS);
		loc.getBlock().getRelative(0, 1, 0).setData(RotationUtils.directionToStairRotation(direction));
	}

	@Override
	public ArrayList<Vector> getRelativePreviewVectors() {
		return new ArrayList<Vector>( Arrays.asList( new Vector(0, 1, 0) ));
	}

	@Override
	public void remove(Building building) {
		building.getLocation().getBlock().getRelative(0, 1, 0).setType(Material.AIR);
	}

}
