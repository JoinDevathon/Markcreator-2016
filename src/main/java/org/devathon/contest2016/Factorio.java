package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.devathon.contest2016.Events.CustomInteraction;

public class Factorio extends JavaPlugin implements Listener {

	public static Factorio plugin;
	
	@Override
	public void onEnable() {
		plugin = this;
		
    	PluginManager pm = Bukkit.getPluginManager();
    	pm.registerEvents(new CustomInteraction(), this);
    	
    }

    @Override
	public void onDisable() {
        
	}
    
    public static Factorio getInstance() {
    	return plugin;
    }
}

