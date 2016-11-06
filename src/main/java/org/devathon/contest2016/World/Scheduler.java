package org.devathon.contest2016.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.devathon.contest2016.Factorio;
import org.devathon.contest2016.Utils.RotationUtils;
import org.devathon.contest2016.World.Building.Buildings.Building;
import org.devathon.contest2016.World.Building.Buildings.BuildingManager;
import org.devathon.contest2016.World.Building.Buildings.Drill;
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
		Block blockAbove = building.getLocation().getBlock().getRelative(0, 1, 0);
		
		if(building.getBuildingType() instanceof Drill) {
			//TODO drill
		}
	}
}
