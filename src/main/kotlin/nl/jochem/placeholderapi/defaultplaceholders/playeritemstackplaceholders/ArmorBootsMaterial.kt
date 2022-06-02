package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ArmorBootsMaterial : Placeholder {
    override fun getName(): String {
        return "armor_boots_material"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.boots.material().toString() }
        return "null"
    }
}