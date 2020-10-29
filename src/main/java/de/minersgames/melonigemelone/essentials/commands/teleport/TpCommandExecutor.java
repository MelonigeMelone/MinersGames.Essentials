package de.minersgames.melonigemelone.essentials.commands.teleport;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.tp")) {
                if (args.length == 1) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t != null) {
                        p.teleport(t);
                        p.sendMessage(Messages.TELEPORTED_TO_PLAYER.getMessage().replaceAll("%player%", t.getDisplayName()));
                    } else {
                        p.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                    }
                } else if(args.length == 3) {
                    try {
                        Location loc = new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
                        p.teleport(loc);
                        p.sendMessage(Messages.TELEPORTED_TO_LOCATION.getMessage().replaceAll("%location%", "§7X: §e" + loc.getX() + " §7Y: §e" + loc.getY() + " §7Z: §e" + loc.getZ()));
                    } catch(NumberFormatException e) {
                        p.sendMessage(Messages.WRONG_VALUE.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/tp (Spieler) oder /tp (x) (Y) (z)" ));
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
