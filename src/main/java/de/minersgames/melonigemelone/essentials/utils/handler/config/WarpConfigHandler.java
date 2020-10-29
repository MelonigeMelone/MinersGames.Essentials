package de.minersgames.melonigemelone.essentials.utils.handler.config;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.LocationSerialization;
import de.minersgames.melonigemelone.essentials.utils.model.Warp;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class WarpConfigHandler {

    public WarpConfigHandler() {
        loadWarps();
    }

    public HashMap<String, Warp> warps = new HashMap<>();

    private File file = new File(Essentials.getInstance().getDataFolder() + "/WarpSystem", "warps.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void createWarp(String warpName, Location warpLocation, Material material) {
        String location = LocationSerialization.getStringFromLocation(warpLocation);
        cfg.set("WARPS."+ warpName.toLowerCase(),location + ";" + material.toString());
        save();

        warps.put(warpName.toLowerCase(), new Warp(warpName.toLowerCase(), warpLocation, material));
    }

    public void deleteWarp(String warpName){

        warps.remove(warpName.toLowerCase());



        cfg.set("WARPS." +  warpName.toLowerCase(), null);
        save();
    }

    public Location getWarpLocation(String warpName){
        for(Warp warp : warps.values()) {
            if(warp.getName().toLowerCase().equalsIgnoreCase(warpName.toLowerCase())) {
                return warp.getLocation();
            }
        }
        return null;
    }

    public boolean isWarpExist(String warpName){
        if (warps.keySet().contains(warpName.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void loadWarps(){
        ArrayList<String> list = new ArrayList<>();
        if(cfg.isConfigurationSection("WARPS")){
            for(String key: cfg.getConfigurationSection("WARPS").getKeys(false)){
                String[] cfgLine = cfg.getString("WARPS."+key.toLowerCase()).split(";");
                warps.put(key, new Warp(key, LocationSerialization.getLocationFromString(cfgLine[0]), Material.getMaterial(cfgLine[1])));
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
