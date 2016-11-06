package org.devathon.contest2016.World.Ores;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class Pile {
	
	private Material mat;
	private Location loc;
	private int amountLeft;
	
	public Pile(Material mat, Location loc, int amount) {
		this.mat = mat;
		this.loc = loc;
		amountLeft = amount;
	}
	
	public void move(Location newLoc) {
		loc.getBlock().setType(Material.AIR);
		newLoc.getBlock().setType(mat);
		
		loc = newLoc;
	}
	
	public void dig() {
		removeAmount(1);
		
		Item item = loc.getWorld().dropItem(loc.clone().add(0.5, 1, 0.5), new ItemStack(mat));
		item.setVelocity(new Vector());
		
		if(amountLeft == 0) {
			PileManager.removePile(loc);
		}
	}
	
	public Material getMaterial() {
		return mat;
	}
	
	public Location getLocation() {
		return loc;
	}
	
	public int getAmountLeft() {
		return amountLeft;
	}
	
	public void removeAmount(int amount) {
		amountLeft -= amount;
	}
}
