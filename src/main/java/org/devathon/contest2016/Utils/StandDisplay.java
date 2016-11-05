package org.devathon.contest2016.Utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Factorio;

public class StandDisplay {
	
	private ArmorStand stand;
	
	private Vector animationVelocity = new Vector(0, 0.075, 0);
	private double lifetime = 30;
	
	public StandDisplay(Location loc, String text) {
		stand = (ArmorStand) loc.getWorld().spawnEntity(loc.add(0.5, -1.5, 0.5), EntityType.ARMOR_STAND);
		stand.setVisible(false);
		stand.setBasePlate(false);
		stand.setCollidable(false);
		stand.setCanPickupItems(false);
		stand.setGravity(false);
		stand.setInvulnerable(true);
		
		stand.setCustomName(text);
		stand.setCustomNameVisible(true);
		
		tick();
	}
	
	public void tick() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(Factorio.getInstance(), new Runnable() {
			public void run() {
				if(lifetime > 0) {
					lifetime--;
					
					animate();
					tick();
					
				} else {
					destroy();
				}
			}
		}, 1);
	}
	
	public void animate() {
		if(!stand.isDead()) {
			stand.teleport(stand.getLocation().add(animationVelocity));
		}
	}
	
	public void destroy() {
		stand.remove();
	}
}
