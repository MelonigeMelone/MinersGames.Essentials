package de.minersgames.melonigemelone.essentials.utils.manager;

import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;

public class PlayerHandler {

    public static ArrayList<PlayerData> loadedPlayerData = new ArrayList<>();

    public PlayerData get(Player p) {
        for(PlayerData playerData : loadedPlayerData) {
            if(playerData.getPlayer().getUniqueId().equals(p.getUniqueId())) {
                return playerData;
            }
        }
        return null;
    }

    public void load(Player p) {
        PlayerData playerData = new PlayerData(p, GroupHandler.getGroup(p));
        loadedPlayerData.add(playerData);

        String team = playerData.getGroup().getTabRank();
        Scoreboard scoreboard = GroupHandler.scoreboard;

        scoreboard.getTeam(team).addPlayer(p);

        p.setDisplayName(scoreboard.getTeam(team).getPrefix() + playerData.getTabDisplayName() + scoreboard.getTeam(team).getSuffix());

        for(Player all : Bukkit.getOnlinePlayers())  {
            all.setScoreboard(scoreboard);
        }
    }

    public void remove(Player p) {

        loadedPlayerData.remove(get(p));

    }

}
