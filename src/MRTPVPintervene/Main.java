package MRTPVPintervene;

import MRTPVPintervene.Listeners.registerListeners;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public Map<UUID, Instant> lastReminder;
    
    registerListeners listeners;

    @Override
    public void onEnable() {
        this.lastReminder = new HashMap();
        this.listeners = new registerListeners(this);
        MRTPVPintervene.Utils.Message.sendToConsole("§aMRTPVPintervene > 正常启动.");
    }

    @Override
    public void onDisable() {
    }

}
