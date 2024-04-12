package dev.toastersrpg.command.toast

import org.bukkit.entity.Player
import pine.toast.library.Wonderland
import pine.toast.library.commands.CommandPlayer

class Commands {

    @CommandPlayer(cooldown = 3, permission = "toasters.racesinv")
    fun openRaces(player: Player, args: List<String>) {
        Wonderland.getInvManager().openInventory(player, "Races")
    }

    @CommandPlayer
    fun test(player: Player, args: List<String>) {
        val joinargs = args.joinToString(" ")
        player.sendMessage(joinargs)
    }


}