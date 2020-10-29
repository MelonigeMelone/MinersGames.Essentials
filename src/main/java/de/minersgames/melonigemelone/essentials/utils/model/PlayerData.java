package de.minersgames.melonigemelone.essentials.utils.model;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.LocationSerialization;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerData {

    private Player player;
    private Group group;

    private String chatFormat;
    private String tabDisplayName;

    private List<Home> homes;

    public PlayerData(Player player, Group group) {
        this.player = player;
        this.group = group;

        String chatDisplayName = "";
        if(group.getChatDisplayNameColors().length <= 1) {
            chatDisplayName = group.getChatDisplayNameColors()[0] + player.getDisplayName();
        } else {
            String[] colors = group.getChatDisplayNameColors();
            int colorCounter = 0;

            for(int i = 0; i<player.getDisplayName().length(); i++) {
                if(colorCounter >= colors.length) {
                    colorCounter = 0;
                }

                chatDisplayName = chatDisplayName + colors[colorCounter].replaceAll("&", "§") + player.getDisplayName().charAt(i);

                colorCounter++;
            }
        }
        String comletedisplayName = group.getChatPrefix() + chatDisplayName + group.getChatSuffix();

        this.chatFormat = group.getChatFormat().replaceAll("%displayname%", comletedisplayName);

        String tabDisplayName = "";
        if(group.getTabDisplayNameColors().length <= 1) {
            tabDisplayName = group.getTabDisplayNameColors()[0] + player.getDisplayName();
        } else {
            String[] colors = group.getTabDisplayNameColors();
            int colorCounter = 0;

            for(int i = 0; i<player.getDisplayName().length(); i++) {
                if(colorCounter >= colors.length) {
                    colorCounter = 0;
                }

                tabDisplayName = tabDisplayName + colors[colorCounter].replaceAll("&", "§") + player.getDisplayName().charAt(i);

                colorCounter++;
            }
        }

        System.out.println(tabDisplayName);

        this.tabDisplayName = tabDisplayName;
        this.homes = Essentials.homeConfigHandler.loadHomePunkte(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getChatFormat() {
        return chatFormat;
    }

    public void setChatFormat(String chatFormat) {
        this.chatFormat = chatFormat;
    }

    public String getTabDisplayName() {
        return tabDisplayName;
    }

    public void setTabDisplayName(String tabDisplayName) {
        this.tabDisplayName = tabDisplayName;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public List<String> getHomeNames() {
        List<String> homeNames = new ArrayList<>();

        for(Home home : homes) {
          homeNames.add(home.getName().toLowerCase());
        }

        return homeNames;
    }

    public Home getHome(String homeName) {
        for(Home home : homes) {
            if(home.getName().toLowerCase().equalsIgnoreCase(homeName.toLowerCase())) {
                return home;
            }
        }
        return null;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }

    public boolean existsHome(String homeName) {
        if(getHomeNames().contains(homeName.toLowerCase())) {
            return true;
        }
        return false;
    }

    public void createHome(String homeName, Location loc) {
        String location = LocationSerialization.getStringFromLocation(loc);
        Material material = loc.add(0, -1, 0).getBlock().getType();
        if(material.equals(Material.AIR)) {
            material = Material.BARRIER;
        }
        homes.add(new Home(homeName, loc, material));

        Essentials.homeConfigHandler.createHome(player, homeName, location, material);
    }

    public void delHome(String homeName) {
        homes.remove(getHome(homeName));

        Essentials.homeConfigHandler.deleteHome(player, homeName);
    }

    public void teleportHome(String homeName) {
        player.teleport(getHome(homeName).getLoc());
    }
}
