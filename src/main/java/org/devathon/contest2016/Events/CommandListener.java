package org.devathon.contest2016.Events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.devathon.contest2016.Utils.IntegerUtils;
import org.devathon.contest2016.Utils.ItemStackBuilder;

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
		
		if(event.getMessage().toLowerCase().startsWith("/testfactorio")) {
			event.setCancelled(true);
			
			player.chat("/moveworld factorio");
			player.teleport(new Location(Bukkit.getWorld("factorio"), IntegerUtils.getRandom(1000), 5, IntegerUtils.getRandom(1000)));
			
			player.getInventory().clear();
			player.setGameMode(GameMode.SURVIVAL);
			
			player.getInventory().addItem(new ItemStackBuilder().setMaterial(Material.DIAMOND_PICKAXE).setAmount(1).build());
			player.getInventory().addItem(new ItemStackBuilder().setMaterial(Material.INK_SACK).setDurability(0).setAmount(64).build());
			player.getInventory().addItem(new ItemStackBuilder().setMaterial(Material.INK_SACK).setDurability(1).setAmount(64).build());
			player.getInventory().addItem(new ItemStackBuilder().setMaterial(Material.INK_SACK).setDurability(2).setAmount(64).build());
			player.getInventory().addItem(new ItemStackBuilder().setMaterial(Material.INK_SACK).setDurability(3).setAmount(64).build());

			player.sendMessage(ChatColor.GREEN + "Happy testing!");
		}
	}
	
}
