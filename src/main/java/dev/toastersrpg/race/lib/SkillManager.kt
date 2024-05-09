package dev.toastersrpg.race.lib

import dev.toastersrpg.Keys
import dev.toastersrpg.ToastRpg
import dev.toastersrpg.pdc.AdptManager
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import pine.toast.library.events.PlayerRightClickEvent
import pine.toast.library.utilities.CooldownManager

object SkillManager : Listener{

    private val skills: MutableSet<RaceSkill> = mutableSetOf()

    fun handleCosts(player: Player, raceSkill: RaceSkill) {
        val mana = ToastRpg.getMana()

        if (mana.hasEnoughMana(player.uniqueId, raceSkill.manaCost)) {
            mana.removeMana(player.uniqueId, raceSkill.manaCost)
        }
    }

    fun getSkill(name: String): RaceSkill? {
        return skills.find { it.name == name }
    }

    fun getSkills(): Set<RaceSkill> {
        return skills
    }

    fun registerSkill(skill: RaceSkill) {
        skills.add(skill)
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun handleWeaponSkill(event: PlayerRightClickEvent) {
        val player: Player = event.getPlayer()

        player.sendMessage("Check 1 before getting race info")

        val raceInfo = player.persistentDataContainer.get(Keys.RACE_INFO, AdptManager.raceInfoAdapter)
        if (raceInfo == null) player.sendMessage("No Race found")

        if (raceInfo!!.skills.size == 0) return

        player.sendMessage("Looping through all the skills applied.")
        for (skill in raceInfo.skills) {
            if (skill.target == SkillTarget.RIGHT_CLICK) {
                if (!CooldownManager.isPlayerOnCooldown(player, skill.getKey())) {
                    player.sendMessage("Found ${skill.name} with ${skill.target.name}")
                    skill.use(player)

                }
            }
        }
    }





}