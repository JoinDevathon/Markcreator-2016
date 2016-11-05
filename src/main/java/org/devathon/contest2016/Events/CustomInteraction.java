package org.devathon.contest2016.Events;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.BlockUtils;
import org.devathon.contest2016.Utils.StandDisplay;
import org.devathon.contest2016.Utils.StringUtils;
import org.devathon.contest2016.World.Ores.Pile;
import org.devathon.contest2016.World.Ores.PileManager;

public class CustomInteraction implements Listener {

	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if(player.getGameMode() == GameMode.CREATIVE) {
			return;
		}
		
    	event.setCancelled(true);
    		
    	if(block.getType().name().contains("LOG")) {
    		int totalBroken = breakAndCountAllRelatives(block, "LOG");
    		
    		new StandDisplay(block.getLocation(), "+" + totalBroken + " wood");
		}
    	
    	if(PileManager.isOre(block.getType())) {
    		Pile pile = PileManager.createOrReturnPile(block);
    		
    		pile.dig();
    		new StandDisplay(block.getLocation().add(0, 2, 0), "+1 " + StringUtils.formatMaterialName(pile.getMaterial()));
    	}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Block block = event.getClickedBlock();
			
			if(PileManager.isOre(block.getType())) {
				Pile pile = PileManager.createOrReturnPile(block);
				
				new StandDisplay(block.getLocation().add(0, 1, 0), pile.getAmountLeft() + "", new Vector(), 20);
			}
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
