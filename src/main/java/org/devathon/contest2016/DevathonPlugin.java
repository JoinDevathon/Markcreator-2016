package org.devathon.contest2016;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DevathonPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
    	Bukkit.broadcastMessage("--------------------------------------");
        Bukkit.broadcastMessage("Hello world!");
        Bukkit.broadcastMessage("--------------------------------------");
    }

    @Override
    public void onDisable() {
        // put your disable code here
    }
}

