package nl.jochem.placeholderapi.defaultplaceholders.serverplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object Version : Placeholder {
    override fun getName(): String {
        return "version"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return MinecraftServer.VERSION_NAME }
        return "null"
    }
}