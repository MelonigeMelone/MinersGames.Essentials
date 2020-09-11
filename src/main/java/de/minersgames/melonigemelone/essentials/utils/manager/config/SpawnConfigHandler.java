package de.minersgames.melonigemelone.essentials.utils.manager.config;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.LocationSerialization;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnConfigHandler {

    public static Location spawnLocation = null;

    private static File file = new File(Essentials.getInstance().getDataFolder() + "/WarpSystem", "spawn.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void setSpawn(Location spawnLoc) {
        String location = LocationSerialization.getStringFromLocation(spawnLoc);
        cfg.set("SPAWN", location);
        save();

        spawnLocation = spawnLoc;
    }

    public static boolean spawnSet() {
        if(spawnLocation == null) {
            return false;
        }

        return true;
    }

    public static Location getSpawnLocation() {
        return spawnLocation;
    }

    public static void loadSpawn() {
        if(file.exists()) {
            if(cfg.contains("SPAWN")) {
                spawnLocation = LocationSerialization.getLocationFromString(cfg.getString("SPAWN"));
            }
        }
    }

    public static void save() {
        try {
            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
