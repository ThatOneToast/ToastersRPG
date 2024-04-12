package dev.toastersrpg

import dev.toastersrpg.inventories.RaceInv
import dev.toastersrpg.materials.CraftingMaterials
import dev.toastersrpg.materials.Items
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import pine.toast.library.Wonderland
import pine.toast.library.utilities.RecipeManager

class ToastRpg : JavaPlugin() {

    override fun onEnable() {
        instance = this

        Wonderland.initialize(this)
        Wonderland.getCommandManager().registerCommands(Commands())
        Wonderland.getInvManager().registerInventory(RaceInv())

        RecipeManager.createRecipe(materials.empowermentStone, items.empowermentStone.build(), "empowerment_stone")
        RecipeManager.createRecipe(materials.swordOfHatred, items.swordOfHatred.build(), "sword_of_hatred")

        RecipeManager.registerRecipes()
        logger.info("ToastRPG has been enabled!")
    }

    override fun onDisable() {
        logger.info("ToastRPG has been disabled.")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        return Wonderland.getCommandManager().executeCommand(sender, label, args)
    }

    companion object {
        @JvmStatic
        private var instance: ToastRpg? = null

        @JvmStatic
        private var items = Items()

        @JvmStatic
        private var materials = CraftingMaterials()

        @JvmStatic
        fun getPlugin(): ToastRpg {
            return instance ?: throw IllegalStateException("Plugin not initialized")
        }

        @JvmStatic
        fun getItems(): Items {
            return items
        }

        @JvmStatic
        fun getMaterials(): CraftingMaterials {
            return materials
        }

    }
}
