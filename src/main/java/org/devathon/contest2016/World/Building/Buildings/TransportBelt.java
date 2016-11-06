package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.RotationUtils;

public class TransportBelt extends BuildingType {

	public TransportBelt() {
		super(Material.COAL);
	}

	public boolean canPlace(Location loc) {
		return loc.getBlock().getType() == Material.GRASS;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void build(Player player, Location loc) {
		String direction = RotationUtils.getPlayerDirection(player);
		
		loc.getBlock().setType(Material.PISTON_BASE);
		loc.getBlock().setData(RotationUtils.directionToPistonRotation(direction));
	}

	@Override
	public ArrayList<Vector> getRelativePreviewVectors() {
		return new ArrayList<Vector>( Arrays.asList( new Vector(0, 0, 0) ));
	}

}
