package dev.toastersrpg.race

import dev.toastersrpg.race.lib.RaceSkill
import dev.toastersrpg.race.lib.SkillTarget
import org.bukkit.entity.Fireball
import org.bukkit.entity.Player
import java.io.Serializable

class FireballSkill(
    override val name: String = "Fireball",
    override var level: Int = 1,
    override var manaCost: Double = 33.75,
    override var healthCost: Double = 0.0,
    override var cooldown: Double = 30.0,
    override var target: SkillTarget = SkillTarget.RIGHT_CLICK
) : RaceSkill(), Serializable {

    override fun use(player: Player) {
        player.launchProjectile(Fireball::class.java)
    }
}