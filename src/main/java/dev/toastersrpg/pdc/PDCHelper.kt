package dev.toastersrpg.pdc

import dev.toastersrpg.Keys
import dev.toastersrpg.race.lib.RaceInfo
import org.bukkit.entity.Player

object PDCHelper {

    fun getRaceInfo(player: Player): RaceInfo? {
        return player.persistentDataContainer.get(Keys.RACE_INFO, AdptManager.raceInfoAdapter)
    }

    /**
     * It's here because it here. Don't use this this if you don't have to.
     * RaceManager.applyRaceToPlayer() will provide everything for the race into the palyer
     * @param player
     * @param raceInfo
     */
    fun setRaceInfo(player: Player, raceInfo: RaceInfo) {
        player.persistentDataContainer.set(Keys.RACE_INFO, AdptManager.raceInfoAdapter, raceInfo)
    }


}