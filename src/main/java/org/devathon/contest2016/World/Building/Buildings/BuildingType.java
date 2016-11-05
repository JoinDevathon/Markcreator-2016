package org.devathon.contest2016.World.Building.Buildings;

import org.bukkit.Location;
import org.bukkit.Material;

public abstract class BuildingType {

	private Material mat;
	
	public BuildingType(Material mat) {
		this.mat = mat;
	}
	
	public Material getMaterial() {
		return mat;
	}

	public abstract boolean canPlace(Location loc);
	public abstract void preview(Location loc, String rotation);
	public abstract void build(Location loc, String rotation);
}
