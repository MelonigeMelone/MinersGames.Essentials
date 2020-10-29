package de.minersgames.melonigemelone.essentials.utils.handler;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.config.messages.Messages;
import de.minersgames.melonigemelone.essentials.utils.model.TpaModel;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class TpaHandler {

    public HashMap<UUID, TpaModel> tpaTargetCache = new HashMap<>();
    public HashMap<UUID, TpaModel> tpaApplicantCache = new HashMap<>();

    public Essentials essentials = Essentials.getInstance();

    public void createTpa(Player applicant, Player target, TpaModel.Variant variant) {

        if(!tpaApplicantCache.keySet().contains(applicant.getUniqueId())) {
            TpaModel tpaModel = new TpaModel(applicant.getUniqueId(), target.getUniqueId(), variant);

            tpaTargetCache.put(target.getUniqueId(), tpaModel);
            tpaApplicantCache.put(applicant.getUniqueId(), tpaModel);

            applicant.sendMessage(Messages.TPA_SEND_TO.getMessage().replaceAll("%player%", target.getDisplayName()));

            target.spigot().sendMessage(create(applicant, target, variant));

            Bukkit.getScheduler().runTaskLaterAsynchronously(essentials, () -> {
                if (tpaTargetCache.keySet().contains(target.getUniqueId()) && tpaTargetCache.get(target.getUniqueId()).getApplicantUUID().equals(applicant.getUniqueId())) {
                    tpaTargetCache.remove(target.getUniqueId());
                    tpaApplicantCache.remove(applicant.getUniqueId());
                }
            }, 200);
        } else {
            applicant.sendMessage(Messages.TPA_ALREAYD_SEND.getMessage());
        }

    }

    public void accept(Player target) {
        if(tpaTargetCache.keySet().contains(target.getUniqueId())) {
            TpaModel tpaModel = tpaTargetCache.get(target.getUniqueId());
            Player applicant = Bukkit.getPlayer(tpaModel.getApplicantUUID());

            if(applicant != null) {
                if (applicant.isOnline()) {

                    applicant.sendMessage(Messages.TPA_ACCEPTED.getMessage().replaceAll("%player%", target.getDisplayName()));
                    applicant.playSound(applicant.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1 );

                    if (tpaModel.getVariant().equals(TpaModel.Variant.TPA)) {
                        applicant.teleport(target);
                        target.sendMessage(Messages.TELEPORT_HERE_PLAYER.getMessage().replaceAll("%player%", applicant.getDisplayName()));
                    } else if (tpaModel.getVariant().equals(TpaModel.Variant.TPAHERE)) {
                        target.teleport(applicant);
                        target.sendMessage(Messages.TELEPORTED_TO_PLAYER.getMessage().replaceAll("%player%", applicant.getDisplayName()));
                    }

                    tpaTargetCache.remove(target.getUniqueId());
                    tpaApplicantCache.remove(tpaModel.getApplicantUUID());
                } else {
                    target.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                    tpaTargetCache.remove(target.getUniqueId());
                    tpaApplicantCache.remove(tpaModel.getApplicantUUID());
                }
            } else {
                target.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                tpaTargetCache.remove(target.getUniqueId());
                tpaApplicantCache.remove(tpaModel.getApplicantUUID());
            }
        } else {
            target.sendMessage(Messages.NO_TPA.getMessage());
        }
    }

    public void deny(Player target) {
        if(tpaTargetCache.keySet().contains(target.getUniqueId())) {
            TpaModel tpaModel = tpaTargetCache.get(target.getUniqueId());
            Player applicant = Bukkit.getPlayer(tpaModel.getApplicantUUID());

            if(applicant != null) {
                if (applicant.isOnline()) {

                    applicant.sendMessage(Messages.TPA_DENIED.getMessage().replaceAll("%player%", target.getDisplayName()));
                    target.sendMessage(Messages.DENIED_TPA.getMessage());

                    tpaTargetCache.remove(target.getUniqueId());
                    tpaApplicantCache.remove(tpaModel.getApplicantUUID());
                } else {
                    target.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                    tpaTargetCache.remove(target.getUniqueId());
                    tpaApplicantCache.remove(tpaModel.getApplicantUUID());
                }
            } else {
                target.sendMessage(Messages.NO_PLAYER_FOUND.getMessage());
                tpaTargetCache.remove(target.getUniqueId());
                tpaApplicantCache.remove(tpaModel.getApplicantUUID());
            }
        } else {
            target.sendMessage(Messages.NO_TPA.getMessage());
        }
    }

    public TextComponent create(Player applicant, Player target, TpaModel.Variant variant) {

        TextComponent aceept = new TextComponent();
        aceept.setText("Akzeptieren");
        aceept.setColor(ChatColor.GREEN);
        aceept.setBold(true);
        aceept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

        TextComponent deny = new TextComponent();
        deny.setText("Ablehnen");
        deny.setColor(ChatColor.RED);
        deny.setBold(true);
        deny.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));

        TextComponent message = new TextComponent();
        if(variant.equals(TpaModel.Variant.TPA)) {
            message.setText(Messages.RECEIVED_TPA_FROM.getMessage().replaceAll("%player%", applicant.getDisplayName()));
        } else  if(variant.equals(TpaModel.Variant.TPAHERE)) {
            message.setText(Messages.RECEIVED_TPAHERE_FROM.getMessage().replaceAll("%player%", applicant.getDisplayName()));
        }
        message.setColor(ChatColor.GRAY);
        message.addExtra(" §8[");
        message.addExtra(aceept);
        message.addExtra("§8] §8[");
        message.addExtra(deny);
        message.addExtra("§8]");

        return message;

    }




}
