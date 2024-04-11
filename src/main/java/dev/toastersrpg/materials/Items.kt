package dev.toastersrpg.materials

import dev.toastersrpg.materials.handlers.EmpowermentStoneHandler
import dev.toastersrpg.materials.handlers.SwordOfHatredHandler
import org.bukkit.Material
import org.bukkit.attribute.Attribute
import org.bukkit.attribute.AttributeModifier
import org.bukkit.enchantments.Enchantment
import pine.toast.library.items.ItemBlueprint
import pine.toast.library.utilities.WonderlandColors

object Items {

    private val empowermentStoneLore = mutableListOf(
        "${WonderlandColors.WHITE.code} This stone can grant you special powers!"
    )

    val empowermentStone = ItemBlueprint(
        "${WonderlandColors.RED.code}Empowerment Stone",
        empowermentStoneLore,
        Material.PRISMARINE_CRYSTALS,
        null,
        null,
        null,
        EmpowermentStoneHandler::class.java

    )

    private val swordOfHatredLore = mutableListOf(
        "${WonderlandColors.WHITE.code} This sword can sometimes hurt you!",
        "${WonderlandColors.WHITE.code} With great damage also comes with the pain of bearing it."
    )

    private val swordOfHatredEnchants: MutableMap<Enchantment, Int> = mutableMapOf<Enchantment, Int>().apply {
        put(Enchantment.DAMAGE_ALL, 4)
        put(Enchantment.KNOCKBACK, 3)
        put(Enchantment.DAMAGE_UNDEAD, 7)
    }

    private val swordOfHatredAttr: MutableMap<AttributeModifier, Attribute> = mutableMapOf<AttributeModifier, Attribute>().apply {
        val damageAttr = AttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE.name, 13.5, AttributeModifier.Operation.ADD_NUMBER)
        val knockbackAttr = AttributeModifier(Attribute.GENERIC_MAX_HEALTH.name, -35.0, AttributeModifier.Operation.ADD_NUMBER)

        put(damageAttr, Attribute.GENERIC_ATTACK_DAMAGE)
        put(knockbackAttr, Attribute.GENERIC_MAX_HEALTH)
    }


    val swordOfHatred = ItemBlueprint(
        "${WonderlandColors.DARK_RED.code}Sword of Hatred",
        swordOfHatredLore,
        Material.NETHERITE_SWORD,
        swordOfHatredEnchants,
        swordOfHatredAttr,
        null,
        SwordOfHatredHandler::class.java


    )

}