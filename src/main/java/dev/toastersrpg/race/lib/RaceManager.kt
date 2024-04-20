package dev.toastersrpg.race.lib

import dev.toastersrpg.Keys
import dev.toastersrpg.pdc.AdptManager
import net.kyori.adventure.text.Component
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import pine.toast.library.utilities.WonderlandColors

object RaceManager {

    fun applyRaceToPlayer(player: Player, raceInfo: RaceInfo) {
        val pdc = player.persistentDataContainer

        val health = raceInfo.health
        val mana = raceInfo.mana
        val speed = raceInfo.speed
        val damage = raceInfo.damage
        val defense = raceInfo.defense

        pdc.set(Keys.MANA, PersistentDataType.DOUBLE, mana)
        pdc.set(Keys.RACE_INFO, AdptManager.raceInfoAdapter, raceInfo)

        if (speed != 0.0) {

            player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue = speed

        }

        player.healthScale = 40.0

        player.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue = health
        player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)?.baseValue = damage
        player.getAttribute(Attribute.GENERIC_ARMOR)?.baseValue = defense

        player.sendMessage(Component.text("${WonderlandColors.GREEN.code}Applied ${raceInfo.race}"))
    }

    fun addSkill(player: Player, skill: RaceSkill) {
        val pdc = player.persistentDataContainer
        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdptManager.raceInfoAdapter) ?: throw IllegalArgumentException("No race info")

        raceInfo.addSkill(skill)
    }

    fun removeSkill(player: Player, skillName: String) {
        val pdc = player.persistentDataContainer
        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdptManager.raceInfoAdapter) ?: throw IllegalArgumentException("No race info")

        raceInfo.removeSkill(skillName)
    }

}