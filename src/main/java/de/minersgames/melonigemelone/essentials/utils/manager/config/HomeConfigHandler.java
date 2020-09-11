package de.minersgames.melonigemelone.essentials.utils.manager.config;

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
import java.util.HashMap;
import java.util.UUID;

public class HomeConfigHandler {

    public static HashMap<UUID, ArrayList<Home>> homePunkte = new HashMap<>();

    public static File file = new File (Essentials.getInstance().getDataFolder() + "/PlayerData", "homes.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void createHome(Player p, String homeName){
        String location = LocationSerialization.getStringFromLocation(p.getLocation());
        Material material = p.getLocation().add(0, -1, 0).getBlock().getType();
        if(material.equals(Material.AIR)) {
            material = Material.BARRIER;
        }
        cfg.set("HOMES."+p.getUniqueId()+"."+homeName.toLowerCase(),location + ";" + material.toString());
        save();

       homePunkte.get(p.getUniqueId()).add(new Home(homeName, p.getLocation(), material));
    }
    public static void deleteHome(Player p, String homeName){
        for(Home home : homePunkte.get(p.getUniqueId())) {
            if(home.getName().toLowerCase().equalsIgnoreCase(homeName.toLowerCase())) {
                homePunkte.get(p.getUniqueId()).remove(home);
                break;
            }
        }

        cfg.set("HOMES."+p.getUniqueId()+"."+homeName.toLowerCase(),null);
        save();
    }
    public static Location getHomeLocation(Player p, String homeName){
        for(Home home : homePunkte.get(p.getUniqueId())) {
            if(home.getName().toLowerCase().equalsIgnoreCase(homeName.toLowerCase())) {
               return home.getLoc();
            }
        }
        return null;
    }

    public static Home getHomePunkt(Player p, String homeName) {
        for(Home home : homePunkte.get(p.getUniqueId())) {
            if(home.getName().toLowerCase().equalsIgnoreCase(homeName.toLowerCase())) {
                return home;
            }
        }
        return null;
    }

    public static boolean isHomeExist(Player p, String homeName){
        for(Home home : homePunkte.get(p.getUniqueId())) {
            if(home.getName().toLowerCase().equalsIgnoreCase(homeName.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getPlayerHomes(Player p){
        ArrayList<String> list = new ArrayList<>();
        for(Home home : homePunkte.get(p.getUniqueId())) {
            list.add(home.getName().toLowerCase());
        }
        return list;
    }

    public static ArrayList<Home> getHomePunkte(Player p){
        return homePunkte.get(p.getUniqueId());
    }

    public static void loadHomePunkte(Player p){
        ArrayList<Home> list = new ArrayList<>();
        if(cfg.isConfigurationSection("HOMES."+p.getUniqueId())){

            for(String key: cfg.getConfigurationSection("HOMES."+p.getUniqueId()).getKeys(false)){
                list.add(loadHomePunkt(p, key));
            }

        }
        homePunkte.put(p.getUniqueId(), list);
    }

    public static Home loadHomePunkt(Player p, String homeName) {
       String[] cfgLine = cfg.getString("HOMES."+p.getUniqueId()+"."+homeName.toLowerCase()).split(";");
        String location = cfgLine[0];
        Location loc = LocationSerialization.getLocationFromString(location);
        return new Home(homeName.toLowerCase(), loc, Material.getMaterial(cfgLine[1]));
    }



    public static double countPlayerHomes(Player p) {
        return homePunkte.get(p.getUniqueId()).size();
    }

    public static void save() {
        try {

            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
