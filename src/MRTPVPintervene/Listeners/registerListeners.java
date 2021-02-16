package MRTPVPintervene.Listeners;

import MRTPVPintervene.Main;


public class registerListeners {
    
    Main plugin;
    
    public registerListeners(Main aThis) {
        this.plugin=aThis;
        this.plugin.getServer().getPluginManager().registerEvents(new PlayerQuitListener(this.plugin), this.plugin);
        this.plugin.getServer().getPluginManager().registerEvents(new EntityDamageEventListener(this.plugin), this.plugin);
    }
    
}