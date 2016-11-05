package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.Events.CommandListener;
import org.devathon.contest2016.Events.CustomInteraction;
import org.devathon.contest2016.World.ChunkLoadManager;
import org.devathon.contest2016.World.FactorioWorldManager;

public class Factorio extends JavaPlugin implements Listener {

	private static Factorio plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
    	PluginManager pm = Bukkit.getPluginManager();
    	pm.registerEvents(new CustomInteraction(), this);
    	pm.registerEvents(new CommandListener(), this);
    	pm.registerEvents(new ChunkLoadManager(), this);
    	
    	FactorioWorldManager.createWorld("factorio");
    }

    @Override
	public void onDisable() {
        
	}
    
    public static Factorio getInstance() {
    	return plugin;
    }
}

