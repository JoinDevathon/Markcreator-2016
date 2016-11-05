package org.devathon.contest2016.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		
		if(event.getMessage().toLowerCase().startsWith("/moveworld")) {
			if(event.getMessage().split(" ").length == 2) {
				String targetWorldName = event.getMessage().split(" ")[1];
				
				if(Bukkit.getWorld(targetWorldName) != null) {
					World targetWorld = Bukkit.getWorld(targetWorldName);
					
					player.teleport(targetWorld.getSpawnLocation());
					player.sendMessage(ChatColor.GREEN + "Moved to world: " + targetWorldName);
					
				} else {
					player.sendMessage(ChatColor.RED + "World does not exist.");
				}
			} else {
				player.sendMessage(ChatColor.RED + "Usage: /moveworld <worldName>");
			}
			
			event.setCancelled(true);
		}
	}
	
}
