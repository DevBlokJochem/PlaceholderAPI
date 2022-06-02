package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder
import nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders.AllowFlight

object ArmorLeggingsMaterial : Placeholder {
    override fun getName(): String {
        return "armor_leggings_material"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.leggings.material().toString() }
        return "null"
    }
}