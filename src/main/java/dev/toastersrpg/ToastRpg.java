package dev.toastersrpg;

import org.bukkit.plugin.java.JavaPlugin;

public class ToastRpg extends JavaPlugin {
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
