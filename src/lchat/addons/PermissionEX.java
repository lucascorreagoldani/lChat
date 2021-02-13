package lchat.addons;

import org.bukkit.entity.Player;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionEX {

    public static String getPrefix(Player p) {
        return PermissionsEx.getUser(p).getPrefix().replaceAll("&", "§");
    }

    public static String getSuflix(Player p) {
        return PermissionsEx.getUser(p).getSuffix().replaceAll("&", "§");
    }

}
