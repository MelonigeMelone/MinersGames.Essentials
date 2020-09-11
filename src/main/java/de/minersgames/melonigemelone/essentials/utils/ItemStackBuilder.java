package de.minersgames.melonigemelone.essentials.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.LinkedList;
import java.util.List;

public class ItemStackBuilder {

    public static LinkedList<String> stackLore = new LinkedList();

    public static ItemStack getItemStack(Material material, int amount) {
        ItemStack stack = new ItemStack(material);
        ItemMeta stackMeta = stack.getItemMeta();
        stack.setItemMeta(stackMeta);
        stack.setAmount(amount);

        return stack;
    }

    public static ItemStack getItemStack(String Name, Material material) {
        ItemStack stack = new ItemStack(material);
        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setDisplayName(Name);
        stack.setItemMeta(stackMeta);

        return stack;
    }

    public static ItemStack getItemStack(String Name, Material material, int amount) {
        ItemStack stack = new ItemStack(material);
        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setDisplayName(Name);
        stack.setItemMeta(stackMeta);

        return stack;
    }

    public static ItemStack getItemStack(String Name, Material material, List<String> lore) {
        ItemStack stack = new ItemStack(material);
        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setLore(lore);
        stackMeta.setDisplayName(Name);
        stack.setItemMeta(stackMeta);

        return stack;
    }

    public static ItemStack getItemStack(String Name, Material material, String lore) {
        stackLore.clear();
        stackLore.add(lore);
        ItemStack stack = new ItemStack(material);
        ItemMeta stackMeta = stack.getItemMeta();
        stackMeta.setLore(stackLore);
        stackMeta.setDisplayName(Name);
        stack.setItemMeta(stackMeta);

        return stack;
    }
}
