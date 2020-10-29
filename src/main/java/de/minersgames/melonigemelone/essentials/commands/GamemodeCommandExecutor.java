package de.minersgames.melonigemelone.essentials.commands;

import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GamemodeCommandExecutor implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 1) {
                switch (args[0]) {
                    case "0":
                    case "survival":
                    case "überleben":
                        if(p.hasPermission("essentials.command.gamemode.survival")) {
                            p.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Überleben"));
                            p.setGameMode(GameMode.SURVIVAL);
                        } else {
                            p.sendMessage(Messages.NO_PERM.getMessage());
                        }
                        break;
                    case "1":
                    case "creative":
                    case "Kreativ":
                        if(p.hasPermission("essentials.command.gamemode.creative")) {
                            p.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Kreativ"));
                            p.setGameMode(GameMode.CREATIVE);
                        } else {
                            p.sendMessage(Messages.NO_PERM.getMessage());
                        }
                        break;
                    case "2":
                    case "adventure":
                    case "Abenteuer":
                        if(p.hasPermission("essentials.command.gamemode.adventure")) {
                            p.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Abenteuer"));
                            p.setGameMode(GameMode.ADVENTURE);
                        } else {
                            p.sendMessage(Messages.NO_PERM.getMessage());
                        }
                        break;
                    case "3":
                    case "spectator":
                    case "Zuschauer":
                        if(p.hasPermission("essentials.command.gamemode.spectator")) {
                            p.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Zuschauer"));
                            p.setGameMode(GameMode.SPECTATOR);
                        } else {
                            p.sendMessage(Messages.NO_PERM.getMessage());
                        }
                        break;
                    default:
                        p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/" + label + " (0|1|2|3) [Spieler]"));
                        break;

                }
            } else if(args.length == 2) {
                Player t = Bukkit.getPlayerExact(args[1]);
                if(t != null) {
                    String message = Messages.GAMEMODE_SET_TO_PLAYER.getMessage().replaceAll("%player%", t.getDisplayName());
                    switch (args[0]) {
                        case "0":
                        case "survival":
                        case "überleben":
                            if(p.hasPermission("essentials.command.gamemode.survival.other")) {
                                p.sendMessage(message.replaceAll("%gamemode%", "Überleben"));
                                t.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Überleben"));
                                t.setGameMode(GameMode.SURVIVAL);
                            } else {
                                p.sendMessage(Messages.NO_PERM.getMessage());
                            }
                            break;
                        case "1":
                        case "creative":
                        case "Kreativ":
                            if(p.hasPermission("essentials.command.gamemode.creative.other")) {
                                p.sendMessage(message.replaceAll("%gamemode%", "Kreativ"));
                                t.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Kreativ"));
                                t.setGameMode(GameMode.CREATIVE);
                            } else {
                                p.sendMessage(Messages.NO_PERM.getMessage());
                            }
                            break;
                        case "2":
                        case "adventure":
                        case "Abenteuer":
                            if(p.hasPermission("essentials.command.gamemode.adventure.other")) {
                                p.sendMessage(message.replaceAll("%gamemode%", "Abenteuert"));
                                t.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Abenteuer"));
                                t.setGameMode(GameMode.ADVENTURE);
                            } else {
                                p.sendMessage(Messages.NO_PERM.getMessage());
                            }
                            break;
                        case "3":
                        case "spectator":
                        case "Zuschauer":
                            if(p.hasPermission("essentials.command.gamemode.spectator.other")) {
                                p.sendMessage(message.replaceAll("%gamemode%", "Zuschauer"));
                                t.sendMessage(Messages.GAMEMODE_SET.getMessage().replaceAll("%gamemode%", "Zuschauer"));
                                t.setGameMode(GameMode.SPECTATOR);
                            } else {
                                p.sendMessage(Messages.NO_PERM.getMessage());
                            }
                            break;
                        default:
                            p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/" + label + " (0|1|2|3) [Spieler]"));
                            break;
                    }
                } else {
                    p.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                }
            } else {
                p.sendMessage(Messages.USE.getMessage().replaceAll("%command%", "/" + label + " (0|1|2|3) [Spieler]"));
            }
        } else {
            sender.sendMessage(Messages.ONLY_PLAYER.getMessage());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            if (args.length == 1) {
                return Arrays.asList("0", "1", "2", "3", "survival", "creative", "spectator", "adventure").stream().filter(s -> s.startsWith(args[0].toLowerCase())).collect(Collectors.toList());
            }
        }
        return null;

    }


}
