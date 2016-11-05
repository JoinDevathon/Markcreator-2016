package org.devathon.contest2016.World.Building.Buildings;

import org.bukkit.Location;
import org.bukkit.Material;

public class TransportBelt extends BuildingType {

	@SuppressWarnings("deprecation")
	public TransportBelt() {
		super(Material.getMaterial(33));
	}

	public boolean canPlace(Location loc) {
		return loc.getBlock().getType() == Material.GRASS;
	}

	@Override
	public void preview(Location loc, String rotation) {
		int color = canPlace(loc) ? 5 : 14;
		
		
	}

	@Override
	public void build(Location loc, String rotation) {
		
	}

}
