package nl.jochem.placeholderapi.defaultplaceholders.serverplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object Name : Placeholder {
    override fun getName(): String {
        return "name"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return MinecraftServer.getBrandName() }
        return "null"
    }
}