package dev.toastersrpg.inventories

import net.kyori.adventure.text.Component
import org.apache.commons.lang3.mutable.Mutable
import org.bukkit.Material
import org.bukkit.entity.Item
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.ItemStack
import pine.toast.library.Wonderland
import pine.toast.library.inventories.WLInventory
import pine.toast.library.utilities.WonderlandColors

class RaceInv : WLInventory(
    1,
    Component.text("${WonderlandColors.RED.code}Races"),
    true,
    "Races"
) {
    override fun handleClick(event: InventoryClickEvent) {
        val player = event.whoClicked
        val clickedSlot = event.slot

        if (clickedSlot == 0) {
            player.sendMessage(WonderlandColors.GREEN + "Dummy procedure. Will grant a class and abilities to the user in the future.")
        }
    }

    override fun handleClose(event: InventoryCloseEvent) {
        val player = event.player
        player.sendMessage(WonderlandColors.GREEN + "This would then save the option in the future.")
    }

    override fun handleOpen(event: InventoryOpenEvent) {
        // Don't need this right now.
    }

    override fun populateInventory(): Map<Int, ItemStack> {
        val inv: MutableMap<Int, ItemStack> = mutableMapOf()

        inv[0] = ItemStack(Material.STICK)

        return inv
    }
}