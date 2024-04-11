package dev.toastersrpg.materials.handlers

import pine.toast.library.events.PlayerLeftClickEvent
import pine.toast.library.events.PlayerRightClickEvent
import pine.toast.library.items.ItemHandler

class EmpowermentStoneHandler : ItemHandler {
    override fun onPlayerLeftClick(event: PlayerLeftClickEvent) {
    }

    override fun onPlayerRightClick(event: PlayerRightClickEvent) {
        TODO("When right click, give some random benefits like attributes. I don't know what yet so I'ma leave it blank.")
    }
}