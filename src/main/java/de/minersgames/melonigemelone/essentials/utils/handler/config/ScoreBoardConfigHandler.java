package de.minersgames.melonigemelone.essentials.utils.handler.config;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.GroupHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.ScoreBoardHandler;
import de.minersgames.melonigemelone.essentials.utils.model.Group;
import de.minersgames.melonigemelone.essentials.utils.model.ScoreBoardData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ScoreBoardConfigHandler {

    public ScoreBoardConfigHandler() {
        createFile();
        load();
    }

    private File file = new File(Essentials.getInstance().getDataFolder(), "scoreboard.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void createFile() {
        if(!file.exists()) {
            cfg.set("ScoreBaord.Enabled", false);
            cfg.set("ScoreBaord.Title", "&eMiner-Games");
            cfg.set("ScoreBaord.Lines", Arrays.asList("&7Spieler Online:", "&8-> &e%playerlist_online,normal,yes,amount%","  ", "&7Gruppe", "&8-> &e%group_displayname%"));
        }
        save();
    }

    public void load() {
        boolean enabled = cfg.getBoolean("ScoreBaord.Enabled");
        String displayName = cfg.getString("ScoreBaord.Title");
        List<String> lines = cfg.getStringList("ScoreBaord.Lines");

        ScoreBoardHandler.scoreBoardData = new ScoreBoardData(enabled, displayName, lines);
    }

    private void save() {
        try {

            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
