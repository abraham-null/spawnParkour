package ConfigStuff;

import org.bukkit.plugin.Plugin;

import SLAPI.SLAPI;
import spawnParkour.spawnParkour;

public class HashmapManager {
	
	// -------------------------------------------------------------------------------------
		// loadHashs
		// -------------------------------------------------------------------------------------
		public void loadHashs() {

			// load hashmaps
			try {
				spawnParkour.getPlugin(spawnParkour.class).chestBlocks = SLAPI.load(spawnParkour.getPlugin(spawnParkour.class).getDataFolder() + "/chestBlocks.bin");
			} catch (Exception e) {
				// handle the exception
				e.printStackTrace();
			}
			
			try {
				spawnParkour.getPlugin(spawnParkour.class).parkourBlocks = SLAPI.load(spawnParkour.getPlugin(spawnParkour.class).getDataFolder() + "/parkourBlocks.bin");
			} catch (Exception e) {
				// handle the exception
				e.printStackTrace();
			}
		}

		// -------------------------------------------------------------------------------------
		// saveHashs
		// -------------------------------------------------------------------------------------
		public void saveHashs() {

			// save hashmaps
			try {
				SLAPI.save(spawnParkour.getPlugin(spawnParkour.class).chestBlocks, spawnParkour.getPlugin(spawnParkour.class).getDataFolder() + "/chestBlocks.bin");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				SLAPI.save(spawnParkour.getPlugin(spawnParkour.class).parkourBlocks, spawnParkour.getPlugin(spawnParkour.class).getDataFolder() + "/parkourBlocks.bin");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
