package dev.toastersrpg.races

import dev.toastersrpg.Keys
import dev.toastersrpg.pdc.AdapterManager
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player

object RaceManager {

    /**
     * This applies the race to the palyer, granting all attributes
     * This also sets the health scale to the player to 40.0 ( 2 rows of hearts )
     * @param player
     * @param race
     */
    fun applyRace(player: Player, race: RaceInfo) {
        player.persistentDataContainer.set(
            Keys.RACE_INFO,
            AdapterManager.raceInfo,
            race
        )


        player.health = race.startingHealth
        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = race.startingHealth
        player.healthScale = 40.0
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue = race.damage
        player.getAttribute(Attribute.GENERIC_ARMOR)!!.baseValue = race.defense

        if (race.speed != 0.0)
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.baseValue = race.speed

    }

    /**
     * This function applies the changes from Damage, health, armor, and movement speed.
     * @param player
     * @throws NullPointerException if there is no race
     */
    fun updateRace(player: Player) {
        val race = player.persistentDataContainer.get(
            Keys.RACE_INFO,
            AdapterManager.raceInfo
        ) ?: throw NullPointerException("Race info not set")

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue = race.startingHealth
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)!!.baseValue = race.damage
        player.getAttribute(Attribute.GENERIC_ARMOR)!!.baseValue = race.defense

        if (race.speed != 0.0)
            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)!!.baseValue = race.speed

    }

}