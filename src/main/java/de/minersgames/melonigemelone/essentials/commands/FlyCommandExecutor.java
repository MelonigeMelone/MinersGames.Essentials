package de.minersgames.melonigemelone.essentials.commands;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.fly")) {
               if(args.length == 0) {
                   if(p.isFlying()) {

                       disableFly(p);
                   } else {

                      activateFly(p);
                   }
               } else if(args.length == 1) {
                   if(p.hasPermission("essentials.command.fly.other")) {
                       Player t = Bukkit.getPlayerExact(args[0]);
                       if (t != null) {
                           if (t.isFlying()) {

                               disableFly(t);
                               p.sendMessage(Messages.FLY_DISABLED_OTHER.getMessage().replaceAll("%player%", t.getDisplayName()));
                           } else {

                               activateFly(t);
                               p.sendMessage(Messages.FLY_ACTIVATED_OTHER.getMessage().replaceAll("%player%", t.getDisplayName()));
                           }
                       } else {
                           p.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                       }
                   } else {
                       p.sendMessage(Messages.NO_PERM.getMessage());
                   }
               } else {
                   p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/fly [Spieler]"));
               }
            } else {
                p.sendMessage(Messages.NO_PERM.getMessage());
            }
        } else {
            sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
        }
        return false;
    }

    public static void activateFly(Player p) {
        p.setAllowFlight(true);
        p.setFlying(true);
        p.sendMessage(Messages.FLY_ACTIVATED.getMessage());
    }

    public static void disableFly(Player p) {
        p.setFlying(false);
        p.setAllowFlight(false);
        p.sendMessage(Messages.FLY_DISABLED.getMessage());
    }
}
