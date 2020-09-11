package de.minersgames.melonigemelone.essentials.commands.home;

import de.minersgames.melonigemelone.essentials.utils.manager.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeComamndExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.sethome")) {
                if(args.length == 1) {
                    String homeName = args[0];
                    if(!HomeConfigHandler.isHomeExist(p, homeName)) {
                        HomeConfigHandler.createHome(p, homeName);
                        p.sendMessage(Messages.HOME_CREATED.getMessage().replaceAll("%name%", homeName));
                    } else {
                        p.sendMessage(Messages.HOME_ALREADY_EXISTS.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/sethome (Name)"));
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
