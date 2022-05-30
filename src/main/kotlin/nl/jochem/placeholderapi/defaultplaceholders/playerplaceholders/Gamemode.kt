package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object Gamemode : Placeholder {
    override fun getName(): String {
        return "gamemode"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.gameMode.toString() }
        return "null"
    }
}