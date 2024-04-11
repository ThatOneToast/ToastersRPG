package dev.toastersrpg.materials

import dev.toastersrpg.ToastRpg
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import pine.toast.library.utilities.RecipeShape

class CraftingMaterials {

    val empowermentStone = RecipeShape(
        ItemStack(Material.OBSIDIAN), ItemStack(Material.NETHER_STAR), ItemStack(Material.OBSIDIAN),
        ItemStack(Material.NETHER_STAR), ItemStack(Material.NETHERITE_INGOT), ItemStack(Material.NETHER_STAR),
        ItemStack(Material.OBSIDIAN), ItemStack(Material.NETHER_STAR), ItemStack(Material.OBSIDIAN)
    )

    val swordOfHatred = RecipeShape(
        null, ItemStack(ToastRpg.getItems().empowermentStone.build()), null,
        null, ItemStack(ToastRpg.getItems().empowermentStone.build()), null,
        null, ItemStack(Material.NETHERITE_BLOCK), null
    )
}