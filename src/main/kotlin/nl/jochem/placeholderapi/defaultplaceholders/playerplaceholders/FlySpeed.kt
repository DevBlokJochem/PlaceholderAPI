package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object FlySpeed : Placeholder {
    override fun getName(): String {
        return "fly_speed"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.flyingSpeed.toString() }
        return "null"
    }
}