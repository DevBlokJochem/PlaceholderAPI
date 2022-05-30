package nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders

import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.Placeholder

object FoodLevel : Placeholder {
    override fun getName(): String {
        return "food_level"
    }

    override fun getPlaceholder(player: Player?): String {
        if(player != null) { return player.food.toString() }
        return "null"
    }
}