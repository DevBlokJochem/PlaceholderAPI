package nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.Placeholder

object ItemHandName : Placeholder {
    override fun getName(): String {
        return "item_hand_name"
    }

    override fun getPlaceholderString(player: Player?): String {
        if(player != null) { return player.itemInMainHand.displayName.toString() }
        return "null"
    }
}