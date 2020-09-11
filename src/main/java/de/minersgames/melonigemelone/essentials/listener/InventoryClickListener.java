package de.minersgames.melonigemelone.essentials.listener;

import de.minersgames.melonigemelone.essentials.utils.manager.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.WarpConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase(Messages.GUI_TITLE_HOMES.getMessage())) {
            e.setCancelled(true);
            handleHomes(e);
        } else if(e.getView().getTitle().equalsIgnoreCase(Messages.GUI_TITLE_WARPS.getMessage())) {
            e.setCancelled(true);
            handleWarps(e);
        }

    }

    public void handleHomes(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack is = e.getCurrentItem();
        if(is != null) {
            if(is.getType() != Material.AIR) {
                if(is.getItemMeta().hasDisplayName()) {
                    String homeName = is.getItemMeta().getDisplayName().replaceAll(Messages.GUI_HOME_PREFIX.getMessage(), "");
                    if (HomeConfigHandler.isHomeExist(p, homeName)) {
                        p.teleport(HomeConfigHandler.getHomeLocation(p, homeName));
                    }
                }
            }
        }
    }

    public void handleWarps(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack is = e.getCurrentItem();
        if(is != null) {
            if(is.getType() != Material.AIR) {
                if(is.getItemMeta().hasDisplayName()) {
                    String warpName = is.getItemMeta().getDisplayName().replaceAll(Messages.GUI_WARPS_PREFIX.getMessage(), "");
                    if (WarpConfigHandler.isWarpExist(warpName)) {
                        if(p.hasPermission("essentials.command.warp." + warpName) || p.hasPermission("essentials.command.warp.*")) {
                            p.teleport(WarpConfigHandler.getWarpLocation(warpName));
                        } else {
                            p.sendMessage(Messages.NO_PERM.getMessage());
                        }
                    }
                }
            }
        }
    }
}
