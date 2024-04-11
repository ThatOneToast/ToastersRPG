package dev.toastersrpg;

import dev.toastersrpg.command.Command;
import dev.toastersrpg.command.TestCommand;
import dev.toastersrpg.materials.CraftingMaterials;
import dev.toastersrpg.materials.Items;
import dev.toastersrpg.texture.tasks.HealthUpdater;
import org.bukkit.plugin.java.JavaPlugin;
import pine.toast.library.Wonderland;
import pine.toast.library.enchants.EnchantmentManager;
import pine.toast.library.entities.EntityManager;
import pine.toast.library.utilities.*;

@SuppressWarnings("unused")
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

    // Plugin instances
    private static final CraftingMaterials materials = CraftingMaterials.INSTANCE;
    private static final Items items = Items.INSTANCE;


    @Override
    public void onEnable() {
        plugin = this;

        wonderland.initialize(plugin);
        wonderland.getCommandManager().registerCommands(new Commands());

        Command.register(this, new TestCommand());
        HealthUpdater.getInstance().runTaskTimer(this, 0, 1);

        // Registering recipes
        recipeManager.createRecipe(materials.getEmpowermentStone(), items.getEmpowermentStone().build(), "empowerment_stone");
        recipeManager.createRecipe(materials.getSwordOfHatred(), items.getSwordOfHatred().build(), "sword_of_hatred");

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

    public static CraftingMaterials getMaterials() {
        return materials;
    }

    public static Items getItems() {
        return items;
    }
}
