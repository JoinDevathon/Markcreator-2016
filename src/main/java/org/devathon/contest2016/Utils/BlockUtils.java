package org.devathon.contest2016.Utils;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.BlockIterator;

public class BlockUtils {

	public static List<Block> getRelativeBlocks(Block block) {
		List<Block> blocks = new ArrayList<Block>();
		
		for(int x = -1; x <= 1; x++) {
			for(int y = -1; y <= 1; y++) {
				for(int z = -1; z <= 1; z++) {
					if(!(x == 0 && y == 0 && z == 0)) {
						blocks.add(block.getRelative(x, y, z));
					}
				}
			}
		}
		
		return blocks;
	}
	
	public static List<Block> getSideBlocks(Block block) {
		List<Block> blocks = new ArrayList<Block>();
		
		BlockFace[] toAdd = { BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST };
		
		for(BlockFace face : toAdd) {
			blocks.add(block.getRelative(face));
		}
		
		return blocks;
	}
	
	public static void makePileFormation(Location center, Material mat) {		
		int minSize = 1;
		int maxSize = 8;
		
		Location corner1 = center.clone().add(IntegerUtils.getRandom(minSize, maxSize), 0, IntegerUtils.getRandom(minSize, maxSize));
		Location corner2 = center.clone().add(-IntegerUtils.getRandom(minSize, maxSize), 0, IntegerUtils.getRandom(minSize, maxSize));
		Location corner3 = center.clone().add(-IntegerUtils.getRandom(minSize, maxSize), 0, -IntegerUtils.getRandom(minSize, maxSize));
		Location corner4 = center.clone().add(IntegerUtils.getRandom(minSize, maxSize), 0, -IntegerUtils.getRandom(minSize, maxSize));
		
		drawLine(corner1, corner2, mat);
		drawLine(corner2, corner3, mat);
		drawLine(corner3, corner4, mat);
		drawLine(corner4, corner1, mat);

		fill(center, mat, mat.name().split("_").length > 1 ? mat.name().split("_")[1] : mat.name());
	}
	
	public static void drawLine(Location loc1, Location loc2, Material mat) {
		BlockIterator line = new BlockIterator(loc1.getWorld(), loc1.toVector(), loc2.toVector().subtract(loc1.toVector()).normalize(), 0, (int) loc1.distance(loc2));
		while(line.hasNext()) {
			Block block = line.next();
			
			block.setType(mat);
		}
	}
	
	public static void fill(Location loc, Material cornerMat, String typeKey) {		
		loc.getBlock().setType(cornerMat);
		
		for(Block relative : getSideBlocks(loc.getBlock())) {
			if(!relative.getType().name().contains(typeKey)) {
				fill(relative.getLocation(), cornerMat, typeKey);
			}
		}
	}
}
