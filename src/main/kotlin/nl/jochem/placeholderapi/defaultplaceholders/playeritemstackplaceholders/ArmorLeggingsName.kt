package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorLeggingsName : Placeholder {
    override fun getName(): String {
        return "armor_leggings_name"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.leggings.displayName.toString() }
        return "null"
    }
}