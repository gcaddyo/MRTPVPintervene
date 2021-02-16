package MRTPVPintervene.Listeners;

import MRTPVPintervene.Main;
import static java.lang.Math.abs;
import java.time.Duration;
import java.time.Instant;
import net.minecraft.server.v1_12_R1.DataWatcherObject;
import net.minecraft.server.v1_12_R1.DataWatcherRegistry;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityDamageEventListener implements Listener {

    private final Main plugin;

    EntityDamageEventListener(Main aThis) {
        this.plugin = aThis;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        if (!e.getCause().equals(DamageCause.ENTITY_ATTACK)) {
            return;
        }
        Player damager = (Player) e.getDamager();
        HumanEntity human = (HumanEntity) e.getDamager();
        if (abs(e.getDamage() - damager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()) > 1e-12) {
            infoPlayer(damager);
            e.setCancelled(true);
        }
        //MRTPVPintervene.Utils.Message.send(damager, String.valueOf(e.getDamage()) + " " + String.valueOf(e.getFinalDamage()) + " " + String.valueOf(damager.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue()));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamageByArrow(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Arrow)) {
            return;
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                ((CraftPlayer) (Player) e.getEntity()).getHandle().getDataWatcher().set(new DataWatcherObject(10, DataWatcherRegistry.b), 0);
            }
        }.runTaskLater(this.plugin, 1L);
    }

    private void infoPlayer(Player p) {
        if (plugin.lastReminder.containsKey(p.getUniqueId())) {
            Instant nowIns = Instant.now();
            Instant lastIns = plugin.lastReminder.get(p.getUniqueId());
            if (abs(Duration.between(lastIns, nowIns).getSeconds()) >= 10) {
                plugin.lastReminder.put(p.getUniqueId(), nowIns);
            } else {
                return;
            }
        } else {
            plugin.lastReminder.put(p.getUniqueId(), Instant.now());
        }
        MRTPVPintervene.Utils.Message.send(p, "§cMRTPVPintervene > 您攻击的速度太快了, 请等蓄力完成再攻击.");
    }
}
