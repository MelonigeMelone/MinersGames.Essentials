package de.minersgames.melonigemelone.essentials.utils.handler.config;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.LocationSerialization;
import de.minersgames.melonigemelone.essentials.utils.model.Home;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeConfigHandler {

    public File file = new File (Essentials.getInstance().getDataFolder() + "/PlayerData", "homes.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void createHome(Player p, String homeName, String location, Material material){
        cfg.set("HOMES."+p.getUniqueId()+"."+homeName.toLowerCase(),location + ";" + material.toString());
        save();
    }

    public void deleteHome(Player p, String homeName){
        cfg.set("HOMES."+p.getUniqueId()+"."+homeName.toLowerCase(),null);
        save();
    }

    public List<Home> loadHomePunkte(Player p){
        ArrayList<Home> list = new ArrayList<>();
        if(cfg.isConfigurationSection("HOMES."+p.getUniqueId())){

            for(String key: cfg.getConfigurationSection("HOMES."+p.getUniqueId()).getKeys(false)){
                list.add(loadHomePunkt(p, key));
            }

        }
       return list;
    }

    public Home loadHomePunkt(Player p, String homeName) {
       String[] cfgLine = cfg.getString("HOMES."+p.getUniqueId()+"."+homeName.toLowerCase()).split(";");
        String location = cfgLine[0];
        Location loc = LocationSerialization.getLocationFromString(location);
        return new Home(homeName.toLowerCase(), loc, Material.getMaterial(cfgLine[1]));
    }


    private void save() {
        try {

            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
