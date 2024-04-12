package dev.toastersrpg.materials.handlers

import pine.toast.library.Wonderland
import pine.toast.library.events.PlayerLeftClickEvent
import pine.toast.library.events.PlayerRightClickEvent
import pine.toast.library.items.ItemHandler

class EmpowermentStoneHandler : ItemHandler {
    override fun onPlayerLeftClick(event: PlayerLeftClickEvent) {
    }

    override fun onPlayerRightClick(event: PlayerRightClickEvent) {
        val player = event.getPlayer()

    }
}