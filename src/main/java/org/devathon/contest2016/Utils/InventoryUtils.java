package org.devathon.contest2016.Utils;

import org.bukkit.inventory.Inventory;

public class InventoryUtils {

	public static int getFirstEmptySlot(Inventory inv) {
		for(int x = 0; x < inv.getSize(); x++) {
			if(inv.getItem(x) == null) {
				return x;
			}
		}
		
		return -1;
	}
	
}
