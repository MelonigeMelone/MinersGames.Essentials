package de.minersgames.melonigemelone.essentials.commands.teleport;

import de.minersgames.melonigemelone.essentials.utils.manager.TpaManager;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import de.minersgames.melonigemelone.essentials.utils.model.TpaModel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommandExecutor implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.tpa")) {
                if(args.length == 1) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if(t != null) {
                        if(!t.getUniqueId().equals(p.getUniqueId())) {
                            TpaManager.createTpa(p, t, TpaModel.Variant.TPA);
                        } else {
                            p.sendMessage(Messages.NO_TPA_TO_YOURSELF.getMessage());
                        }
                    } else {
                        p.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/tpa (Spieler)"));
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
