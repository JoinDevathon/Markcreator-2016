package org.devathon.contest2016.Events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.BlockUtils;
import org.devathon.contest2016.Utils.StandDisplay;
import org.devathon.contest2016.Utils.StringUtils;
import org.devathon.contest2016.World.FactorioWorldManager;
import org.devathon.contest2016.World.Building.Preview;
import org.devathon.contest2016.World.Ores.Pile;
import org.devathon.contest2016.World.Ores.PileManager;

public class CustomInteraction implements Listener {

	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if(!FactorioWorldManager.isInFactorioMode(player)) {
			return;
		}
		
    	event.setCancelled(true);
    	
    	if(block.getType().name().contains("LOG")) {
    		int totalBroken = BlockUtils.breakAndCountAllRelatives(block, "LOG");
    		
    		new StandDisplay(block.getLocation(), "+" + totalBroken + " wood");
		}
    	
    	if(PileManager.isOre(block.getType())) {
    		Pile pile = PileManager.createAndReturnPile(block, 3000);
    		
    		pile.dig();
    		new StandDisplay(block.getLocation().add(0, 2, 0), "+1 " + StringUtils.formatMaterialName(pile.getMaterial()));
    	}
	}
	
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock();
		
		if(!FactorioWorldManager.isInFactorioMode(player)) {
			return;
		}
		
		PileManager.createAndReturnPile(block, 1);
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();

		if(!FactorioWorldManager.isInFactorioMode(player)) {
			return;
		}

		if(event.getHand() == EquipmentSlot.HAND) {
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Block block = event.getClickedBlock();
				
				if(Preview.hasPreview(player)) {
					event.setCancelled(true);
					event.getClickedBlock().getRelative(event.getBlockFace()).setType(Material.AIR);
					
					Preview.getPreview(player).getBuildingType().buildBuilding(player);
					player.updateInventory();
					
				} else if(PileManager.isOre(block.getType())) {
					Pile pile = PileManager.createAndReturnPile(block, 3000);
					
					new StandDisplay(block.getLocation().add(0, 1, 0), pile.getAmountLeft() + "", new Vector(), 20);
				}
			}
		}
	}
}
