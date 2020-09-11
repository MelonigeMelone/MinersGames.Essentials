package de.minersgames.melonigemelone.essentials.listener;

import de.minersgames.melonigemelone.essentials.utils.manager.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.SpawnConfigHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        HomeConfigHandler.loadHomePunkte(p);

        if(SpawnConfigHandler.spawnSet()) {
            p.teleport(SpawnConfigHandler.getSpawnLocation());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        HomeConfigHandler.homePunkte.remove(p.getUniqueId());

    }
}
