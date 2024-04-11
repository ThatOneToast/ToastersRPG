package dev.toastersrpg

import org.bukkit.entity.Player
import pine.toast.library.commands.CommandPlayer

class Commands {

    @CommandPlayer(cooldown = 3)
    fun hello(player: Player, args: List<String>) {
        player.sendMessage("Hello, ${args.joinToString(" ")}!")
    }


}