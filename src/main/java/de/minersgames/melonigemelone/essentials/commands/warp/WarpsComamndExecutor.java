package de.minersgames.melonigemelone.essentials.commands.warp;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.GUIBuilder;
import de.minersgames.melonigemelone.essentials.utils.ItemStackBuilder;
import de.minersgames.melonigemelone.essentials.utils.manager.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.WarpConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import de.minersgames.melonigemelone.essentials.utils.model.Home;
import de.minersgames.melonigemelone.essentials.utils.model.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class WarpsComamndExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.warps")) {
                ArrayList<ItemStack> items = new ArrayList<>();
                if(!Essentials.warpConfigHandler.warps.isEmpty()) {
                    for (Warp warp : Essentials.warpConfigHandler.warps.values()) {
                        items.add(ItemStackBuilder.getItemStack(Messages.GUI_WARPS_PREFIX.getMessage() + warp.getName(), warp.getMaterial()));
                    }
                    p.openInventory(GUIBuilder.createList(items, Messages.GUI_TITLE_WARPS.getMessage()));
                } else {
                    p.sendMessage(Messages.NO_WARPS_SET.getMessage());
                }
            } else {
                p.sendMessage(Messages.NO_PERM.getMessage());
            }
        } else {
            sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
        }
        return false;
    }
}
