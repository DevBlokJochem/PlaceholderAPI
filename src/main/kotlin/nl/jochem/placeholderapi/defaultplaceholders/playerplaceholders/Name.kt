package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object Name : Placeholder {
    override fun getName(): String {
        return "name"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.username }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(AllowFlight.getPlaceholderString(player))
    }
}