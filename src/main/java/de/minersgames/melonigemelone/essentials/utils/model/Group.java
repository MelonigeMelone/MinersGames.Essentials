package de.minersgames.melonigemelone.essentials.utils.model;

import de.minersgames.melonigemelone.essentials.Essentials;
import de.minersgames.melonigemelone.essentials.utils.handler.GroupHandler;

public class Group {

    private String name;

    private String chatPrefix;
    private String chatSuffix;
    private final String[] chatDisplayNames;
    private String chatFormat;


    private String tabPrefix;
    private String tabSuffix;
    private String tabRank;
    private String[] tabDisplayNames;

    private String sbDislayName;

    public Group(String name, String chatPrefix, String chatSuffix, String[] chatDisplayNames, String chatFormat, String tabPrefix, String tabSuffix,String tabRank, String[] tabDisplayNames, String sbDislayName) {
        this.name = name;
        this.chatPrefix = chatPrefix;
        this.chatSuffix = chatSuffix;
        this.chatDisplayNames = chatDisplayNames;
        this.chatFormat = chatFormat;
        this.tabPrefix = tabPrefix;
        this.tabSuffix = tabSuffix;
        this.tabRank = tabRank;
        this.tabDisplayNames = tabDisplayNames;
        this.sbDislayName = sbDislayName;

        Essentials.groupHandler.scoreboard.registerNewTeam(tabRank);
        Essentials.groupHandler.scoreboard.getTeam(tabRank).setPrefix(tabPrefix);
        Essentials.groupHandler.scoreboard.getTeam(tabRank).setSuffix(tabSuffix);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChatPrefix() {
        return chatPrefix;
    }

    public void setChatPrefix(String chatPrefix) {
        this.chatPrefix = chatPrefix;
    }

    public String getChatSuffix() {
        return chatSuffix;
    }

    public void setChatSuffix(String chatSuffix) {
        this.chatSuffix = chatSuffix;
    }

    public String[] getChatDisplayNameColors() {
        return chatDisplayNames;
    }

    public String getChatFormat() {
        return chatFormat;
    }

    public void setChatFormat(String chatFormat) {
        this.chatFormat = chatFormat;
    }

    public String getTabPrefix() {
        return tabPrefix;
    }

    public void setTabPrefix(String tabPrefix) {
        this.tabPrefix = tabPrefix;
    }

    public String getTabSuffix() {
        return tabSuffix;
    }

    public void setTabSuffix(String tabSuffix) {
        this.tabSuffix = tabSuffix;
    }

    public String[] getTabDisplayNameColors() {
        return tabDisplayNames;
    }

    public void setTabDisplayNameColors(String[] tabDisplayNames) {
        this.tabDisplayNames = tabDisplayNames;
    }

    public String getTabRank() {
        return tabRank;
    }

    public void setTabRank(String tabRank) {
        this.tabRank = tabRank;
    }

    public String getSbDislayName() {
        return sbDislayName;
    }

    public void setSbDislayName(String sbDislayName) {
        this.sbDislayName = sbDislayName;
    }


}
