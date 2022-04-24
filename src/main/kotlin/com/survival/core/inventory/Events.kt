package com.survival.core.inventory

import com.survival.core.config.loadSavedInventory
import com.survival.core.config.updateSavedInventory
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerLoginEvent

object Events {

    fun onJoin(event: PlayerLoginEvent) {
        event.player.loadSavedInventory()
    }

    fun onLeave(event: PlayerDisconnectEvent) {
        event.player.updateSavedInventory()
    }

}