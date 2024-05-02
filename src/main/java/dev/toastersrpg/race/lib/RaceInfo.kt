package dev.toastersrpg.race.lib

import dev.toastersrpg.ToastRpg
import org.bukkit.NamespacedKey

abstract class RaceInfo(
) {

    abstract val race: String
    abstract var health: Double
    abstract var mana: Double
    abstract var speed: Double
    abstract var damage: Double
    abstract var defense: Double
    abstract var skills: MutableSet<RaceSkill>

    fun hasSkills(): Boolean {
        return skills.isNotEmpty()
    }

    fun addSkill(skill: RaceSkill) {
        skills.add(skill)
    }

    fun removeSkill(skill: String) {
        skills.removeIf { it.name == skill }
    }

    fun getSkill(name: String): RaceSkill? {
        return skills.find { it.name == name }
    }

    fun replaceSkill(skillName: String, newSkill: RaceSkill) {
        val skill = getSkill(skillName)
        if (skill != null) {
            skills.remove(skill)
            skills.add(newSkill)
        }

    }

    fun getKey(): NamespacedKey {
        return NamespacedKey(ToastRpg.getPlugin(), race)
    }

    abstract fun applySkills()
}