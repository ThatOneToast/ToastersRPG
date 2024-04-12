package dev.toastersrpg

import org.bukkit.entity.Player
import pine.toast.library.commands.CommandPlayer
import pine.toast.library.utilities.WonderlandColors

class Commands {

    @CommandPlayer(cooldown = 3, permission = "toasters.racesinv")
    fun openRaces(player: Player, args: List<String>) {
        ToastRpg.getWonderland().getInvManager().openInventory(player, "Races")
    }


}