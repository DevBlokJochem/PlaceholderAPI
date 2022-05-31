package nl.jochem.placeholderapi.defaultplaceholders.serverplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object Online : Placeholder {
    override fun getName(): String {
        return "online"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return MinecraftServer.getConnectionManager().onlinePlayers.size.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}