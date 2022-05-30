package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorBootsName : Placeholder {
    override fun getName(): String {
        return "armor_boots_name"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.boots.displayName.toString() }
        return "null"
    }
}