package dev.toastersrpg.race.lib

import dev.toastersrpg.ToastRpg
import org.bukkit.entity.Player

object SkillManager {

    fun handleCosts(player: Player, raceSkill: RaceSkill) {
        val mana = ToastRpg.getMana()

        if (mana.hasEnoughMana(player.uniqueId, raceSkill.manaCost)) {
            mana.removeMana(player.uniqueId, raceSkill.manaCost)
        }
    }





}