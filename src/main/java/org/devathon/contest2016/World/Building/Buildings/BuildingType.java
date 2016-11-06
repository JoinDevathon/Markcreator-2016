package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.InventoryUtils;
import org.devathon.contest2016.World.Building.Preview;

public abstract class BuildingType {

	private MaterialData mat;
	
	public BuildingType(MaterialData mat) {
		this.mat = mat;
	}
	
	public MaterialData getMaterialData() {
		return mat;
	}

	public abstract ArrayList<Vector> getRelativePreviewVectors();
	
	public abstract boolean canPlace(Location loc);

	public void buildBuilding(Player player) {
		Preview preview = Preview.getPreview(player);

		if(preview != null && preview.isStillValid() && canPlace(preview.getTarget())) {
			build(player, preview.getTarget());
			InventoryUtils.removeOneFromHand(player);
			
		} else {
			player.sendMessage(ChatColor.RED + "Invalid placement.");
		}
		
		BuildingManager.addBuilding(new Building(this, preview.getTarget()));
	}
	
	public abstract void build(Player player, Location loc);
	
	public void removeBuilding(Building building) {
		remove(building);
		
		building.getLocation().getWorld().dropItem(building.getLocation().clone().add(0, 1, 0), mat.toItemStack(1));
	}
	
	public abstract void remove(Building building);
}
