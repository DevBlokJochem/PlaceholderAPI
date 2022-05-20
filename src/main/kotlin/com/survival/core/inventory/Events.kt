package com.survival.core.inventory

import com.survival.core.config.*
import com.survival.core.groups.MaterialGroups.INVENTORY_BLOCKS
import net.minestom.server.event.inventory.InventoryCloseEvent
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.item.Material

object Events {

    fun onJoin(event: PlayerLoginEvent) {
        event.player.loadSavedInventory()
    }

    fun onLeave(event: PlayerDisconnectEvent) {
        event.player.updateSavedInventory()
    }

    fun onPlace(event: PlayerBlockPlaceEvent) {
        if(INVENTORY_BLOCKS.contains(event.player.instance!!.getBlock(event.blockPosition.relative(event.blockFace.oppositeFace)).registry().material()) && !event.player.isSneaking) {
            event.isCancelled = true
        }
    }

    fun onClickOnBlock(event: PlayerBlockInteractEvent) {
        if(event.player.isSneaking) {
            return
        }
        if(event.block.registry().material() == Material.ENDER_CHEST) {
            event.player.openEnderchest()
        }

    }
    fun onBlockInventoryClose(event: InventoryCloseEvent) {
        if(event.inventory?.title.toString().contains("§5§lEnderChest")) {
            event.player.saveEnderchest(event.inventory!!)
        }
    }

}