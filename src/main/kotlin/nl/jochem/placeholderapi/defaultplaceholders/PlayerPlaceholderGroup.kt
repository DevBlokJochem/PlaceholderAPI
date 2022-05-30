package nl.jochem.placeholderapi.defaultplaceholders

import nl.jochem.placeholderapi.core.Placeholder
import nl.jochem.placeholderapi.core.PlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders.ArmorBootsName
import nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders.ArmorChestplateName
import nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders.ArmorHelmetName
import nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders.ArmorLeggingsName
import nl.jochem.placeholderapi.defaultplaceholders.playerplaceholders.*

object PlayerPlaceholderGroup : PlaceholderGroup {
    override fun getPrefix(): String {
        return "player"
    }

    override val placeholderGroups: ArrayList<Placeholder> = ArrayList()

    override fun register() {
        placeholderGroups.add(AllowFlight)
        placeholderGroups.add(FlySpeed)
        placeholderGroups.add(FoodLevel)
        placeholderGroups.add(Gamemode)
        placeholderGroups.add(HealthLevel)
        placeholderGroups.add(HealthMax)
        placeholderGroups.add(Name)
        placeholderGroups.add(SaturationLevel)
        placeholderGroups.add(XpAmount)
        placeholderGroups.add(XpLevel)
    }

}