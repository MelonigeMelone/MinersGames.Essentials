package de.minersgames.melonigemelone.essentials.commands.home;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.GUIBuilder;
import de.minersgames.melonigemelone.essentials.utils.ItemStackBuilder;
import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import de.minersgames.melonigemelone.essentials.utils.model.Home;
import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class HomesComamndExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.homes")) {
                ArrayList<ItemStack> items = new ArrayList<>();
                PlayerData playerData = Essentials.playerHandler.get(p);
                if(!playerData.getHomes().isEmpty()) {
                    for (Home home : playerData.getHomes()) {
                        items.add(ItemStackBuilder.getItemStack(Messages.GUI_HOME_PREFIX.getMessage() + home.getName(), home.getMaterial()));
                    }
                    p.openInventory(GUIBuilder.createList(items, Messages.GUI_TITLE_HOMES.getMessage()));
                } else {
                    p.sendMessage(Messages.NO_HOMES_SET.getMessage());
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
