package spawnParkour;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import Game.Game;
import utils.RandomGenerator;

public class spawnParkour extends JavaPlugin {

	public int minSpawnTime = 100;
	public int maxSpawnTime = 300;
	public int spawnTime = 100;
	public RandomGenerator randomGenerator = new RandomGenerator();

	public List<Location> spawnLocations = new ArrayList<Location>();

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {

		// build location array for chests
		spawnLocations.add(new Location(Bukkit.getWorld("world_start"), -7, 35, 367));
		spawnLocations.add(new Location(Bukkit.getWorld("world_start"), -36, 30, 380));
		spawnLocations.add(new Location(Bukkit.getWorld("world_start"), -65, 39, 374));

		setNewSpawnTime();
		startup();
	}

	public void setNewSpawnTime() {
		spawnTime = randomGenerator.getRandomInt(minSpawnTime, maxSpawnTime);
	}

	public void startup() {
		setNewSpawnTime();

		new BukkitRunnable() {
			public void run() {
				new Game();
			}
		}.runTaskLater(this, spawnTime * 20);

	}

}
