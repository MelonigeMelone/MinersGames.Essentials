package de.minersgames.melonigemelone.essentials.commands;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadCastCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.broadcast")) {
                if(args.length >= 1) {
                    String message = "§8§m               [§r§e Wichtige Nachricht §8§m]               \n§r";

                    for(String m : args) {
                        message = message + " " + m;
                    }

                    message = message +"\n§8§m                                                         \n";
                    Bukkit.broadcastMessage(message.replaceAll("&", "§"));

                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/broadcast (Nachricht)"));
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
