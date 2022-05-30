package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ArmorChestplateName : Placeholder {
    override fun getName(): String {
        return "armor_chestplate_name"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.chestplate.displayName.toString() }
        return "null"
    }
}