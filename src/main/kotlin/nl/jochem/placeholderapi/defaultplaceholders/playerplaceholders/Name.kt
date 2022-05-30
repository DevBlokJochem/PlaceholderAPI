package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object Name : Placeholder {
    override fun getName(): String {
        return "name"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.username }
        return "null"
    }
}