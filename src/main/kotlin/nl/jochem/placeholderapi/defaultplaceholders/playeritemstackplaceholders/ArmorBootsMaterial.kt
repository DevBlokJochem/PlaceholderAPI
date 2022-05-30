package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorBootsMaterial : Placeholder {
    override fun getName(): String {
        return "armor_boots_material"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.boots.material().toString() }
        return "null"
    }
}