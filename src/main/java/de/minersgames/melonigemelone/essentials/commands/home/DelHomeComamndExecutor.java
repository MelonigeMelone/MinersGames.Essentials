package de.minersgames.melonigemelone.essentials.commands.home;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class DelHomeComamndExecutor implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.delhome")) {
                if(args.length == 1) {
                    String homeName = args[0];
                    PlayerData playerData = Essentials.playerHandler.get(p);
                    if(playerData.existsHome(homeName)) {
                        playerData.delHome(homeName);
                        p.sendMessage(Messages.HOME_DELETED.getMessage().replaceAll("%name%", homeName));
                    } else {
                        p.sendMessage(Messages.HOME_NOT_EXISTS.getMessage());
                    }
                } else {
                    p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/delhome (Name)"));
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
            PlayerData playerData = Essentials.playerHandler.get(p);
            if(!playerData.getHomes().isEmpty()) {
                if (args.length == 1) {
                    return playerData.getHomeNames().stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
                }
            }
        }
        return null;

    }
}
