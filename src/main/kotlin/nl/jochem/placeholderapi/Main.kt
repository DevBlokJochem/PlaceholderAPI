package nl.jochem.placeholderapi

import net.minestom.server.MinecraftServer
import net.minestom.server.extensions.Extension
import nl.jochem.placeholderapi.api.PlaceholderAPI
import nl.jochem.placeholderapi.command.PapiCommand
import nl.jochem.placeholderapi.configs.RegisterMessagesConfig
import nl.jochem.placeholderapi.defaultplaceholders.PlayerItemstackPlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.PlayerPlaceholderGroup
import java.io.File

class Main : Extension() {

    override fun initialize() {
        registerConfigs()
        registerCommands()
        registerPlaceholders()
        println("PlaceholderAPI enabled!")
    }

    override fun terminate() {

        println("PlaceholderAPI disabled!")
    }

    private fun registerCommands() {
        val commandManager = MinecraftServer.getCommandManager()
        commandManager.register(PapiCommand())
    }

    private fun registerConfigs() {
        if(!File("extensions/placeholderapi").exists()) { File("extensions/placeholderapi").mkdirs() }
        RegisterMessagesConfig
    }

    private fun registerPlaceholders() {
        PlaceholderAPI.setPlaceholders(PlayerPlaceholderGroup)
        PlaceholderAPI.setPlaceholders(PlayerItemstackPlaceholderGroup)

    }
}