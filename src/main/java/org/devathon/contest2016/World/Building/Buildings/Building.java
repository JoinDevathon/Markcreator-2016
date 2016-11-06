package org.devathon.contest2016.World.Building.Buildings;

import org.bukkit.Location;

public class Building {
	
	private BuildingType type;
	private Location loc;

	public Building(BuildingType type, Location loc) {
		this.type = type;
		this.loc = loc;
	}
	
	public BuildingType getBuildingType() {
		return type;
	}
	
	public Location getLocation() {
		return loc;
	}
}
