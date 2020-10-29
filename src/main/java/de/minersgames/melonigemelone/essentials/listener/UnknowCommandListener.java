package de.minersgames.melonigemelone.essentials.listener;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.help.HelpTopic;

public class UnknowCommandListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if (!event.isCancelled()) {

            String command = event.getMessage().split(" ")[0];
            HelpTopic htopic = Bukkit.getServer().getHelpMap().getHelpTopic(command);
            if (htopic == null) {

                p.sendMessage(Messages.NO_COMMAND_FOUND.getMessage().replaceAll("%command%", command));
                event.setCancelled(true);
            }
        }
    }
}
