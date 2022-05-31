package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object XpAmount : Placeholder {
    override fun getName(): String {
        return "xp_amount"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.exp.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}