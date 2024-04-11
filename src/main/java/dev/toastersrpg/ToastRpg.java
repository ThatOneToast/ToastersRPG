package dev.toastersrpg;

import dev.toastersrpg.command.Command;
import dev.toastersrpg.command.TestCommand;
import dev.toastersrpg.texture.tasks.HealthUpdater;
import org.bukkit.plugin.java.JavaPlugin;
import pine.toast.library.Wonderland;
import pine.toast.library.enchants.EnchantmentManager;
import pine.toast.library.entities.EntityManager;
import pine.toast.library.utilities.CooldownManager;
import pine.toast.library.utilities.RecipeManager;
import pine.toast.library.utilities.ScoreboardManager;
import pine.toast.library.utilities.ScreenManager;

public class ToastRpg extends JavaPlugin {
    private static JavaPlugin plugin;

    // Wonderland instances
    private static final Wonderland wonderland = Wonderland.INSTANCE;
    private static final EnchantmentManager enchantmentManager = EnchantmentManager.INSTANCE;
    private static final CooldownManager cooldownManager = CooldownManager.INSTANCE;
    private static final EntityManager entityManager = EntityManager.INSTANCE;
    private static final ScoreboardManager scoreboardManager = ScoreboardManager.INSTANCE;
    private static final RecipeManager recipeManager = RecipeManager.INSTANCE;
    private static final ScreenManager screenManager = ScreenManager.INSTANCE;


    @Override
    public void onEnable() {
        plugin = this;

        wonderland.initialize(plugin);
        wonderland.getCommandManager().registerCommands(new Commands());

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

    public static Wonderland getWonderland() {
        return wonderland;
    }

    public static EnchantmentManager getEnchantmentManager() {
        return enchantmentManager;
    }

    public static CooldownManager getCooldownManager() {
        return cooldownManager;
    }

    public static EntityManager getEntityManager() {
        return entityManager;
    }

    public static ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public static RecipeManager getRecipeManager() {
        return recipeManager;
    }

    public static ScreenManager getScreenManager() {
        return screenManager;
    }
}
