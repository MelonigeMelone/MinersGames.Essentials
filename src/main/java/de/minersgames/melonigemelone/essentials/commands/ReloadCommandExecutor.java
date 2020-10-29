package de.minersgames.melonigemelone.essentials.commands;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.GUIBuilder;
import de.minersgames.melonigemelone.essentials.utils.ItemStackBuilder;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import de.minersgames.melonigemelone.essentials.utils.model.Home;
import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class ReloadCommandExecutor implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if(p.hasPermission("essentials.command.reload")) {
                    Essentials.rankConfigHandler.createFile();
                    Essentials.rankConfigHandler.loadGroups();
                } else {
                    p.sendMessage(Messages.NO_PERM.getMessage());
                }
            } else {
                sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
            }
            return false;
        }


}
