package lchat.events;

import lchat.Main;
import lchat.addons.PermissionEX;
import lchat.commands.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;

public class GlobalChat implements CommandExecutor {

    public static ArrayList<Player> delay_global;
    static {
        delay_global = new ArrayList<Player>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            {
                if (args.length > 0)
                if (!delay_global.contains(p)) {
                if (!p.hasPermission("lchat.delay"))
                    delay_global.add(p);
                    Bukkit.getScheduler().scheduleAsyncDelayedTask(Main.getInstance(), new Runnable() {
                        @Override
                        public void run() {
                            delay_global.remove(p);
                        }
                    }, 5 * 20l);
                }else {
                    p.sendMessage("§cAguarde 5 segundos para utilizar o chat novamente.");
                    return false;
                }
            }
            if (cmd.getName().equalsIgnoreCase("g")) {
                if (args.length == 0) {
                    p.sendMessage("§cUtilize /g (mensagem).");
                    return false;
                }
                if (Chat.chatt == false && !p.hasPermission("lchat.chat")) {
                    p.sendMessage("§cO chat está desabilitado no momento.");
                    return false;
                }
                String msg = "";
                for (int i = 0; i < args.length; i++) {
                    msg = msg + " " + args[i];
                }
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (p.hasPermission("lchat.colors")) {
                        all.sendMessage("§7[g] " + PermissionEX.getPrefix(p) + "§f" + p.getName() + "§f:" + msg.replace("&", "§"));
                    } else {
                        all.sendMessage("§7[g] " + PermissionEX.getPrefix(p) + "§f" + p.getName() + "§f:§7" + msg);
                    }

                }

            }

        }
        return false;
    }

}