package org.devathon.contest2016.Utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {

	public static int getFirstEmptySlot(Inventory inv) {
		for(int x = 0; x < inv.getSize(); x++) {
			if(inv.getItem(x) == null) {
				return x;
			}
		}
		
		return -1;
	}
	
	public static void removeOneFromHand(Player player) {
		ItemStack mainItem = player.getInventory().getItemInMainHand();
		
		if(mainItem.getAmount() > 1) {
			mainItem.setAmount(mainItem.getAmount() - 1);
		} else {
			player.getInventory().setItemInMainHand(null);
		}
	}
}
