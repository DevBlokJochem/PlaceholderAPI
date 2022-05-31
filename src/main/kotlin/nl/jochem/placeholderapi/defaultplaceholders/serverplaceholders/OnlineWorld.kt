package nl.jochem.placeholderapi.defaultplaceholders.serverplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object OnlineWorld : Placeholder {
    override fun getName(): String {
        return "online_world"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.instance!!.players.size.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}