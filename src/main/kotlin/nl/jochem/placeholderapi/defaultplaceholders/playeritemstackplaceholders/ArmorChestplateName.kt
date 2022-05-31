package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ArmorChestplateName : Placeholder {
    override fun getName(): String {
        return "armor_chestplate_name"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.chestplate.displayName.toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}