package de.minersgames.melonigemelone.essentials.commands.warp;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateWarpCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.createwarp")) {
                if(args.length == 2) {
                    String warpName = args[0];
                    if(!Essentials.warpConfigHandler.isWarpExist(warpName)) {
                        if(Material.matchMaterial(args[1]) != null) {
                            Essentials.warpConfigHandler.createWarp(warpName, p.getLocation(), Material.getMaterial(args[1]));
                                p.sendMessage(Messages.WARP_CREATED.getMessage().replaceAll("%warp%", warpName));
                        } else {
                            p.sendMessage(Messages.UNKNOW_MATERIAL.getMessage());
                        }
                    } else {
                        p.sendMessage(Messages.WARP_ALREAY_EXISTS.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/createwarp (Name) (Material)"));
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
