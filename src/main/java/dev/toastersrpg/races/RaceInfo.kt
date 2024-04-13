package dev.toastersrpg.races

abstract class RaceInfo {
    abstract val name: String
    abstract val startingHealth: Double
    abstract var mana: Double
    abstract var manaRegeneration: Double
    abstract var damage: Double
    abstract var defense: Double
    abstract var speed: Double

}
