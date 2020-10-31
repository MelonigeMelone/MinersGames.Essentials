package de.minersgames.melonigemelone.essentials.utils.handler.config;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.ScoreBoardHandler;
import de.minersgames.melonigemelone.essentials.utils.model.ScoreBoardData;
import de.minersgames.melonigemelone.essentials.utils.model.TablistData;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class TablistConfigHandler {

    public TablistConfigHandler() {
        createFile();
        load();
    }

    private File file = new File(Essentials.getInstance().getDataFolder(), "tablist.yml");
    public FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public void createFile() {
        if(!file.exists()) {
            cfg.set("Tablist.Header", "%nL%&e&lM&6&lin&e&ler-&6&lGa&e&lme&6&ls%nL%&7Header%nL%");
            cfg.set("Tablist.Footer", "%nL%&7Footer%nL%");
        }
        save();
    }

    public void load() {
        String header = cfg.getString("Tablist.Header").replaceAll("&", "§").replaceAll("%nL%", "\n");
        String footer = cfg.getString("Tablist.Footer").replaceAll("&", "§").replaceAll("%nL%", "\n");

        Essentials.tablistHandler.tablistData = new TablistData(header, footer);
    }

    private void save() {
        try {

            cfg.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
