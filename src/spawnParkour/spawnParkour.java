package spawnParkour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import ConfigStuff.HashmapManager;
import Game.Game;
import Listeners.CommandListener;
import utils.LocationHandler;
import utils.RandomGenerator;

public class spawnParkour extends JavaPlugin {

	public int minSpawnTime = 20;
	public int maxSpawnTime = 30;
	public int spawnTime = 100;
	
	public File configFile;
	public FileConfiguration config;
	public File chestBlocksFile;
	public File parkourBlocksFile;
	public HashmapManager hashmapManager = new HashmapManager();
	public LocationHandler locationHandler = new LocationHandler();

	public List<Location> spawnLocations = new ArrayList<Location>();
	public List<Location> blockLocations = new ArrayList<Location>();
	public Map<String, Material> chestBlocks = new HashMap<>();
	public Map<String, Material> parkourBlocks = new HashMap<>();

	@Override
	public void onDisable() {
		despawnALL();
	}

	@Override
	public void onEnable() {

		// build location array for chests
	//	spawnLocations.add(new Location(Bukkit.getWorld("world_start"), -7, 35, 367));
	//	spawnLocations.add(new Location(Bukkit.getWorld("world_start"), -36, 30, 380));
	//	spawnLocations.add(new Location(Bukkit.getWorld("world_start"), -65, 39, 374));


		this.getCommand("spgame").setExecutor(new CommandListener());

		chestBlocksFile = new File(getDataFolder(), "chestBlocks.bin");
		parkourBlocksFile = new File(getDataFolder(), "parkourBlocks.bin");
	
		if (!chestBlocksFile.exists()) {
			copy(getResource("chestBlocks.bin"), chestBlocksFile);
		}
	
		if (!parkourBlocksFile.exists()) {
			copy(getResource("parkourBlocks.bin"), parkourBlocksFile);
		}
		
		 
		hashmapManager.loadHashs();
		
		for(String s : chestBlocks.keySet()){
			spawnLocations.add(locationHandler.str2loc(s));
		}
		
		for(String s : parkourBlocks.keySet()){
			blockLocations.add(locationHandler.str2loc(s));
		}
		
		despawnALL();
		setNewSpawnTime();
		startup();
	}

	public void setNewSpawnTime() {
		spawnTime = new RandomGenerator(minSpawnTime, maxSpawnTime).getRandomInt();
		Bukkit.broadcastMessage("spawnTime: "+spawnTime);
	}

	public void startup() {
		setNewSpawnTime();

		new BukkitRunnable() {
			public void run() {
				if(spawnLocations.size() > 0){
				new Game();
				} else {
					Bukkit.broadcastMessage("no locations found. cannot start.");
				}
			
			}
		}.runTaskLater(this, spawnTime * 20);

	}
	
	public void despawnALL(){
		for(String s : parkourBlocks.keySet()){
			Location loc = locationHandler.str2loc(s);
			loc.getBlock().setType(Material.AIR);
		}
		
		for(String s : chestBlocks.keySet()){
			Location loc = locationHandler.str2loc(s);
			loc.getBlock().setType(Material.AIR);
		}
		
	}
	
	// -------------------------------------------------------------------------------------
	// copy
	// -------------------------------------------------------------------------------------
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
