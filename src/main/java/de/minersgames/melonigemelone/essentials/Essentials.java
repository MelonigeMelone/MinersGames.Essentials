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
import de.minersgames.melonigemelone.essentials.listener.ChatListener;
import de.minersgames.melonigemelone.essentials.listener.InventoryClickListener;
import de.minersgames.melonigemelone.essentials.listener.JoinQuitListener;
import de.minersgames.melonigemelone.essentials.listener.UnknowCommandListener;
import de.minersgames.melonigemelone.essentials.utils.handler.GroupHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.PlayerHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.ScoreBoardHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.TpaHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.config.HomeConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.config.ScoreBoardConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.config.SpawnConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.config.WarpConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.config.groups.RankConfigHandler;
import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.MessagesConfigHandler;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Essentials extends JavaPlugin {

    public static Essentials instance;
    public static Permission perms;

    public static MessagesConfigHandler messagesConfigHandler;
    public static RankConfigHandler rankConfigHandler;
    public static HomeConfigHandler homeConfigHandler;
    public static ScoreBoardConfigHandler scoreBoardConfigHandler;
    public static SpawnConfigHandler spawnConfigHandler;
    public static WarpConfigHandler warpConfigHandler;

    public static GroupHandler groupHandler;
    public static PlayerHandler playerHandler;
    public static ScoreBoardHandler scoreBoardHandler;
    public static TpaHandler tpaHandler;

    @Override
    public void onEnable() {
        instance = this;

        setupPermissions();

        groupHandler = new GroupHandler();
        playerHandler = new PlayerHandler();

        messagesConfigHandler = new MessagesConfigHandler();
        rankConfigHandler = new RankConfigHandler();
        homeConfigHandler = new HomeConfigHandler();
        scoreBoardConfigHandler = new ScoreBoardConfigHandler();
        spawnConfigHandler = new SpawnConfigHandler();
        warpConfigHandler = new WarpConfigHandler();

        scoreBoardHandler = new ScoreBoardHandler();
        tpaHandler = new TpaHandler();

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
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new UnknowCommandListener(), this);
    }

    private void setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
    }


    public static Essentials getInstance() {
        return instance;
    }
}
