package spawnParkour;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import utils.RandomGenerator;

public class spawnParkour extends JavaPlugin{
	
	public int minSpawnTime = 1000;
	public int maxSpawnTime = 3000;
	public boolean isSpawned = false;

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		
		Bukkit.getLogger().info("randNum: " + new RandomGenerator(minSpawnTime, maxSpawnTime).getRandomInt());
	}
	
	

}
