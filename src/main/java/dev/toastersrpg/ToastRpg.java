package dev.toastersrpg;

import dev.toastersrpg.command.Command;
import dev.toastersrpg.command.TestCommand;
import dev.toastersrpg.texture.tasks.HealthUpdater;
import org.bukkit.plugin.java.JavaPlugin;

public class ToastRpg extends JavaPlugin {
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        Command.register(this, new TestCommand());
        HealthUpdater.getInstance().runTaskTimer(this, 0, 1);
    }

    @Override
    public void onDisable() {
        HealthUpdater.getInstance().cancel();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
