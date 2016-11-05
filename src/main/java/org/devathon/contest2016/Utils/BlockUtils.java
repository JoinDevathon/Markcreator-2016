package org.devathon.contest2016.Utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.block.Block;

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
	
}
