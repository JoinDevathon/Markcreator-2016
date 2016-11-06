package org.devathon.contest2016.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.devathon.contest2016.Factorio;
import org.devathon.contest2016.Utils.InventoryUtils;
import org.devathon.contest2016.Utils.RotationUtils;
import org.devathon.contest2016.World.Building.Buildings.Building;
import org.devathon.contest2016.World.Building.Buildings.BuildingManager;
import org.devathon.contest2016.World.Building.Buildings.Chest;
import org.devathon.contest2016.World.Building.Buildings.Drill;
import org.devathon.contest2016.World.Building.Buildings.Inserter;
import org.devathon.contest2016.World.Ores.Pile;
import org.devathon.contest2016.World.Ores.PileManager;

public class Scheduler {

	public Scheduler() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Factorio.getInstance(), new Runnable() {
			public void run() {
				for(World world : FactorioWorldManager.getFactorioWorlds()) {
					for(Entity ent : world.getEntities()) {
						applyTickMechanics(ent);
					}
				}
				
				for(Pile pile : PileManager.getAllPiles()) {
					applyTickMechanics(pile);
				}
				
				for(Building building : BuildingManager.getAllBuildings()) {
					applyTickMechanics(building);
				}
			}
			
		}, 10, 10);
	}
	
	@SuppressWarnings("deprecation")
	public void applyTickMechanics(Entity ent) {
		Block blockUnder = ent.getLocation().getBlock().getRelative(0, -1, 0);
		
		if(blockUnder.getType().name().contains("PISTON")) {
			ent.teleport(ent.getLocation().add(RotationUtils.pistonRotationToDirectionVector(blockUnder.getData())));			
		}
	}
	
	@SuppressWarnings("deprecation")
	public void applyTickMechanics(Pile pile) {
		Block blockUnder = pile.getLocation().getBlock().getRelative(0, -1, 0);
		
		if(blockUnder.getType().name().contains("PISTON")) {
			Location newLoc = pile.getLocation().clone().add(RotationUtils.pistonRotationToDirectionVector(blockUnder.getData()));

			if(newLoc.getBlock().getType() == Material.AIR) {
				pile.move(newLoc);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void applyTickMechanics(Building building) {
		if(building.getBuildingType() instanceof Drill) {
			Block drillBlock = building.getLocation().getBlock().getRelative(0, 1, 0);
			Pile pile = PileManager.createAndReturnPile(building.getLocation().getBlock(), 3000);
			Block targetOutputBlock = drillBlock.getLocation().clone().add(RotationUtils.getStairOutputDirectionVector(drillBlock.getData())).getBlock();
			
			if(targetOutputBlock.getType() == Material.AIR) {
				pile.removeAmount(1);
				PileManager.createPile(targetOutputBlock.getLocation(), pile.getMaterial(), 1);
			}
		}
		
		if(building.getBuildingType() instanceof Inserter) {
			Block inserterBlock = building.getLocation().getBlock().getRelative(0, 1, 0);
			Block targetOutputBlock = inserterBlock.getLocation().clone().add(RotationUtils.getStairOutputDirectionVector(inserterBlock.getData())).getBlock();
			Block targetInputBlock = inserterBlock.getLocation().clone().add(RotationUtils.getStairInputDirectionVector(inserterBlock.getData())).getBlock();

			Pile inputPile = null;
			Inventory inputChestInv = null;
			Inventory outputChestInv = null;
			
			if(PileManager.isPile(targetInputBlock.getLocation())) {
				inputPile = PileManager.getPile(targetInputBlock.getLocation());
				
			} else if(BuildingManager.getBuilding(targetInputBlock.getLocation().clone().add(0, -1, 0)) != null) {
				Building inputBuilding = BuildingManager.getBuilding(targetInputBlock.getLocation().clone().add(0, -1, 0));
				
				if(inputBuilding.getBuildingType() instanceof Chest) {
					inputChestInv = Chest.getInventory(inputBuilding);
				}
			}
			
			if(BuildingManager.getBuilding(targetOutputBlock.getLocation().clone().add(0, -1, 0)) != null) {
				Building outputBuilding = BuildingManager.getBuilding(targetOutputBlock.getLocation().clone().add(0, -1, 0));
				
				if(outputBuilding.getBuildingType() instanceof Chest) {
					outputChestInv = Chest.getInventory(outputBuilding);
				}
			}
			
			if(inputPile != null || inputChestInv != null) { // Has input
				if((outputChestInv != null && InventoryUtils.getFirstEmptySlot(outputChestInv) != -1) || targetOutputBlock.getType() == Material.AIR) { // Can output
					//Remove 1 from input
					if(inputPile != null) {
						inputPile.removeAmount(1);
						if(inputPile.getAmountLeft() == 0) {
							PileManager.removePile(inputPile.getLocation());
						}
					}
					
					ItemStack stack = null;
					if(inputChestInv != null) {
						stack = InventoryUtils.removeFirstFromInv(inputChestInv);
					}
					
					if(outputChestInv != null) {
						if(stack != null) {
							outputChestInv.addItem(stack);
						} else {
							outputChestInv.addItem(new ItemStack(inputPile.getMaterial()));
						}
						
					} else {
						if(stack == null && inputPile != null) {
							if(targetOutputBlock.getType() == Material.AIR) {
								PileManager.createPile(targetOutputBlock.getLocation(), inputPile.getMaterial(), 1);
							}
							
						} else if(stack != null && inputPile == null) {
							if(targetOutputBlock.getType() == Material.AIR) {
								PileManager.createPile(targetOutputBlock.getLocation(), stack.getType(), 1);
							}
						}
					}
				}
			}
		}
	}
}
