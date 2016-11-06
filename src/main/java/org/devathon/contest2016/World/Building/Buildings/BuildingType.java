package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.InventoryUtils;
import org.devathon.contest2016.World.Building.Preview;

public abstract class BuildingType {

	private Material mat;
	
	public BuildingType(Material mat) {
		this.mat = mat;
	}
	
	public Material getMaterial() {
		return mat;
	}

	public abstract ArrayList<Vector> getRelativePreviewVectors();
	
	public void buildBuilding(Player player) {
		Preview preview = Preview.getPreview(player);

		preview.clean();
		if(preview != null && preview.isStillValid() && canPlace(preview.getTarget())) {
			build(player, preview.getTarget());
			InventoryUtils.removeOneFromHand(player);
			
			player.sendMessage(ChatColor.GREEN + "Built.");

		} else {
			player.sendMessage(ChatColor.RED + "Invalid placement.");
		}
	}
	
	public abstract boolean canPlace(Location loc);
	public abstract void build(Player player, Location loc);
}
