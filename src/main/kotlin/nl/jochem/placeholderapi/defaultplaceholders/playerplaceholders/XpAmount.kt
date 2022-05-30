package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object XpAmount : Placeholder {
    override fun getName(): String {
        return "xp_amount"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.exp.toString() }
        return "null"
    }
}