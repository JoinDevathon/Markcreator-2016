package org.devathon.contest2016.Events;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.devathon.contest2016.Factorio;
import org.devathon.contest2016.World.FactorioWorldManager;
import org.devathon.contest2016.World.Building.Preview;
import org.devathon.contest2016.World.Building.Buildings.BuildingTypeManager;
import org.devathon.contest2016.World.Building.Buildings.BuildingType;

public class MovementListener implements Listener {

	private HashMap<Player, Location> lastPlayerValidLocation = new HashMap<Player, Location>();
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		
		if(!FactorioWorldManager.isInFactorioMode(player)) {
			return;
		}
		
		if(player.isOnGround() && !player.getLocation().getBlock().getRelative(0, -1, 0).getType().name().contains("WATER")) {
			lastPlayerValidLocation.put(player, player.getLocation().clone());
		}
		
		if(player.getLocation().getBlock().getType().name().contains("WATER")) {
			player.teleport(lastPlayerValidLocation.get(player));
		}
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Factorio.getInstance(), new Runnable() {
			public void run() {
				Preview.cleanIfNeccesary(player);
				
				BuildingType buildingType = BuildingTypeManager.getBuilding(player.getItemInHand().getData());
				if(buildingType != null) {
					new Preview(player, buildingType);
				}
			}
		}, 1);
	}
}
