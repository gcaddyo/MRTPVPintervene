package MRTPVPintervene.Listeners;

import MRTPVPintervene.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final Main plugin;

    PlayerQuitListener(Main aThis) {
        this.plugin = aThis;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onQuit(PlayerQuitEvent e) {
        if (plugin.lastReminder.containsKey(e.getPlayer().getUniqueId())) {
            plugin.lastReminder.remove(e.getPlayer().getUniqueId());
        }
    }

}
