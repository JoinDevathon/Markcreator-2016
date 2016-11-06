package org.devathon.contest2016.World;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.devathon.contest2016.Factorio;
import org.devathon.contest2016.Utils.RotationUtils;
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
			}
			
		}, 20, 20);
	}
	
	@SuppressWarnings("deprecation")
	public void applyTickMechanics(Entity ent) {
		Block blockUnder = ent.getLocation().getBlock().getRelative(0, -1, 0);
		
		if(blockUnder.getType().name().contains("PISTON")) {
			ent.setVelocity(RotationUtils.pistonRotationToDirectionVector(blockUnder.getData()).multiply(0.25));			
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
}
