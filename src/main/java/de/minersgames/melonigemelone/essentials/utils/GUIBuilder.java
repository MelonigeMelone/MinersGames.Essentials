package de.minersgames.melonigemelone.essentials.utils;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class GUIBuilder {

    public static Inventory createList(ArrayList<ItemStack> items, String homeName) {
        int size = 0;

        if(items.size() > 27) {
            size = 36;
        } else if(items.size() > 18) {
            size = 27;
        } else if(items.size() > 9) {
            size = 18;
        } else {
            size = 9;
        }

        Inventory inv = Bukkit.createInventory(null, size, homeName);

        for(int i = 0; i<items.size(); i++) {
            inv.addItem(items.get(i));
        }

        return inv;
    }
}
