package de.minersgames.melonigemelone.essentials.utils.handler;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.config.ScoreBoardConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.model.Group;
import de.minersgames.melonigemelone.essentials.utils.model.PlayerData;
import de.minersgames.melonigemelone.essentials.utils.model.ScoreBoardData;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;


public class ScoreBoardHandler {

    public ScoreBoardHandler() {
        if(scoreBoardData.isEnabled()) {
            updateScoreBoard();
        }
    }

    public static ScoreBoardData scoreBoardData;

    public void sendScoreBoard(Player p) {
        if(scoreBoardData.isEnabled()) {
            PlayerData playerData = Essentials.playerHandler.get(p);

            Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();

            Objective obj = sb.getObjective("aaa");
            if(obj == null) {
                obj = sb.registerNewObjective("aaa", "bbb");
            }

            obj.setDisplayName(scoreBoardData.getDisplayName().replaceAll("&", "§"));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            int counter = scoreBoardData.getLines().size();
            for(String line : scoreBoardData.getLines()) {
                line = PlaceholderAPI.setPlaceholders(p, line);
                line = line.replaceAll("%group_displayname%", playerData.getGroup().getSbDislayName());
                line = line.replaceAll("&", "§");
                setScore(obj, counter, line);
                counter--;
            }

            for(Group group : Essentials.groupHandler.loadedGroups) {
                registerTeam(sb,group.getTabRank(), group.getTabPrefix(), group.getTabSuffix());
            }
            String team = playerData.getGroup().getTabRank();

            sb.getTeam(team).addPlayer(p);

            p.setDisplayName(sb.getTeam(team).getPrefix() + playerData.getTabDisplayName() + sb.getTeam(team).getSuffix());

            p.setScoreboard(sb);

        }
    }

    public void updateScoreBoard(Player p) {
        if(scoreBoardData.isEnabled()) {
            if(p.getScoreboard() == null) {
                sendScoreBoard(p);
            }

            PlayerData playerData = Essentials.playerHandler.get(p);

            Scoreboard sb = p.getScoreboard();
            Objective obj =  sb.getObjective("aaa");
            if(obj == null) {
                obj = sb.registerNewObjective("aaa", "bbb");
            }

            for(Group group : Essentials.groupHandler.loadedGroups) {
                registerTeam(sb,group.getTabRank(), group.getTabPrefix(), group.getTabSuffix());
            }
            String team = playerData.getGroup().getTabRank();

            sb.getTeam(team).addPlayer(p);

            p.setDisplayName(sb.getTeam(team).getPrefix() + playerData.getTabDisplayName() + sb.getTeam(team).getSuffix());
        }
    }

    public void registerTeam(Scoreboard sb, String teamName, String prefix, String suffix) {
        Team team = sb.getTeam(teamName);
        if(team == null) {
            team = sb.registerNewTeam(teamName);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
    }

    public void updateTeam(Scoreboard sb, String teamName, String prefix, String suffix, ChatColor entry) {
        Team team = sb.getTeam(teamName);
        if(team == null) {
            team = sb.registerNewTeam(teamName);
        }
        team.setPrefix(prefix);
        team.setSuffix(suffix);
        team.addEntry(entry.toString());
    }

    public void setScore(Objective objective, int lineNumber, String line) {
        Score score = objective.getScore(line);
        score.setScore(lineNumber);
    }

    public void updateScoreBoard() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Essentials.getInstance(), new Runnable() {
            @Override
            public void run() {
                for (Player t : Bukkit.getOnlinePlayers()) {
                    sendScoreBoard(t);
                }
            }
        }, 20L, 10*20);
    }



}
