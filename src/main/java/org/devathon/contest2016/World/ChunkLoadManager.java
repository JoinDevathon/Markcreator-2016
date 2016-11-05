package org.devathon.contest2016.World;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.devathon.contest2016.Factorio;

public class ChunkLoadManager implements Listener {

	@EventHandler
	public void onChunkLoad(ChunkLoadEvent event) {
		Chunk chunk = event.getChunk();
		
		if(FactorioWorldManager.getFactorioWorlds().contains(chunk.getWorld())) {
			if(event.isNewChunk()) {
				populateChunk(chunk);
			}
		}
	}
	
	public void populateChunk(Chunk chunk) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Factorio.getInstance(), new Runnable() {
			public void run() {
				placeWater(chunk.getBlock(8, 3, 8).getLocation());
			}
		}, 1);
	}
	
	public void placeWater(Location loc) {
		loc.getBlock().setType(Material.STATIONARY_WATER);
	}
}
