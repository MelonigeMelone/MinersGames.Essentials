package de.minersgames.melonigemelone.essentials.utils.manager.config.groups;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.manager.GroupHandler;
import de.minersgames.melonigemelone.essentials.utils.model.Group;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class RankConfigHandler {

    public RankConfigHandler() {
        createFile();
        loadGroups();
    }

    private File file = new File(Essentials.getInstance().getDataFolder() + "/Groups", "ranks.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void createFile() {

        for(String group : Essentials.perms.getGroups()) {
            if(!cfg.contains("GROUP." + group)) {
                cfg.set("GROUP." + group + ".Chat.Prefix", "&e" + group + " ");
                cfg.set("GROUP." + group + ".Chat.Suffix", "");
                cfg.set("GROUP." + group + ".Chat.DisplayNameColors", "&7");
                cfg.set("GROUP." + group + ".Chat.Format", "%displayname% &8&l>> &7%message%");

                cfg.set("GROUP." + group + ".Tab.Prefix", "&e" + group + " &7");
                cfg.set("GROUP." + group + ".Tab.Suffix", "");
                cfg.set("GROUP." + group + ".Tab.Rank", "01" + group);
                cfg.set("GROUP." + group + ".Tab.DisplayNameColors", "&7");

                cfg.set("GROUP." + group + ".ScoreBoard.DisplayName", "&e" + group);
            } else if(!cfg.contains("GROUP." + group + ".ScoreBoard.DisplayName")) {
                cfg.set("GROUP." + group + ".ScoreBoard.DisplayName", "&e" + group);
            }
        }

        save();

    }

    public void loadGroups() {
        for(String key : cfg.getConfigurationSection("GROUP").getKeys(false)){
            String chatPrefix = cfg.getString("GROUP." + key + ".Chat.Prefix").replaceAll("&", "§");
            String chatSuffix = cfg.getString("GROUP." + key + ".Chat.Suffix").replaceAll("&", "§");
            String chatFormat = cfg.getString("GROUP." + key + ".Chat.Format").replaceAll("&", "§");
            String[] chatDisplayNameColors = cfg.getString("GROUP." + key + ".Chat.DisplayNameColors").replaceAll("&", "§").split(",");

            String tabPrefix = cfg.getString("GROUP." + key + ".Tab.Prefix").replaceAll("&", "§");
            String tabSuffix = cfg.getString("GROUP." + key + ".Tab.Suffix").replaceAll("&", "§");
            String tabRank = cfg.getString("GROUP." + key + ".Tab.Rank");
            String[] tabDisplayNameColors = cfg.getString("GROUP." + key + ".Tab.DisplayNameColors").replaceAll("&", "§").split(",");

            String sbDisplayName =   cfg.getString("GROUP." + key + ".ScoreBoard.DisplayName");

            GroupHandler.loadedGroups.add(new Group(key,chatPrefix,chatSuffix,chatDisplayNameColors,chatFormat,tabPrefix,tabSuffix,tabRank,tabDisplayNameColors,sbDisplayName));
        }
    }

    private void save() {
        try {

            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
