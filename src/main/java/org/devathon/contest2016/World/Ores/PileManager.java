package org.devathon.contest2016.World.Ores;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.util.Vector;

public class PileManager {

	private static HashMap<Vector, Pile> locationPile = new HashMap<Vector, Pile>();
	
	public static void createPile(Location loc, Material mat) {
		Pile pile = new Pile(mat, loc);
		
		locationPile.put(loc.toVector(), pile);
	}
	
	public static boolean isPile(Vector vec) {
		return locationPile.containsKey(vec);
	}
	
	public static Pile getPile(Vector vec) {
		return locationPile.get(vec);
	}
	
	public static boolean isOre(Material mat) {
		ArrayList<Material> ores = new ArrayList<Material>( Arrays.asList(Material.STONE, Material.COAL_ORE, Material.IRON_ORE) );
		
		return ores.contains(mat);
	}
	
	public static Pile createOrReturnPile(Block block) {
		Vector blockVec = block.getLocation().toVector();
		
		if(isOre(block.getType())) {
			if(!isPile(blockVec)) {
				createPile(block.getLocation(), block.getType());
			}
			
			return getPile(blockVec);
		}
		
		return null;
	}
}
