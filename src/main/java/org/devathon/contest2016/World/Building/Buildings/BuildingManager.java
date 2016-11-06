package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;

import org.bukkit.Location;

public class BuildingManager {

	private static ArrayList<Building> buildings = new ArrayList<Building>();
	
	public static ArrayList<Building> getAllBuildings() {
		return buildings;
	}
	
	public static void addBuilding(Building building) {
		buildings.add(building);
	}
	
	public static Building getBuilding(Location loc) {
		for(Building building : getAllBuildings()) {
			if(building.getLocation().toVector().equals(loc.toVector())) {
				return building;
			}
		}
		
		return null;
	}
	
}
