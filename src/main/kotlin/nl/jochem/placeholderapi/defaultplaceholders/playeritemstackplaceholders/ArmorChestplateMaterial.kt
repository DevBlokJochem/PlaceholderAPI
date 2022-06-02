package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ArmorChestplateMaterial : Placeholder {
    override fun getName(): String {
        return "armor_chestplate_material"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.chestplate.material().toString() }
        return "null"
    }
}