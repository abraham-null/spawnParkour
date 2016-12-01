package ConfigStuff;

import java.io.File;

import spawnParkour.spawnParkour;

public class ConfigStuff {

	 public void createConfig(String filename) {
	        try {
	            if (!spawnParkour.getPlugin(spawnParkour.class).getDataFolder().exists()) {
	            	spawnParkour.getPlugin(spawnParkour.class).getDataFolder().mkdirs();
	            }
	            File file = new File(spawnParkour.getPlugin(spawnParkour.class).getDataFolder(), filename);
	            if (!file.exists()) {
	            	spawnParkour.getPlugin(spawnParkour.class).getLogger().info(filename + " not found, creating!");
	            	spawnParkour.getPlugin(spawnParkour.class).saveDefaultConfig();
	            } else {
	            	spawnParkour.getPlugin(spawnParkour.class).getLogger().info(filename + " found, loading!");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();

	        }

	    }


}
