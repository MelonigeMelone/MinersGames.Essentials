package de.minersgames.melonigemelone.essentials.utils.handler;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.model.Group;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    public Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public Permission permission = Essentials.perms;

    public List<Group> loadedGroups = new ArrayList<>();

    public Group getGroup(Player p) {
        String playerGroup = permission.getPrimaryGroup(p);
        for(Group group : loadedGroups) {
            if(group.getName().toUpperCase().equalsIgnoreCase(playerGroup.toUpperCase())) {
                return group;
            }
        }
        return null;
    }
}
