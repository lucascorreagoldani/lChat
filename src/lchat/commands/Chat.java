package lchat.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class Chat implements CommandExecutor, Listener{

    public static boolean chatt=true;

    @EventHandler
    public void chat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        if (chatt==false && !p.hasPermission("lchat.chat")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (chatt==false && !p.hasPermission("lchat.chat")) {
            e.setCancelled(true);
            p.sendMessage("§cO chat está desabilitado no momento.");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("chat")) {
            if (sender.hasPermission("lchat.chat")) {
                if (args.length == 0) {
                    sender.sendMessage("§cUtilize /chat (on/off/clear).");
                    return true;
                }

                if(args[0].equalsIgnoreCase("on")) {
                    if (chatt==true) {
                        sender.sendMessage("§aO chat já está habilitado.");
                        return true;
                    }
                    if (chatt==false) {
                        chatt=true;
                        sender.sendMessage("§aO chat foi habilitado.");
                        return true;
                    }

                }
                if (args[0].equalsIgnoreCase("off")) {
                    if (chatt==false) {
                        sender.sendMessage("§cO chat já está desabilitado.");
                        return true;
                    }
                    if (chatt==true) {
                        chatt=false;
                        sender.sendMessage("§cO chat foi desabilitado.");
                        return true;
                    }
                }
                if (args[0].equalsIgnoreCase("clear")) {
                    for (int i = 0; i < 300; i++) {
                        Bukkit.broadcastMessage(" ");
                    }
                    sender.sendMessage("§aVocê limpou o chat.");
                    return true;
                }
            } else {
                sender.sendMessage("§cVocê precisa do grupo Admin ou superior para executar este comando.");
                return true;
            }

        }
        return false;

    }

}
