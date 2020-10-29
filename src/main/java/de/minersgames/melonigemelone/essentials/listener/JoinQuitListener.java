package de.minersgames.melonigemelone.essentials.listener;

import de.minersgames.melonigemelone.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.lang.management.BufferPoolMXBean;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Essentials.playerHandler.load(p);

        e.setJoinMessage(null);

        for(Player t : Bukkit.getOnlinePlayers()) {
            Essentials.scoreBoardHandler.sendScoreBoard(t);
        }


        if(Essentials.spawnConfigHandler.spawnSet()) {
            p.teleport(Essentials.spawnConfigHandler.getSpawnLocation());
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        Player p = e.getPlayer();

        e.setQuitMessage(null);

        Essentials.playerHandler.remove(p);

    }
}
