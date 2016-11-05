package org.devathon.contest2016.World;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

public class FactorioWorldManager {

	private static ArrayList<World> factorioWorlds = new ArrayList<World>();
	
	public static void createWorld(String name) {
		WorldCreator creator = new WorldCreator(name);
		creator.type(WorldType.FLAT);
		creator.generateStructures(false);
		
		World newWorld = Bukkit.getServer().createWorld(creator);
		factorioWorlds.add(newWorld);
	}
	
	public static ArrayList<World> getFactorioWorlds() {
		return factorioWorlds;
	}
}
