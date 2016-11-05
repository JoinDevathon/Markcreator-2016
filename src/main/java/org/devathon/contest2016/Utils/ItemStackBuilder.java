package org.devathon.contest2016.Utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemStackBuilder {

	private ItemStack stack;
	
	public ItemStackBuilder() {
		stack = new ItemStack(Material.AIR);
	}
	
	public ItemStackBuilder(ItemStack currentStack) {
		stack = currentStack;
	}

	public ItemStackBuilder setMaterial(Material mat) {
		stack.setType(mat);
		
		return this;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStackBuilder setId(int id) {
		stack.setTypeId(id);
		
		return this;
	}
	
	public ItemStackBuilder setDurability(int data) {
		stack.setDurability((short) data);
		
		return this;
	}
	
	public ItemStackBuilder setAmount(int amount) {
		stack.setAmount(amount);
		
		return this;
	}
	
	public ItemStackBuilder setName(String name) {
		ItemMeta meta = stack.getItemMeta();
		
		meta.setDisplayName(StringUtils.colorize(name));
		stack.setItemMeta(meta);
		
		return this;
	}
	
	public ItemStackBuilder setLore(List<String> lore) {
		ItemMeta meta = stack.getItemMeta();
		
		meta.setLore(lore);
		stack.setItemMeta(meta);
		
		return this;
	}
	
	public ItemStack build() {
		return stack;
	}
}
