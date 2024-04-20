package dev.toastersrpg.command.toast

import dev.toastersrpg.race.Mage
import dev.toastersrpg.race.lib.RaceManager
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pine.toast.library.Wonderland
import pine.toast.library.commands.simple.CommandType
import pine.toast.library.commands.simple.WLCommand

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


}