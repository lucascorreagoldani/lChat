package lchat.events;

import lchat.addons.PermissionEX;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TellChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("tell")) {
                Player p = (Player) sender;
                if (args.length < 2) {
                    p.sendMessage("§cUtilize /tell (player) (mensagem).");
                    return false;
                }
                if (args.length > 0) {
                    Player t = Bukkit.getPlayerExact(args[0]);
                    if (t == null) {
                        p.sendMessage("§cEste usuário não existe.");
                        return false;
                    }
                    if (t.isOnline() == false) {
                        p.sendMessage("§Este jogador está offline.");
                        return false;
                    }
                    if (t.getName() == p.getName()) {
                        p.sendMessage("§cVocê não pode conversar consigo mesmo.");
                        return false;
                    }
                    String msg = "";
                    for (int i = 1; i < args.length; i++) {
                        msg = msg + " " + args[i];
                    }
                    if (sender.hasPermission("lchat.colors")) {
                        t.sendMessage("§8Mensagem de " + PermissionEX.getPrefix(p) + "" + p.getName() + "§8:§6" + msg.replace("&", "§"));
                        p.sendMessage("§8Mensagem para " + PermissionEX.getPrefix(t) + "" + t.getName() + "§8:§6" + msg.replace("&", "§"));
                    } else {
                        t.sendMessage("§8Mensagem de " + PermissionEX.getPrefix(p) + "" + p.getName() + "§8:§6" + msg);
                        p.sendMessage("§8Mensagem para " + PermissionEX.getPrefix(t) + "" + t.getName() + "§8:§6" + msg);
                    }

                }

            }

        }
        return false;
    }

}
