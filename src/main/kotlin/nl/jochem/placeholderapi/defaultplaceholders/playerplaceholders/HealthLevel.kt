package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object HealthLevel : Placeholder {
    override fun getName(): String {
        return "health_level"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.health.toString() }
        return "null"
    }
}