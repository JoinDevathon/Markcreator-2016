package org.devathon.contest2016.Events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.devathon.contest2016.Utils.BlockUtils;
import org.devathon.contest2016.Utils.StandDisplay;

public class CustomInteraction implements Listener {

	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
    	event.setCancelled(true);
    		
    	if(event.getBlock().getType().name().contains("LOG")) {
    		int totalBroken = breakAndCountAllRelatives(block, "LOG");
    		
    		new StandDisplay(block.getLocation(), "+" + totalBroken + " wood");
    	}
	}
	
	public int breakAndCountAllRelatives(Block block, String typeKey) {
		int count = 1;
		block.breakNaturally();
		
		for(Block relative : BlockUtils.getRelativeBlocks(block)) {
			if(relative.getType().name().contains(typeKey)) {
				count += breakAndCountAllRelatives(relative, typeKey);
			}
		}
		
		return count;
	}
}
