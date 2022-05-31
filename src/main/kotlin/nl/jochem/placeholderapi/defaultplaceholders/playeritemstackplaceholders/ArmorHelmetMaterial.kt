package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ArmorHelmetMaterial : Placeholder {
    override fun getName(): String {
        return "armor_helmet_material"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.helmet.material().toString() }
        return "null"
    }

    override fun getPlaceholderComponent(player: Player?): Component {
        return Component.text(getPlaceholderString(player))
    }
}