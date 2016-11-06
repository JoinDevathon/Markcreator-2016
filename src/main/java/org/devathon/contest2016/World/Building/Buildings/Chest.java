package org.devathon.contest2016.World.Building.Buildings;

import java.util.ArrayList;
import java.util.Arrays;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.BlockUtils;

public class Chest extends BuildingType {

	@SuppressWarnings("deprecation")
	public Chest() {
		super(new MaterialData(Material.INK_SACK, (byte) 3));
	}

	public boolean canPlace(Location loc) {
		boolean chestAgainst = false;
		for(Block rel : BlockUtils.getSideBlocks(loc.getBlock().getRelative(0, 1, 0))) {
			if(rel.getType() == Material.CHEST) {
				chestAgainst = true;
			}
		}
		
		return loc.getBlock().getType() == Material.GRASS && loc.getBlockY() == 3 && !chestAgainst;
	}

	@Override
	public void build(Player player, Location loc) {
		loc.getBlock().getRelative(0, 1, 0).setType(Material.CHEST);
	}

	@Override
	public ArrayList<Vector> getRelativePreviewVectors() {
		return new ArrayList<Vector>( Arrays.asList( new Vector(0, 1, 0) ));
	}

	@Override
	public void remove(Building building) {
		building.getLocation().getBlock().getRelative(0, 1, 0).setType(Material.AIR);
	}

	public static Inventory getInventory(Building building) {
		org.bukkit.block.Chest chest = ((org.bukkit.block.Chest) building.getLocation().getBlock().getRelative(0, 1, 0).getState());
		
		return chest.getBlockInventory();
	}
}