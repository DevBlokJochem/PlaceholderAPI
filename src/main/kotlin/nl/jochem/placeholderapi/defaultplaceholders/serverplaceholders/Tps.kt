package nl.jochem.placeholderapi.defaultplaceholders.serverplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object Tps : Placeholder {
    override fun getName(): String {
        return "tps"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return MinecraftServer.TICK_PER_SECOND.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}