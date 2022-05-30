package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object FlySpeed : Placeholder {
    override fun getName(): String {
        return "fly_speed"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.flyingSpeed.toString() }
        return "null"
    }
}