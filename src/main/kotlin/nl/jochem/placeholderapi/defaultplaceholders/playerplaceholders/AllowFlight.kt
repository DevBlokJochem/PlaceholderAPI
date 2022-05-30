package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object AllowFlight : Placeholder {
    override fun getName(): String {
        return "allow_flight"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.isAllowFlying.toString() }
        return "null"
    }
}