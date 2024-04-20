package dev.toastersrpg.race.lib

import org.bukkit.entity.Player
import pine.toast.library.utilities.ItemTarget
import java.io.Serializable

abstract class RaceSkill : Serializable {
    abstract val name: String
    abstract var level: Int
    abstract var manaCost: Double
    abstract var healthCost: Double
    abstract var activations: List<ItemTarget>


    abstract fun use(player: Player)


    fun upgrade(level: Int) {
        this.level += level


        val percentageIncrease = level / 100.0
        this.manaCost *= (1 + percentageIncrease)
        this.healthCost.let { this.healthCost = it * (1 + percentageIncrease) }
    }

}