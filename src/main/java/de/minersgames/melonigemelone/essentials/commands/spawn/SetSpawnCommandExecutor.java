package de.minersgames.melonigemelone.essentials.commands.spawn;

import de.minersgames.melonigemelone.essentials.utils.manager.config.SpawnConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(p.hasPermission("essentials.command.setspawn")) {
                SpawnConfigHandler.setSpawn(p.getLocation());
                p.sendMessage(Messages.SPAWN_SET.getMessage());
            } else {
                p.sendMessage(Messages.NO_PERM.getMessage());
            }
        } else {
            sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
        }
        return false;
    }
}
