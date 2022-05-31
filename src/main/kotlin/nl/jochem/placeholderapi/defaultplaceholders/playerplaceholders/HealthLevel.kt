package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object HealthLevel : Placeholder {
    override fun getName(): String {
        return "health_level"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.health.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}