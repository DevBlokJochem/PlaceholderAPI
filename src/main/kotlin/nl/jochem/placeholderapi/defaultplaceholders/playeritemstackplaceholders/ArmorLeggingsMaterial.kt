package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorLeggingsMaterial : Placeholder {
    override fun getName(): String {
        return "armor_leggings_material"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.leggings.material().toString() }
        return "null"
    }
}