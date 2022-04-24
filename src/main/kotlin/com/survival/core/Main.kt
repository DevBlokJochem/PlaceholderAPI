package com.survival.core

import com.survival.core.blockdrops.BlockBreakEvent
import com.survival.core.blockdrops.BlockPickup
import com.survival.core.commands.*
import com.survival.core.config.*
import com.survival.core.events.EventNodes
import com.survival.core.inventory.Events
import net.minestom.server.MinecraftServer
import net.minestom.server.event.item.PickupItemEvent
import net.minestom.server.event.player.PlayerBlockBreakEvent
import net.minestom.server.event.player.PlayerDisconnectEvent
import net.minestom.server.event.player.PlayerLoginEvent
import net.minestom.server.extensions.Extension
import java.io.File

class Main : Extension() {

    override fun initialize() {
        registerConfigs()
        registerCommands()
        registerEvents()
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
        //commandManager.register(PermissionCommand())
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
        //RegisterInventoryData.init()
    }

    private fun registerEvents() {
        EventNodes.init()
        EventNodes.getBreakNode().addListener(PlayerBlockBreakEvent::class.java) { event -> BlockBreakEvent().onBreak(event) }
        EventNodes.getPickupNode().addListener(PickupItemEvent::class.java) { event -> BlockPickup().onPickup(event) }
//        EventNodes.getStaffNode().addListener(PlayerLoginEvent::class.java) { event -> Events.onJoin(event) }
//        EventNodes.getStaffNode().addListener(PlayerDisconnectEvent::class.java) { event -> Events.onLeave(event) }
    }

}

val handler = MinecraftServer.getGlobalEventHandler()