package dev.toastersrpg

import dev.toastersrpg.command.toast.Commands
import dev.toastersrpg.materials.CraftingMaterials
import dev.toastersrpg.materials.Items
import dev.toastersrpg.worldelements.Mana
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

        RecipeManager.createRecipe(materials.empowermentStone, items.empowermentStone.build(), "empowerment_stone")
        RecipeManager.createRecipe(materials.swordOfHatred, items.swordOfHatred.build(), "sword_of_hatred")

        mana.manaRegenTick(this)




        logger.info("ToastRPG has been enabled!")
    }

    override fun onDisable() {
        Wonderland.shutDown()
        logger.info("ToastRPG has been disabled.")
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        return Wonderland.getCommandManager().executeCommand(sender, label, args)
    }

    companion object {
        @JvmStatic
        private var instance: ToastRpg? = null

        @JvmStatic
        private val items = Items()

        @JvmStatic
        private val materials = CraftingMaterials()

        @JvmStatic
        private val mana = Mana()

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

        @JvmStatic
        fun getMana(): Mana {
            return mana
        }


    }
}
