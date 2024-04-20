package dev.toastersrpg.race

import dev.toastersrpg.race.lib.RaceSkill
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import pine.toast.library.utilities.ItemTarget

class FireballSkill : RaceSkill() {
    override val name: String
        get() = "Fireball"
    override var level: Int
        get() = 1
        set(value) {}
    override var manaCost: Double
        get() = 35.0
        set(value) {}
    override var healthCost: Double
        get() = 0.0
        set(value) {}
    override var activations: List<ItemTarget>
        get() = listOf(ItemTarget.WEAPON)
        set(value) {}

    override fun use(player: Player) {
        player.launchProjectile(Fireball::class.java)
    }
}