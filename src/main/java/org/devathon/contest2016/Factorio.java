package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.Utils.StandDisplay;

public class Factorio extends JavaPlugin implements Listener {

	public static Factorio plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
    	PluginManager pm = Bukkit.getPluginManager();
    	pm.registerEvents(this, this);
    	
    }

    @Override
	public void onDisable() {
        
	}
    
    @EventHandler
    public void onPlayerJoin(BlockBreakEvent event) {
    	event.setCancelled(true);
    		
    	new StandDisplay(event.getBlock().getLocation().add(0, 1, 0), event.getBlock().getType().name().toLowerCase().replace("_", " "));
	}
    
    public static Factorio getInstance() {
    	return plugin;
    }
}

