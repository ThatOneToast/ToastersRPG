package dev.toastersrpg.materials.handlers

import dev.toastersrpg.ToastRpg
import org.bukkit.Location
import org.bukkit.NamespacedKey
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import pine.toast.library.events.PlayerLeftClickEvent
import pine.toast.library.events.PlayerRightClickEvent
import pine.toast.library.items.ItemHandler
import pine.toast.library.utilities.CooldownManager
import pine.toast.library.utilities.WonderlandColors
import java.util.*

class SwordOfHatredHandler : ItemHandler {
    override fun onPlayerLeftClick(event: PlayerLeftClickEvent) {

        if (CooldownManager.isPlayerOnCooldown(event.getPlayer(), NamespacedKey(ToastRpg.getPlugin(), "swordOfHatred_potions"))) {
            event.getPlayer().sendMessage("${WonderlandColors.RED}You can only use this power every 45 seconds!")
            return
        }

        CooldownManager.applyPlayerCooldown(event.getPlayer(), NamespacedKey(ToastRpg.getPlugin(), "swordOfHatred_potions"), 45)
        val effects = arrayOf(
            PotionEffectType.REGENERATION,
            PotionEffectType.SPEED,
            PotionEffectType.JUMP,
            PotionEffectType.DAMAGE_RESISTANCE,
            PotionEffectType.FIRE_RESISTANCE,
        )

        val randomEffect = effects[Random().nextInt(effects.size)]

        val duration = 20 * 15
        val amplifier = 1

        val potionEffect = PotionEffect(randomEffect, duration, amplifier)
        event.getPlayer().addPotionEffect(potionEffect)


    }

    override fun onPlayerRightClick(event: PlayerRightClickEvent) {

        if (CooldownManager.isPlayerOnCooldown(event.getPlayer(), NamespacedKey(ToastRpg.getPlugin(), "swordOfHatred_teleport"))) {
            event.getPlayer().sendMessage("${WonderlandColors.RED}You can only use this power every 120 seconds!")
            return
        }

        CooldownManager.applyPlayerCooldown(event.getPlayer(), NamespacedKey(ToastRpg.getPlugin(), "swordOfHatred_teleport"), 120)

        fun isSafeLocation(location: Location): Boolean {
            val block = location.block
            val below = block.location.subtract(0.0, 1.0, 0.0).block
            val above = block.location.add(0.0, 1.0, 0.0).block

            return !block.type.isSolid && !below.type.isSolid && !above.type.isSolid
        }

        val player = event.getPlayer()
        val world = player.world
        val random = Random()

        var newLocation: Location

        do {
            // Generate random offsets within a 20 block radius
            val dx = random.nextInt(41) - 20 // Range: [-20, 20]
            val dz = random.nextInt(41) - 20 // Range: [-20, 20]

            // Keep the y-coordinate the same to only search horizontally
            newLocation = player.location.clone().add(dx.toDouble(), 0.0, dz.toDouble())

            // Find the highest non-air block at the x and z coordinates (plus 1 to put the player above the ground)
            newLocation.y = world.getHighestBlockYAt(newLocation).toDouble() + 1

        } while (!isSafeLocation(newLocation))

        player.teleport(newLocation)


    }
}