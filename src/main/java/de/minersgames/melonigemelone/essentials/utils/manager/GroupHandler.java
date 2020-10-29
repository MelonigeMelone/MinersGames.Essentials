package de.minersgames.melonigemelone.essentials.utils.manager;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.model.Group;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class GroupHandler {

    public static Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

    public static Permission permission = Essentials.perms;

    public static List<Group> loadedGroups = new ArrayList<>();

    public static Group getGroup(Player p) {
        String playerGroup = permission.getPrimaryGroup(p);
        for(Group group : loadedGroups) {
            if(group.getName().toUpperCase().equalsIgnoreCase(playerGroup.toUpperCase())) {
                return group;
            }
        }
        return null;
    }
}
