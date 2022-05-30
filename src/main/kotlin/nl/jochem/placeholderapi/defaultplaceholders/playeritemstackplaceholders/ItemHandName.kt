package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object ItemHandName : Placeholder {
    override fun getName(): String {
        return "item_hand_name"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.itemInMainHand.displayName.toString() }
        return "null"
    }
}