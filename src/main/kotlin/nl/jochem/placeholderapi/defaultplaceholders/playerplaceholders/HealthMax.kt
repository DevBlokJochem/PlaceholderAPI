package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object HealthMax : Placeholder {
    override fun getName(): String {
        return "health_max"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.maxHealth.toString() }
        return "null"
    }
}