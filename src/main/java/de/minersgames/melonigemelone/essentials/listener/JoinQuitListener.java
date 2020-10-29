package de.minersgames.melonigemelone.essentials.listener;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.manager.GroupHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.PlayerHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.ScoreBoardHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.SpawnConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import de.minersgames.melonigemelone.essentials.utils.model.ScoreBoardData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinQuitListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Essentials.playerHandler.load(p);

        e.setJoinMessage(null);

        //ScoreBoardHandler.setScoreBoard(p);


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
