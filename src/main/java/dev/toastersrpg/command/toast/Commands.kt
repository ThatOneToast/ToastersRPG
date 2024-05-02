package dev.toastersrpg.command.toast

import dev.toastersrpg.Keys
import dev.toastersrpg.pdc.AdptManager
import dev.toastersrpg.race.Mage
import dev.toastersrpg.race.lib.RaceManager
import dev.toastersrpg.race.lib.SkillManager
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pine.toast.library.Wonderland
import pine.toast.library.commands.simple.CommandType
import pine.toast.library.commands.simple.WLCommand
import pine.toast.library.utilities.WonderlandColors

class Commands {

    @WLCommand(target = CommandType.PLAYER, cooldown = 3, permission = "toasters.racesinv")
    fun openRaces(player: Player, args: Array<String>) {
        Wonderland.getInvManager().openInventory(player, "Races")
    }

    @WLCommand(target = CommandType.CONSOLE)
    fun test(player: CommandSender, args: Array<String>) {
        val joinargs = args.joinToString(" ")
        player.sendMessage(joinargs)
    }

    @WLCommand(target = CommandType.PLAYER)
    fun testPlayer(player: Player, args: Array<String>) {
        val joinargs = args.joinToString(" ")
        player.sendMessage(joinargs)
    }

    @WLCommand(target = CommandType.PLAYER, permission = "toasters.applyrace")
    fun applyMageRace(player: Player, args: Array<String>) {
        RaceManager.applyRaceToPlayer(player, Mage())
    }

    @WLCommand(target = CommandType.ALL, permission = "toasters.getskills")
    fun listSkills(player: CommandSender, args: Array<String>) {
        val skills = SkillManager.getSkills()
        val skillsNameList: List<String> = skills.map { it.name }
        val skillsName: String = skillsNameList.joinToString(", ")
        player.sendMessage(WonderlandColors.GREEN + skillsName)
    }

    @WLCommand(target = CommandType.ALL, permission = "toasters.addskills")
    fun addSkill(player: Player, args: Array<String>) {
        if (args.size == 2) {
            val target = Bukkit.getPlayer(args[1])
            if (target == null) player.sendMessage(WonderlandColors.RED + "This player does not exist.")

            val skill = SkillManager.getSkill(args[0])
            if (skill == null) player.sendMessage(WonderlandColors.RED + "This skill does not exist.")
            RaceManager.addSkill(player, skill!!)
        }

        if (args.size == 1) {
            val skill = SkillManager.getSkill(args[0])
            if (skill == null) player.sendMessage(WonderlandColors.RED + "This skill does not exist.")
            RaceManager.addSkill(player, skill!!)

        }

        player.sendMessage(WonderlandColors.GREEN + "Complete")
    }

    @WLCommand(target = CommandType.PLAYER, permission = "toasters.pdccheck")
    fun checkRaceInfo(player: Player, args: Array<String>) {
        val raceInfo = player.persistentDataContainer.get(Keys.RACE_INFO, AdptManager.raceInfoAdapter)

        if (raceInfo != null) {
            player.sendMessage("Race: ${raceInfo.race}")
            player.sendMessage("Health: ${raceInfo.health}")
            player.sendMessage("Mana: ${raceInfo.mana}")
            player.sendMessage("Speed: ${raceInfo.speed}")
            player.sendMessage("Damage: ${raceInfo.damage}")
            player.sendMessage("Defense: ${raceInfo.defense}")
            val skillsName: MutableList<String> = mutableListOf()

            for (skill in raceInfo.skills) {
                skillsName.add(skill.name)
            }

            player.sendMessage(skillsName.joinToString { ", " })
        } else {
            player.sendMessage("Race info not found.")
        }
    }

}