package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorHelmetMaterial : Placeholder {
    override fun getName(): String {
        return "armor_helmet_material"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.helmet.material().toString() }
        return "null"
    }
}