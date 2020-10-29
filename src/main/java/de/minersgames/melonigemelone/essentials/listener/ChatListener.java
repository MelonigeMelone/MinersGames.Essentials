package de.minersgames.melonigemelone.essentials.listener;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        PlayerData playerData = Essentials.playerHandler.get(p);

        String message = e.getMessage();

        if(p.hasPermission("essentials.chatColors")) {
            message = message.replaceAll("&", "§");
        }

        e.setFormat(playerData.getChatFormat().replaceAll("%message%", message));
    }
}
