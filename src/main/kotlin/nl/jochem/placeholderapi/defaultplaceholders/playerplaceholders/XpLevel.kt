package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object XpLevel : Placeholder {
    override fun getName(): String {
        return "xp_level"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.level.toString() }
        return "null"
    }
}