package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object SaturationLevel : Placeholder {
    override fun getName(): String {
        return "saturation_level"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.foodSaturation.toString() }
        return "null"
    }
}