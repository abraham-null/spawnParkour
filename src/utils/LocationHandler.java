package utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationHandler {

	public Location parseLoc(String str) {
		String[] arg = str.split(",");
		double[] parsed = new double[5];
		for (int a = 0; a < 3; a++) {
			parsed[a] = Double.parseDouble(arg[a + 1]);
		}

		Location location = new Location(Bukkit.getServer().getWorld(arg[0]), parsed[0], parsed[1], parsed[2], (float) parsed[3], (float) parsed[4]);
		return location;
	}

	public Location str2loc(String str) {

		String str2loc[] = str.split("\\:");
		Location loc = new Location(Bukkit.getServer().getWorld(str2loc[0]), 0, 0, 0, 0, 0);

		loc.setX(Double.parseDouble(str2loc[1]));

		loc.setY(Double.parseDouble(str2loc[2]));

		loc.setZ(Double.parseDouble(str2loc[3]));

		loc.setYaw((float) Double.parseDouble(str2loc[4]));

		loc.setPitch((float) Double.parseDouble(str2loc[5]));

		return loc;

	}

	public String loc2str(Location loc) {

		return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ() + ":" + (float) loc.getYaw() + ":" + (float) loc.getPitch();

	}
	
}
