package dev.toastersrpg.race.lib

import dev.toastersrpg.ToastRpg
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import java.io.Serializable

abstract class RaceSkill : Serializable {
    abstract val name: String
    abstract var level: Int
    abstract var manaCost: Double
    abstract var healthCost: Double
    abstract var cooldown: Double
    abstract var target: SkillTarget


    abstract fun use(player: Player)

    fun register() {
        SkillManager.registerSkill(this)
    }

    fun upgrade(level: Int) {
        this.level += level


        val percentageIncrease = level / 100.0
        this.manaCost *= (1 + percentageIncrease)
        this.healthCost.let { this.healthCost = it * (1 + percentageIncrease) }
        this.cooldown -= 0.25
    }

    fun getKey(): NamespacedKey {
        return NamespacedKey(ToastRpg.getPlugin(), name)
    }

}