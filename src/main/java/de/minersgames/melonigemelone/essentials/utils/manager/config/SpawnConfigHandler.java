package de.minersgames.melonigemelone.essentials.utils.manager.config;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.LocationSerialization;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class SpawnConfigHandler {

    public SpawnConfigHandler() {
        loadSpawn();
    }

    public Location spawnLocation = null;

    private File file = new File(Essentials.getInstance().getDataFolder() + "/WarpSystem", "spawn.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void setSpawn(Location spawnLoc) {
        String location = LocationSerialization.getStringFromLocation(spawnLoc);
        cfg.set("SPAWN", location);
        save();

        spawnLocation = spawnLoc;
    }

    public boolean spawnSet() {
        if(spawnLocation == null) {
            return false;
        }

        return true;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void loadSpawn() {
        if(file.exists()) {
            if(cfg.contains("SPAWN")) {
                spawnLocation = LocationSerialization.getLocationFromString(cfg.getString("SPAWN"));
            }
        }
    }

    private void save() {
        try {
            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
