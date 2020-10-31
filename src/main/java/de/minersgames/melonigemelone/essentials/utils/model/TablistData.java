package de.minersgames.melonigemelone.essentials.utils.model;

public class TablistData {

    private String header;
    private String footer;

    public TablistData(String header, String footer) {
        this.header = header;
        this.footer = footer;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }
}
