package Listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ConfigStuff.HashmapManager;
import spawnParkour.spawnParkour;

public class CommandListener implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		// Plugin plugin = spawnParkour.getPlugin(spawnParkour.class);

		Player p = (Player) sender;
		if (!p.hasPermission("thelasthero.spawnparkour.start")) {
			return false;
		}

		if (cmd.getName().equalsIgnoreCase("spgame")) {

			if (args.length == 1) {
				// set chest locations
				if (args[0].equalsIgnoreCase("setchest")) {
					spawnParkour.getPlugin(spawnParkour.class).chestBlocks.put(spawnParkour.getPlugin(spawnParkour.class).locationHandler.loc2str(p.getLocation()), Material.CHEST);
					spawnParkour.getPlugin(spawnParkour.class).spawnLocations.add(p.getLocation());
				}

				// set parkour block locations
				if (args[0].equalsIgnoreCase("removechest")) {
					Block block = p.getLocation().subtract(0, 1, 0).getBlock();
					spawnParkour.getPlugin(spawnParkour.class).chestBlocks.remove(spawnParkour.getPlugin(spawnParkour.class).locationHandler.loc2str(p.getLocation()), Material.CHEST);
					spawnParkour.getPlugin(spawnParkour.class).spawnLocations.remove(block.getLocation());
				}

				if (args[0].equalsIgnoreCase("spawnchests")) {

					for (String loc : (spawnParkour.getPlugin(spawnParkour.class).chestBlocks.keySet())) {
						Location location = spawnParkour.getPlugin(spawnParkour.class).locationHandler.str2loc(loc); 
						location.getBlock().setType(spawnParkour.getPlugin(spawnParkour.class).chestBlocks.get(loc));
					}

				}

				// set parkour block locations
				if (args[0].equalsIgnoreCase("setblock")) {
					Block block = p.getLocation().subtract(0, 1, 0).getBlock();
					spawnParkour.getPlugin(spawnParkour.class).parkourBlocks.put(spawnParkour.getPlugin(spawnParkour.class).locationHandler.loc2str(block.getLocation()), block.getType());
				}

				// set parkour block locations
				if (args[0].equalsIgnoreCase("removeblock")) {
					Block block = p.getLocation().subtract(0, 1, 0).getBlock();
					spawnParkour.getPlugin(spawnParkour.class).parkourBlocks.remove(block.getLocation());
				}

				if (args[0].equalsIgnoreCase("spawnblocks")) {

					for (String loc : spawnParkour.getPlugin(spawnParkour.class).parkourBlocks.keySet()) {
						Location location = spawnParkour.getPlugin(spawnParkour.class).locationHandler.str2loc(loc); 
						location.getBlock().setType(spawnParkour.getPlugin(spawnParkour.class).parkourBlocks.get(loc));
					}

				}

				// start game();
				if (args[0].equalsIgnoreCase("start")) {
					spawnParkour.getPlugin(spawnParkour.class).startup();
				}
				
				// save();
				if (args[0].equalsIgnoreCase("save")) {
					spawnParkour.getPlugin(spawnParkour.class).hashmapManager.saveHashs();
				}
			}

		}

		return false;
	}

}
