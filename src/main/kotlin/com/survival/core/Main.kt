package com.survival.core

import com.survival.core.blockdrops.BlockBreakEvent
import com.survival.core.commands.*
import com.survival.core.config.RegisterMessagesConfig
import com.survival.core.config.RegisterPermissionData
import com.survival.core.config.RegisterWhitelistData
import net.minestom.server.MinecraftServer
import net.minestom.server.event.player.PlayerBlockBreakEvent
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
    }
    private fun registerConfigs() {
        if(!File("data").exists()) { File("data").mkdir() }
        if(!File("configs").exists()) { File("configs").mkdir() }
        RegisterMessagesConfig
        RegisterWhitelistData
        RegisterPermissionData
    }

    private fun registerEvents() {
        MinecraftServer.getGlobalEventHandler().addListener(PlayerBlockBreakEvent::class.java) { event -> BlockBreakEvent().onBreak(event)}
    }

}
