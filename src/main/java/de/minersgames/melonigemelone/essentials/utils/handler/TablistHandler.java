package de.minersgames.melonigemelone.essentials.utils.handler;

import de.minersgames.melonigemelone.essentials.utils.model.TablistData;
import org.bukkit.entity.Player;

public class TablistHandler {

    public TablistData tablistData;

    public void sendTablist(Player p) {
        p.setPlayerListHeaderFooter(tablistData.getHeader(), tablistData.getFooter());

    }

}
