package de.minersgames.melonigemelone.essentials.utils.model;

import org.bukkit.Location;
import org.bukkit.Material;

public class Home {

    private String name;
    private Location loc;
    private Material material;

    public Home(String name, Location loc, Material material) {
        this.name = name;
        this.loc = loc;
        this.material = material;
    }

    public Location getLoc() {
        return loc;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }
}
