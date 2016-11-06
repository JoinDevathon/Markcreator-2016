package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;

import org.bukkit.material.MaterialData;

public class BuildingTypeManager {

	private static ArrayList<BuildingType> buildingTypes = new ArrayList<BuildingType>();
	
	public BuildingTypeManager() {
		buildingTypes.add(new TransportBelt());
		buildingTypes.add(new Drill());
	}
	
	public static ArrayList<BuildingType> getAllBuildingTypes() {
		return buildingTypes;
	}
	
	public static BuildingType getBuilding(MaterialData mat) {
		for(BuildingType type : getAllBuildingTypes()) {
			if(type.getMaterialData().equals(mat)) {
				return type;
			}
		}
		
		return null;
	}
}
