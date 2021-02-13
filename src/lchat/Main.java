package lchat;

import lchat.commands.Chat;
import lchat.events.GlobalChat;
import lchat.events.LocalChat;
import lchat.events.StaffChat;
import lchat.events.TellChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new LocalChat(), this);
        Bukkit.getPluginManager().registerEvents(new Chat(), this);
        getCommand("chat").setExecutor(new Chat());
        getCommand("tell").setExecutor(new TellChat());
        getCommand("g").setExecutor(new GlobalChat());
        getCommand("s").setExecutor(new StaffChat());

    }

    @Override
    public void onDisable() {

    }
}
