package lchat.events;

import com.google.common.collect.Lists;
import lchat.Main;
import lchat.addons.PermissionEX;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import java.util.ArrayList;

public class LocalChat implements Listener {

    public static ArrayList<Player> delay_local;

    static {
        delay_local = new ArrayList<Player>();
    }


    @EventHandler
    public void Local(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        {
            if (!delay_local.contains(p)) {
                if (!p.hasPermission("lchat.delay"))
                    delay_local.add(p);
                Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
                    public void run() {
                        delay_local.remove(p);
                    }
                }, 3 * 20l);
            } else {
                e.setCancelled(true);
                p.sendMessage("§cAguarde 3 segundos para utilizar o chat novamente.");
                return;
            }
            String msg = e.getMessage();
            if (p.hasPermission("lchat.colors"))
                msg = msg.replaceAll("&", "§");
            e.setFormat("§e[l] " + PermissionEX.getPrefix(p) + "§f" + p.getName() + "§f:§e " + msg);
        }
        if (p.hasPermission("lchat.destaque")) {
            e.setFormat(" \n" + e.getFormat() + "\n ");
        }
        ArrayList<Player> t = Lists.newArrayList();
        for (Entity entity : p.getNearbyEntities(100, 100, 100)) {
            if (!(entity instanceof Player)) {
                continue;
            }
            t.add((Player) entity);
            t.add(p);
        }
        e.getRecipients().clear();
        e.getRecipients().addAll(t);
        if (t.isEmpty()) {
            p.sendMessage("§eNão há ninguém perto para te escutar.");
        }
    }

}
