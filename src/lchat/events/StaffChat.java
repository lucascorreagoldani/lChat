package lchat.events;

import lchat.addons.PermissionEX;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("s")) {
                Player p = (Player) sender;
                if (!p.hasPermission("lchat.staff")) {
                    p.sendMessage("§cVocê precisa do grupo Ajudante ou superior para executar este comando.");
                    return false;
                }
                if (args.length == 0) {
                    p.sendMessage("§cUtilize /s (mensagem).");
                    return false;
                }
                String msg = "";
                for (int i = 0; i < args.length; i++) {
                    msg = msg + " " + args[i];
                }
                for (Player staff : Bukkit.getOnlinePlayers()) {
                    if (staff.hasPermission("lchat.staff")) {
                        staff.sendMessage("§d[Staff] " + PermissionEX.getPrefix(p) + "" + p.getName() + "§f:" + msg.replace("&", "§"));
                    }

                }

            }

        }
        return false;

    }

}



