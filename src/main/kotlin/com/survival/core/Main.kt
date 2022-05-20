package com.survival.core

import com.survival.core.blockbreak.BlockBreakEvent
import com.survival.core.blockbreak.BlockPickup
import com.survival.core.commands.ClearCommand
import com.survival.core.commands.FlyCommand
import com.survival.core.commands.PermissionCommand
import com.survival.core.commands.StaffCommand
import com.survival.core.config.*
import com.survival.core.events.EventNodes
import com.survival.core.inventory.Events
import com.survival.core.blockplacement.PlacementRules
import net.minestom.server.MinecraftServer
import net.minestom.server.event.inventory.InventoryCloseEvent
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerBlockInteractEvent
import net.minestom.server.event.player.PlayerBlockPlaceEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.extensions.Extension
import net.minestom.server.extras.MojangAuth
import net.minestom.server.extras.optifine.OptifineSupport

import java.io.File

class Main : Extension() {

    override fun initialize() {
        registerConfigs()
        registerCommands()
        registerEvents()

        PlacementRules.init()
        OptifineSupport.enable()


        println("survival core enabled!")
    }

    override fun terminate() {

        println("survival core disabled!")
    }

    private fun registerCommands() {
        val commandManager = MinecraftServer.getCommandManager()
        commandManager.register(ClearCommand())
        commandManager.register(FlyCommand())
        //commandManager.register(GameModeCommand())
        commandManager.register(PermissionCommand())
        //commandManager.register(WhitelistCommand())
        //commandManager.register(GameRuleCommand())
        commandManager.register(StaffCommand())
    }

    private fun registerConfigs() {
        if(!File("data").exists()) { File("data").mkdir() }
        if(!File("configs").exists()) { File("configs").mkdir() }
        RegisterGameRuleConfig
        RegisterMessagesConfig
        RegisterWhitelistData
        RegisterPermissionData
        RegisterInventoryData.init()
    }

    private fun registerEvents() {
        EventNodes.init()
        EventNodes.getBreakNode().addListener(PlayerBlockBreakEvent::class.java, BlockBreakEvent()::onBreak)
        EventNodes.getPickupNode().addListener(PickupItemEvent::class.java, BlockPickup()::onPickup)
        handler.addListener(PlayerLoginEvent::class.java, Events::onJoin)
        handler.addListener(PlayerDisconnectEvent::class.java, Events::onLeave)
        EventNodes.getBlockInventoryNode().addListener(PlayerBlockPlaceEvent::class.java, Events::onPlace)
        EventNodes.getBlockInventoryNode().addListener(InventoryCloseEvent::class.java, Events::onBlockInventoryClose)
        EventNodes.getBlockInventoryNode().addListener(PlayerBlockInteractEvent::class.java, Events::onClickOnBlock)
    }

}

val handler = MinecraftServer.getGlobalEventHandler()