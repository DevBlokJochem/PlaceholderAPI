package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object Gamemode : Placeholder {
    override fun getName(): String {
        return "gamemode"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.gameMode.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(AllowFlight.getPlaceholderString(player))
    }
}