package de.minersgames.melonigemelone.essentials.commands.warp;

import de.minersgames.melonigemelone.essentials.utils.manager.config.WarpConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class DelWarpCommandExecutor implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.delwarp")) {
                if(args.length == 1) {
                    String warpName = args[0];
                    if(WarpConfigHandler.isWarpExist(warpName)) {
                        WarpConfigHandler.deleteWarp(warpName);
                        p.sendMessage(Messages.WARP_DELETED.getMessage().replaceAll("%warp%", warpName));
                    } else {
                        p.sendMessage(Messages.WARP_NOT_EXISTS.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/delwarp (Name)"));
                }

            } else {
                p.sendMessage(Messages.NO_PERM.getMessage());
            }
        } else {
            sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("core.command.warp.all")) {
                if (args.length == 1) {
                    return WarpConfigHandler.warps.keySet().stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
                }
            }
        }
        return null;

    }
}
