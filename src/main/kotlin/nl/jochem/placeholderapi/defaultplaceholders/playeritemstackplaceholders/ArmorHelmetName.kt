package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorHelmetName : Placeholder {
    override fun getName(): String {
        return "armor_helmet_name"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.helmet.displayName.toString() }
        return "null"
    }
}