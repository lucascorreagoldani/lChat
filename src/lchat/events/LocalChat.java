package lchat.events;

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
    public void Local (AsyncPlayerChatEvent e) {
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
                }else {
                    e.setCancelled(true);
                    p.sendMessage("§cAguarde 3 segundos para utilizar o chat novamente.");
                    return;
                }
        }
        if (p.hasPermission("lchat.colors")) {
            e.setFormat("§e[l] " + PermissionEX.getPrefix(p) + "§f" + p.getName() + "§e: " + e.getMessage().replace("&", "§"));
        }else {
            e.setFormat("§e[l] " + PermissionEX.getPrefix(p) + "§f" + p.getName() + "§e: " + e.getMessage());
        }
        if (p.hasPermission("lchat.destaque")) {
            e.setFormat(" \n" + e.getFormat() + "\n ");
        }

    }

}
