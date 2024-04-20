package dev.toastersrpg.worldelements

import dev.toastersrpg.Keys
import dev.toastersrpg.pdc.AdptManager
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*


class Mana {
    val manaPool: MutableMap<UUID, Double> = mutableMapOf()


    fun addMana(playerId: UUID, amount: Double) {
        val currentMana = manaPool.getOrDefault(playerId, 0.0)
        if (currentMana + amount <= getMaxMana(playerId)) {
            manaPool[playerId] = currentMana + amount
        } else {
            manaPool[playerId] = getMaxMana(playerId)
        }
    }

    fun removeMana(playerId: UUID, amount: Double) {
        val currentMana: Double = manaPool.getOrDefault(playerId, 0.0)
        manaPool[playerId] = currentMana - amount
    }

    fun getMana(playerId: UUID): Double {
        return manaPool.getOrDefault(playerId, 0.0)
    }

    fun hasEnoughMana(playerId: UUID, amount: Double): Boolean {
        return manaPool.getOrDefault(playerId, 0.0) >= amount
    }

    fun clearMana(playerId: UUID) {
        manaPool.remove(playerId)
    }

    fun getRegenMana(playerId: UUID): Double {
        val player = Bukkit.getPlayer(playerId) ?: throw IllegalStateException("Player not found")
        return player.persistentDataContainer.get(Keys.MANA, PersistentDataType.DOUBLE) ?: 0.0
    }

    fun getMaxMana(playerId: UUID): Double {
        val player = Bukkit.getPlayer(playerId) ?: throw IllegalStateException("Player not found")
        val raceInfo = player.persistentDataContainer.get(Keys.RACE_INFO, AdptManager.raceInfoAdapter) ?: throw IllegalArgumentException("No race Info Found")
        return raceInfo.mana
    }

    fun manaRegenTick(plugin: Plugin) {
        // create a new task timer that adds mana based on the regen speed to their current

        object : BukkitRunnable() {
            override fun run() {
                for (player: Player in Bukkit.getServer().onlinePlayers) {
                    val regen = getRegenMana(player.uniqueId)
                    addMana(player.uniqueId, regen)

                }
            }
        }.runTaskTimer(plugin, 0, 20)
    }


}