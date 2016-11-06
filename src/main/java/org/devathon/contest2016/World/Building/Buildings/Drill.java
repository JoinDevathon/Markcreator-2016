package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.RotationUtils;
import org.devathon.contest2016.World.Ores.PileManager;

public class Drill extends BuildingType {

	@SuppressWarnings("deprecation")
	public Drill() {
		super(new MaterialData(Material.INK_SACK, (byte) 1));
	}

	public boolean canPlace(Location loc) {
		return PileManager.isOre(loc.getBlock().getType()) && loc.getBlockY() == 3;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void build(Player player, Location loc) {
		String direction = RotationUtils.getPlayerDirection(player);
		
		loc.getBlock().getRelative(0, 1, 0).setType(Material.COBBLESTONE_STAIRS);
		loc.getBlock().getRelative(0, 1, 0).setData(RotationUtils.directionToStairRotation(direction));
	}

	@Override
	public ArrayList<Vector> getRelativePreviewVectors() {
		return new ArrayList<Vector>( Arrays.asList( new Vector(0, 1, 0) ));
	}

	@Override
	public void remove(Building building) {
		//TODO
	}

}