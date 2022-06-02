package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ArmorLeggingsName : Placeholder {
    override fun getName(): String {
        return "armor_leggings_name"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.leggings.displayName.toString() }
        return "null"
    }
}