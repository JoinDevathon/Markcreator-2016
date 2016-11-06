package org.devathon.contest2016.World.Ores;

import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.devathon.contest2016.Utils.LocationUtils;

public class PileManager {

	private static ArrayList<Pile> piles = new ArrayList<Pile>();
	
	public static void createPile(Location loc, Material mat, int amount) {
		Pile pile = new Pile(mat, loc, amount);
		loc.getBlock().setType(mat);
			
		piles.add(pile);
	}
	
	public static boolean isPile(Location loc) {
		for(Pile pile : piles) {
			if(pile.getLocation().toVector().equals(loc.toVector())) {
				return true;
			}
		}
		return false;
	}
	
	public static Pile getPile(Location loc) {
		for(Pile pile : piles) {
			if(pile.getLocation().toVector().equals(loc.toVector())) {
				return pile;
			}
		}
		return null;
	}
	
	public static ArrayList<Pile> getAllPiles() {
		return piles;
	}
	
	public static void removePile(Location loc) {
		piles.remove(getPile(loc));
		
		LocationUtils.resetBlock(loc);
	}
	
	public static boolean isOre(Material mat) {
		ArrayList<Material> ores = new ArrayList<Material>( Arrays.asList(Material.STONE, Material.COAL_ORE, Material.IRON_ORE) );
		
		return ores.contains(mat);
	}
	
	public static Pile createAndReturnPile(Block block, int amount) { //TODO FIX AMOUNT SYSTEM
		Location blockLoc = block.getLocation();
		
		if(!isPile(blockLoc)) {
			createPile(blockLoc, block.getType(), amount);
		}
		
		return getPile(blockLoc);
	}
}
