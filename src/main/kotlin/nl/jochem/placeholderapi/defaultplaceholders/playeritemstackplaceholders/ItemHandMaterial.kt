package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ItemHandMaterial : Placeholder {
    override fun getName(): String {
        return "item_hand_material"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.itemInMainHand.material().toString() }
        return "null"
    }
}