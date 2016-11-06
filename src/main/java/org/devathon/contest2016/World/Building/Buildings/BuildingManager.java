package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;

import org.bukkit.Material;

public class BuildingManager {

	private static ArrayList<BuildingType> buildingTypes = new ArrayList<BuildingType>();
	
	public BuildingManager() {
		buildingTypes.add(new TransportBelt());
	}
	
	public static ArrayList<BuildingType> getAllBuildingTypes() {
		return buildingTypes;
	}
	
	public static BuildingType getBuilding(Material mat) {
		for(BuildingType type : getAllBuildingTypes()) {
			if(type.getMaterial() == mat) {
				return type;
			}
		}
		
		return null;
	}
}
