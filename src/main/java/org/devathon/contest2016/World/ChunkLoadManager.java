package org.devathon.contest2016.World;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.devathon.contest2016.Factorio;
import org.devathon.contest2016.Utils.BlockUtils;
import org.devathon.contest2016.Utils.IntegerUtils;

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
				int spawnWater = IntegerUtils.getRandom(20);
				int spawnCoal = IntegerUtils.getRandom(30);
				int spawnIron = IntegerUtils.getRandom(30);
				int spawnStone = IntegerUtils.getRandom(30);
				
				if(spawnWater == 0) {
					BlockUtils.makePileFormation(chunk.getBlock(8, 3, 8).getLocation(), Material.STATIONARY_WATER);
					
				} else if(spawnCoal == 0) {
					BlockUtils.makePileFormation(chunk.getBlock(8, 3, 8).getLocation(), Material.COAL_ORE);
					
				} else if(spawnIron == 0) {
					BlockUtils.makePileFormation(chunk.getBlock(8, 3, 8).getLocation(), Material.IRON_ORE);
				
				} else if(spawnStone == 0) {
					BlockUtils.makePileFormation(chunk.getBlock(8, 3, 8).getLocation(), Material.STONE);
				}
			}
		}, 1);
	}
	
	//coal
	//iron
	//copper
	//stone
}
