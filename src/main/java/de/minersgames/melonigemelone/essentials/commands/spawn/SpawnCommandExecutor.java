package de.minersgames.melonigemelone.essentials.commands.spawn;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.manager.config.SpawnConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommandExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;
            if(Essentials.spawnConfigHandler.spawnSet()) {
                p.teleport(Essentials.spawnConfigHandler.getSpawnLocation());
                p.sendMessage(Messages.SPAWN_TELEPORT.getMessage());
            } else {
                p.sendMessage(Messages.SPAWN_NOT_SET.getMessage());
            }
        } else {
            sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
        }
        return false;
    }
}
