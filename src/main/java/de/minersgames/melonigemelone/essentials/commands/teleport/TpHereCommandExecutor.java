package de.minersgames.melonigemelone.essentials.commands.teleport;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpHereCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.tphere")) {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("@all")) {
                        if(p.hasPermission("essentials.command.tphere.all")) {
                            for(Player all : Bukkit.getOnlinePlayers()) {
                                all.teleport(p);
                                all.sendMessage(Messages.TELEPORTED_PLAYER_HERE.getMessage().replaceAll("%player%", p.getDisplayName()));
                            }
                            p.sendMessage(Messages.TELEPORTED_ALL.getMessage());
                        } else {
                            p.sendMessage(Messages.NO_PERM.getMessage());
                        }
                    } else {
                        Player t = Bukkit.getPlayerExact(args[0]);
                        if (t != null) {
                            t.teleport(p);
                            t.sendMessage(Messages.TELEPORTED_PLAYER_HERE.getMessage().replaceAll("%player%", p.getDisplayName()));
                            p.sendMessage(Messages.TELEPORTED_PLAYER_TO_PLAYER.getMessage().replaceAll("%player%", t.getDisplayName()));
                        } else {
                            p.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                        }
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/tphere (Spieler)"));
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
