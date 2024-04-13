package dev.toastersrpg.races

abstract class RaceSkills {

    abstract var manaCost: Double
    abstract var cooldown: Int
    abstract val name: String
    abstract val levelRequirement: Int
    abstract val skillActivation: SkillActivation




}