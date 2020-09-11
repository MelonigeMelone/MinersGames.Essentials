package de.minersgames.melonigemelone.essentials.utils.manager.config.messages;

public enum Messages {
    PREFIX("&8[&eMiner-Games&8] "),
    ONLY_PLAYER("Der Befehl ist nur für Spieler!"),
    NO_PERM(PREFIX.message + "&cDazu hast du keine Rechte!"),
    USE(PREFIX.message + "&cNutze:&7&o %command%"),
    NO_PLAYER_FOUND(PREFIX.message + "&cEs wurde kein Spieler gefunden!"),
    WRONG_VALUE(PREFIX.message + "&cUngültiger Wert!"),
    NO_COMMAND_FOUND(PREFIX.message + "&cDer Befehl &7%command%&c existiert nicht!"),
    UNKNOW_MATERIAL(PREFIX.message + "&cUnbekanntes Material!"),

    GAMEMODE_SET(PREFIX.message + "&7Du bist nun im Gamemode: &e%gamemode%"),
    GAMEMODE_SET_TO_PLAYER(PREFIX.message + "&e%player% &7ist nun im Gamemode: &e%gamemode%"),

    TELEPORTED_TO_PLAYER(PREFIX.message + "&7Du wurdest zu &e%player% &7telepotiert!"),
    TELEPORT_HERE_PLAYER(PREFIX.message + "&e%player% &7 wurde zu dir telepotiert!"),
    TELEPORTED_TO_LOCATION(PREFIX.message + "&7Du wurdest zu &8(%location%&8) &7telepotiert!"),
    TELEPORTED_PLAYER_TO_PLAYER(PREFIX.message + "&7Du hast &e%player% &7zu dir telepotiert!"),
    TELEPORTED_PLAYER_HERE(PREFIX.message + "&7Du wurdest zu &e%player% &7telepotiert!"),
    TELEPORTED_ALL(PREFIX.message + "&7Du hast alle Spieler zu dir telepotiert!"),

    TPA_SEND_TO(PREFIX.message + "&7Du hast eine TPA-Anfrage an &e%player% &7gesendet!"),
    TPA_ACCEPTED(PREFIX.message + "&e%player% &7hat deine TPA-Anfrage &aangenommen!"),
    TPA_DENIED(PREFIX.message + "&e%player% &7hat deine TPA-Anfrage &cabgelehnt!"),
    DENIED_TPA(PREFIX.message + "&7Du hast die TPA-Anfrage &cabgelehnt!"),
    RECEIVED_TPA_FROM(PREFIX.message + "&7Der Spieler &e%player% &7 will sich zu dir telepotieren!"),
    RECEIVED_TPAHERE_FROM(PREFIX.message + "&7Der Spieler &e%player%&7 will dich zu ihm telepotieren!"),
    NO_TPA(PREFIX.message + "&cDu hast keine TPA-Anfrage!"),
    NO_TPA_TO_YOURSELF(PREFIX.message + "&cDu kannst keine TPA-Anfrage an dich selbst senden!"),
    TPA_ALREAYD_SEND(PREFIX.message + "&cDu hast bereits eine TPA-Anfrage gesendet!"),

    FLYSPEED_SET(PREFIX.message + "&7Deine Fluggeschwindigkeit beträgt nun &e%flyspeed%"),
    FLYSPEED_SET_OTHER(PREFIX.message + "&7Du hast die Fluggeschwindigkeit von &e%player% &7auf &e%flyspeed% &7gesetzt!"),

    WALKSPEED_SET(PREFIX.message + "&7Deine Laufgeschwindigkeit beträgt nun &e%walkspeed%"),
    WALKSPEED_SET_OTHER(PREFIX.message + "&7Du die Laufgeschwindigkeit von %eplayer% §7auf &e%walkspeed% &7gesetzt!"),

    HOME_ALREADY_EXISTS(PREFIX.message + "&cEin Homepunkt mit diesem Namen existiert bereits!"),
    HOME_NOT_EXISTS(PREFIX.message + "&cEin Homepunkt mit diesem Namen existiert nicht!"),
    HOME_CREATED(PREFIX.message + "&7Du hast den Homepunkt &e%name% &7erstellt!"),
    HOME_DELETED(PREFIX.message + "&7Du hast den Homepunkt &e%name% &7gelöscht!"),
    HOME_TELEPORT(PREFIX.message + "&7Du wurdest zu dem Homepunkt &e%home% &7telepotiert!"),
    NO_HOMES_SET(PREFIX.message + "&cDu hast keine Homepunkte gesetzt!"),

    WARP_ALREAY_EXISTS(PREFIX.message + "&cEin Warp mit diesem Namen existiert bereits!"),
    WARP_NOT_EXISTS(PREFIX.message + "&cEin Warp mit diesem Namen existiert nicht!"),
    WARP_CREATED(PREFIX.message + "&7Du hast den Warp &e%warp% &7erstellt!"),
    WARP_DELETED(PREFIX.message + "&7Du hast den Warp &e%warp% &7gelöscht!"),
    WARP_TELEPORTED(PREFIX.message + "&7Du wurdest zu dem Warp &e%warp% &7telepotiert!"),
    NO_WARPS_SET(PREFIX.message + "&cEs sind keine Warppunkte gesetzt!"),

    SPAWN_SET(PREFIX.message + "&7Du hast den &eSpawn &7gesetzt!"),
    SPAWN_TELEPORT(PREFIX.message + "&7Du wurdest zum &eSpawn &7telepotiert!"),
    SPAWN_NOT_SET(PREFIX.message + "&cDer Spawn wurde noch nicht gesetzt!"),

    FLY_ACTIVATED(PREFIX.message + "&7Der Flugmodus wurde &aaktiviert!"),
    FLY_DISABLED(PREFIX.message + "&7Der Flugmodus wurde &cdeaktiviert!"),

    FLY_ACTIVATED_OTHER(PREFIX.message + "&7Du hast den Flugmodus für &e%player% &aaktiviert!"),
    FLY_DISABLED_OTHER(PREFIX.message + "&7Du hast den Flugmodus für &e%player% &cdeaktiviert!"),

    GUI_TITLE_HOMES("§eHomes"),
    GUI_HOME_PREFIX("§e"),

    GUI_TITLE_WARPS("§eWarps"),
    GUI_WARPS_PREFIX("§e")

    ;


    String message;

    Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return MessagesConfigHandler.getMessages(this);
    }

    public String getDefaultMessage() {
        return message;
    }
}
