package org.devathon.contest2016.World.Building;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.devathon.contest2016.Utils.LocationUtils;
import org.devathon.contest2016.World.Building.Buildings.BuildingType;

public class Preview {

	private static HashMap<Player, Preview> playerPreviews = new HashMap<Player, Preview>();
	
	public static boolean hasPreview(Player player) {
		return playerPreviews.containsKey(player);
	}
	
	public static Preview getPreview(Player player) {
		return playerPreviews.get(player);
	}
	
	public static void addPreview(Player player, Preview preview) {
		playerPreviews.put(player, preview);
		getPreview(player).render();
	}
	
	public static void removePreview(Player player) {
		getPreview(player).clean();
		playerPreviews.remove(player);
	}
	
	public static void cleanIfNeccesary(Player player) {
		if(hasPreview(player)) {
			Preview preview = getPreview(player);
			
			if(!preview.isStillValid()) {
				removePreview(player);
			}
		}
	}
	
	//
	
	private Player player;
	private Material itemInHand;
	private BuildingType type;
	private Location target;
	private ArrayList<Vector> previewRelativeVecs;
	
	public Preview(Player player, BuildingType type) {
		this.player = player;
		this.itemInHand = player.getInventory().getItemInMainHand().getType();
		this.type = type;
		this.target = LocationUtils.getTargetBlock(player);
		this.previewRelativeVecs = type.getRelativePreviewVectors();
		
		cleanIfNeccesary(player);
		if(!hasPreview(player)) {
			addPreview(player, this);
		}
	}
	
	public BuildingType getBuildingType() {
		return type;
	}
	
	public Location getTarget() {
		return target;
	}
	
	@SuppressWarnings("deprecation")
	public void render() {
		int color = type.canPlace(target) ? 5 : 14;
		
		if(target.getBlock().getType().isSolid()) {
			for(Vector relativeVec : previewRelativeVecs) {
				Location previewLoc = target.clone().add(relativeVec);
				
				player.sendBlockChange(previewLoc, Material.STAINED_GLASS, (byte) color);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void clean() {
		for(Vector relativeVec : previewRelativeVecs) {
			Location previewLoc = target.clone().add(relativeVec);
			
			player.sendBlockChange(previewLoc, previewLoc.getBlock().getType(), previewLoc.getBlock().getData());
		}
	}
	
	public boolean isStillValid() {		
		return player.getInventory().getItemInMainHand().getType() == itemInHand && LocationUtils.getTargetBlock(player).toVector().equals(target.toVector());
	}
}
