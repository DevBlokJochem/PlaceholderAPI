package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object Gamemode : Placeholder {
    override fun getName(): String {
        return "gamemode"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.gameMode.toString() }
        return "null"
    }
}