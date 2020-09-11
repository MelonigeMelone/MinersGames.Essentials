package de.minersgames.melonigemelone.essentials.utils.model;

import org.bukkit.Location;
import org.bukkit.Material;

public class Warp {

    private String name;
    private Location location;
    private Material material;

    public Warp(String name, Location location, Material material) {
        this.name = name;
        this.location = location;
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
