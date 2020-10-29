package de.minersgames.melonigemelone.essentials.utils.model;

import java.util.List;

public class ScoreBoardData {

    private boolean enabled;
    private String displayName;
    private List<String> lines;

    public ScoreBoardData(boolean enabled, String displayName, List<String> lines) {
        this.enabled = enabled;
        this.displayName = displayName;
        this.lines = lines;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
