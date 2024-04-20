package dev.toastersrpg.race

import dev.toastersrpg.race.lib.RaceInfo
import dev.toastersrpg.race.lib.RaceSkill
import java.io.Serializable

class Mage : RaceInfo(), Serializable {
    override val race: String
        get() = "Mage"
    override var health: Double
        get() = 70.0
        set(value) {}
    override var mana: Double
        get() = 100.0
        set(value) {}
    override var speed: Double
        get() = 0.0
        set(value) {}
    override var damage: Double
        get() = 1.25
        set(value) {}
    override var defense: Double
        get() = 2.25
        set(value) {}
    override var skills: MutableSet<RaceSkill>
        get() = mutableSetOf()
        set(value) {}

    override fun applySkills() {
        skills.add(FireballSkill())
    }
}