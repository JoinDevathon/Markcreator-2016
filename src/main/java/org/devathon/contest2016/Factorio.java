package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.Events.CommandListener;
import org.devathon.contest2016.Events.CustomInteraction;
import org.devathon.contest2016.Events.MovementListener;
import org.devathon.contest2016.World.ChunkLoadManager;
import org.devathon.contest2016.World.FactorioWorldManager;
import org.devathon.contest2016.World.Scheduler;
import org.devathon.contest2016.World.Building.Buildings.Building;
import org.devathon.contest2016.World.Building.Buildings.BuildingManager;
import org.devathon.contest2016.World.Building.Buildings.BuildingTypeManager;
import org.devathon.contest2016.World.Ores.Pile;
import org.devathon.contest2016.World.Ores.PileManager;

public class Factorio extends JavaPlugin implements Listener {

	private static Factorio plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
    	PluginManager pm = Bukkit.getPluginManager();
    	pm.registerEvents(new CustomInteraction(), this);
    	pm.registerEvents(new CommandListener(), this);
    	pm.registerEvents(new ChunkLoadManager(), this);
    	pm.registerEvents(new MovementListener(), this);

    	new BuildingTypeManager();
    	new Scheduler();
    	
    	FactorioWorldManager.createWorld("factorio");
    	Bukkit.getWorld("factorio").setDifficulty(Difficulty.PEACEFUL);
    }

    @Override
	public void onDisable() {
        for(Pile pile : PileManager.getAllPiles()) {
        	if(pile.getAmountLeft() == 1) {
        		pile.getLocation().getBlock().setType(Material.AIR);
        	}
        }
        
        for(Building building : BuildingManager.getAllBuildings()) {
        	building.getBuildingType().removeBuilding(building);
        }
	}
    
    public static Factorio getInstance() {
    	return plugin;
    }
}

