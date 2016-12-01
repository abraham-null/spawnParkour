package Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import spawnParkour.spawnParkour;
import utils.RandomGenerator;

public class Game implements Runnable, Listener {
	
	private spawnParkour plugin;

	public Game(spawnParkour plugin) {
		this.plugin = plugin;
	}
	

	private List<Location> spawnLocations = new ArrayList<Location>();
	private Location spawnLoc;
	private BukkitTask task;
	private int count = 0;
	private Inventory inv;
	private Chest chest;
	private Block chestBlock;
	

	public Game() {

		 spawnParkour.getPlugin(spawnParkour.class).getServer().getPluginManager().registerEvents(this, spawnParkour.getPlugin(spawnParkour.class));
		this.count = 0;
		this.spawnLocations = spawnParkour.getPlugin(spawnParkour.class).spawnLocations;
		this.spawnLoc = randomLocation();
		
		start();
	}

	@SuppressWarnings("deprecation")
	@EventHandler 
	private void invClose(InventoryOpenEvent e){
		if(e.getInventory().equals(this.inv)){
			Player p = (Player) e.getPlayer();
			chestOpenedMessage(p.getName());
			Bukkit.getWorld("world_start").spigot().playEffect(this.chest.getLocation(), Effect.EXPLOSION_HUGE);
		}
	}
	
	@EventHandler 
	private void invClose(InventoryCloseEvent e){
		if(e.getInventory().equals(this.inv)){
			stop();
		}
	}


	private void spawnChest() {

		this.spawnLoc.getBlock().setType(Material.CHEST);
		this.chestBlock = this.spawnLoc.getBlock();
		this.chest = (Chest) this.chestBlock.getState();
		this.inv = this.chest.getInventory();
		chestSpawnedMessage();
		
		ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemStack web = new ItemStack(Material.WEB, 3);
		ItemStack gold_boots = new ItemStack(Material.GOLD_BOOTS, 1);
		ItemStack fish = new ItemStack(Material.COOKED_FISH, 1);
		ItemStack golden_apple = new ItemStack(Material.GOLDEN_APPLE, 1);
		ItemStack enderpearl = new ItemStack(Material.ENDER_PEARL, 2);

		inv.addItem(diamond_sword, web, gold_boots, fish, golden_apple, enderpearl);

	}

	private void emptyChest(){
		this.inv.clear();
	}
	
	private void chestOpenedMessage(String playerName){
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
		Bukkit.broadcastMessage(ChatColor.AQUA.toString() + "A " + ChatColor.YELLOW.toString() + "Hero Chest"+ ChatColor.AQUA.toString() + " has been claimed by: " + ChatColor.YELLOW.toString() + playerName);
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
	}
	
	private void chestSpawnedMessage(){
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
		Bukkit.broadcastMessage(ChatColor.AQUA.toString() + "A " + ChatColor.YELLOW.toString() + "Hero Chest"+ ChatColor.AQUA.toString() + " has just spawned!.");
		Bukkit.broadcastMessage(ChatColor.AQUA.toString() + "Find it on one of the floating islands @ spawn to claim its contents.");
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
	}
	
	private void chestDespawnedMessage(){
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
		Bukkit.broadcastMessage(ChatColor.AQUA.toString() + "No one was able to find the " + ChatColor.YELLOW.toString() + "Hero Chest");
		Bukkit.broadcastMessage(ChatColor.AQUA.toString() + "The Chest has despawned.");
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
	}
	
	private void oneMinuteWarningMessage(){
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
		Bukkit.broadcastMessage(ChatColor.AQUA.toString() + "The " + ChatColor.YELLOW.toString() + "Hero Chest" + ChatColor.AQUA.toString() + "will");
		Bukkit.broadcastMessage(ChatColor.YELLOW.toString() + "-------------------------------------");
	}
	
	private void despawnChest(Location spawnLoc) {
		
		emptyChest();
		spawnLoc.getBlock().setType(Material.AIR);

	}

	private Location randomLocation() {
		int spawnNum = new RandomGenerator(0, (spawnLocations.size() - 1)).getRandomInt();
		Bukkit.broadcastMessage("islane num: " + spawnNum);
		return spawnLocations.get(spawnNum);
	}


	public boolean start() {
	    if (this.task != null) return false; //Already running
	    spawnChest();
	    this.task = Bukkit.getScheduler().runTaskTimer(spawnParkour.getPlugin(spawnParkour.class), this, 20L, 20L); //Schedule task
	    return true;
	}
	
	public boolean stop() {
	    if (this.task == null) return false;
    	despawnChest(this.spawnLoc);
    	this.count = 0;
	    this.task.cancel();

    	spawnParkour.getPlugin(spawnParkour.class).setNewSpawnTime(); 
    	spawnParkour.getPlugin(spawnParkour.class).startup();
	    
	    return true;
	}

	public void run() {
        count++;
  
        if(count == 60) {
    		oneMinuteWarningMessage();
        }
        
        if(count > 120) {
    		chestDespawnedMessage();
            stop();
        }
    }

}
