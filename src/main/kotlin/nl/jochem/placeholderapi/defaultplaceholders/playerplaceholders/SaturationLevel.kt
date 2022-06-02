package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object SaturationLevel : Placeholder {
    override fun getName(): String {
        return "saturation_level"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.foodSaturation.toString() }
        return "null"
    }
}