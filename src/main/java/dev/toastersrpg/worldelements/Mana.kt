package dev.toastersrpg.worldelements

import dev.toastersrpg.Keys
import dev.toastersrpg.pdc.AdapterManager
import dev.toastersrpg.races.RaceInfo
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

class Mana {
    private val mana: MutableMap<UUID, Double> = mutableMapOf()
    private val manaRegen: MutableMap<UUID, Double> = mutableMapOf()

    fun setup() {
        val task = object : BukkitRunnable() {
            override fun run() {
                for (player in Bukkit.getOnlinePlayers()) {
                    val raceInfo: RaceInfo = player.persistentDataContainer.get(Keys.RACE_INFO, AdapterManager.raceInfo)
                        ?: continue
                    val manaRegen: Double = raceInfo.manaRegeneration
                    val currentMana: Double = mana[player.uniqueId] ?: 0.0
                    val maxMana: Double = raceInfo.mana
                    val newMana: Double = currentMana + manaRegen
                    if (newMana <= maxMana) {
                        mana[player.uniqueId] = newMana

                    }
                }
            }
        }

    }

    fun setMana(player: Player, amount: Double) {
        mana[player.uniqueId] = amount
    }

    fun addMana(player: Player, amount: Double) {
        mana[player.uniqueId] = (mana[player.uniqueId] ?: 0.0) + amount
    }

    fun removeMana(player: Player, amount: Double) {
        if (canSpendMana(player, amount))
            mana[player.uniqueId] = (mana[player.uniqueId] ?: 0.0) - amount
        else throw IllegalStateException("Player does not have enough mana")
    }

    fun getMana(player: Player): Double {
        return mana[player.uniqueId] ?: 0.0
    }

    private fun canSpendMana(player: Player, amount: Double): Boolean {
        val playerMana = getMana(player)
        val newMana = playerMana - amount

        return if (playerMana >= newMana) true else false

    }


    fun setPermaMana(player: Player, amount: Double) {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        raceInfo.mana = amount

        pdc.set(Keys.RACE_INFO, AdapterManager.raceInfo, raceInfo)

    }

    fun setPermaRegenerate(player: Player, amount: Double) {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        raceInfo.manaRegeneration = amount

        pdc.set(Keys.RACE_INFO, AdapterManager.raceInfo, raceInfo)
    }

    fun addPermaMana(player: Player, amount: Double) {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        raceInfo.mana += amount

        pdc.set(Keys.RACE_INFO, AdapterManager.raceInfo, raceInfo)
    }

    fun addPermaRegen(player: Player, amount: Double) {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        raceInfo.manaRegeneration += amount

        pdc.set(Keys.RACE_INFO, AdapterManager.raceInfo, raceInfo)
    }

    fun removePermaMana(player: Player, amount: Double) {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        raceInfo.mana -= amount

        pdc.set(Keys.RACE_INFO, AdapterManager.raceInfo, raceInfo)
    }

    fun removePermaRegen(player: Player, amount: Double) {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        raceInfo.manaRegeneration -= amount

        pdc.set(Keys.RACE_INFO, AdapterManager.raceInfo, raceInfo)
    }

    fun getPermaMana(player: Player): Double {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        return raceInfo.mana
    }

    fun getPermaRegen(player: Player): Double {
        val pdc = player.persistentDataContainer

        val raceInfo: RaceInfo = pdc.get(Keys.RACE_INFO, AdapterManager.raceInfo)
            ?: throw IllegalStateException("This player has no race")

        return raceInfo.manaRegeneration
    }
}