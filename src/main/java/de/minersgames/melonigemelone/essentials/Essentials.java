package de.minersgames.melonigemelone.essentials;

import de.minersgames.melonigemelone.essentials.commands.*;
import de.minersgames.melonigemelone.essentials.commands.home.DelHomeComamndExecutor;
import de.minersgames.melonigemelone.essentials.commands.home.HomeComamndExecutor;
import de.minersgames.melonigemelone.essentials.commands.home.HomesComamndExecutor;
import de.minersgames.melonigemelone.essentials.commands.home.SetHomeComamndExecutor;
import de.minersgames.melonigemelone.essentials.commands.spawn.SetSpawnCommandExecutor;
import de.minersgames.melonigemelone.essentials.commands.spawn.SpawnCommandExecutor;
import de.minersgames.melonigemelone.essentials.commands.teleport.*;
import de.minersgames.melonigemelone.essentials.commands.warp.CreateWarpCommandExecutor;
import de.minersgames.melonigemelone.essentials.commands.warp.DelWarpCommandExecutor;
import de.minersgames.melonigemelone.essentials.commands.warp.WarpCommandExecutor;
import de.minersgames.melonigemelone.essentials.commands.warp.WarpsComamndExecutor;
import de.minersgames.melonigemelone.essentials.listener.InventoryClickListener;
import de.minersgames.melonigemelone.essentials.listener.JoinQuitListener;
import de.minersgames.melonigemelone.essentials.listener.UnknowCommandListener;
import de.minersgames.melonigemelone.essentials.utils.manager.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.SpawnConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.WarpConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.manager.config.messages.MessagesConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Essentials extends JavaPlugin {

    public static Essentials instance;

    @Override
    public void onEnable() {
        instance = this;

        loadData();

        initCommands();
        initListener();
    }

    public void initCommands() {
        getCommand("gamemode").setExecutor(new GamemodeCommandExecutor());
        getCommand("fly").setExecutor(new FlyCommandExecutor());
        getCommand("flyspeed").setExecutor(new FlySpeedCommandExecutor());
        getCommand("walkspeed").setExecutor(new WalkSpeedCommandExecutor());

        getCommand("tpa").setExecutor(new TpaCommandExecutor());
        getCommand("tpaccept").setExecutor(new TpAcceptCommandExecutor());
        getCommand("tpahere").setExecutor(new TpaHereCommandExecutor());
        getCommand("tp").setExecutor(new TpCommandExecutor());
        getCommand("tpdeny").setExecutor(new TpdenyCommandExecutor());
        getCommand("tphere").setExecutor(new TpHereCommandExecutor());

        getCommand("spawn").setExecutor(new SpawnCommandExecutor());
        getCommand("setspawn").setExecutor(new SetSpawnCommandExecutor());

        getCommand("broadcast").setExecutor(new BroadCastCommandExecutor());

        getCommand("sethome").setExecutor(new SetHomeComamndExecutor());
        getCommand("delhome").setExecutor(new DelHomeComamndExecutor());
        getCommand("home").setExecutor(new HomeComamndExecutor());
        getCommand("homes").setExecutor(new HomesComamndExecutor());

        getCommand("createwarp").setExecutor(new CreateWarpCommandExecutor());
        getCommand("delwarp").setExecutor(new DelWarpCommandExecutor());
        getCommand("warp").setExecutor(new WarpCommandExecutor());
        getCommand("warps").setExecutor(new WarpsComamndExecutor());

    }

    public void initListener() {
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new UnknowCommandListener(), this);
    }

    public static void loadData() {
        MessagesConfigHandler.createConfigFile();
        SpawnConfigHandler.loadSpawn();
        WarpConfigHandler.loadWarps();
        for(Player all : Bukkit.getOnlinePlayers()) {
            HomeConfigHandler.loadHomePunkte(all);
        }
    }

    public static Essentials getInstance() {
        return instance;
    }
}
