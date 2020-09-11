package de.minersgames.melonigemelone.essentials.utils.manager.config.messages;

import de.minersgames.melonigemelone.essentials.Essentials;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class MessagesConfigHandler {

    public static File config = new File(Essentials.getInstance().getDataFolder(), "messages.yml");
    public static FileConfiguration configFile = YamlConfiguration.loadConfiguration(config);

    public static void save() {
        try {

            configFile.save(config);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createConfigFile() {
        if (!config.exists()) {
            configFile.options().header("Verdox VerdoxCore Config");

            for (Messages e : Messages.values()) {
                configFile.addDefault("MESSAGE." + e.name(), e.getDefaultMessage());
            }


            configFile.options().copyDefaults(true);
            configFile.options().copyHeader(true);
            save();
        }
    }

    public static String getString(String key) {
        return configFile.getString(key);
    }


    public static String getMessages(Messages messages) {
        String input = configFile.getString("MESSAGE." + messages.name());
        input = input.replaceAll("&", "§");
        return input;

    }


}
