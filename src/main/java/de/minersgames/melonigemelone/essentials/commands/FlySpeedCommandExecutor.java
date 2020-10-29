package de.minersgames.melonigemelone.essentials.commands;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlySpeedCommandExecutor implements CommandExecutor {

    public static String cmdUse = "/flyspeed (1-10) [Spieler]";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.fylspeed")) {
                if(args.length == 1) {
                    try {
                        float speed = (Float.parseFloat(args[0]) / 10);
                        if (speed >= 0 && speed <= 1) {
                            p.setFlySpeed(speed);
                            p.sendMessage(Messages.FLYSPEED_SET.getMessage().replaceAll("%flyspeed%", String.valueOf(speed*10)));
                        } else {
                            p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", cmdUse));
                        }
                    } catch(NumberFormatException e) {
                        p.sendMessage(Messages.WRONG_VALUE.getMessage());
                    }
                } else if(args.length == 2) {
                    if(p.hasPermission("essentials.command.fylspeed.other")) {
                        Player t = Bukkit.getPlayerExact(args[1]);
                        if (t != null) {
                            try {
                                float speed = (Float.parseFloat(args[0]) / 10);
                                if (speed >= 0 && speed <= 1) {
                                    t.setFlySpeed(speed);
                                    t.sendMessage(Messages.FLYSPEED_SET.getMessage().replaceAll("%flyspeed%", String.valueOf(speed * 10)));
                                    p.sendMessage(Messages.FLYSPEED_SET_OTHER.getMessage().replaceAll("%player%", t.getDisplayName()).replaceAll("%flyspeed%", String.valueOf(speed * 10)));
                                } else {
                                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", cmdUse));
                                }
                            } catch (NumberFormatException e) {
                                p.sendMessage(Messages.WRONG_VALUE.getMessage());
                            }
                        } else {
                            p.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                        }
                    } else {
                        p.sendMessage(Messages.NO_PERM.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", cmdUse));
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
