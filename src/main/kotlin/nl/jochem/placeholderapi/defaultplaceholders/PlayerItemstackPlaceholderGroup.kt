package nl.jochem.placeholderapi.defaultplaceholders

import nl.jochem.placeholderapi.api.Placeholder
import nl.jochem.placeholderapi.api.PlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.playeritemstackplaceholders.*

object PlayerItemstackPlaceholderGroup : PlaceholderGroup {
    override fun getPrefix(): String {
        return "playeritemstack"
    }

    override val placeholderGroups: ArrayList<Placeholder> = ArrayList()
    override fun getPluginName(): String {
        return "null"
    }

    override fun register() {
        placeholderGroups.add(ArmorHelmetName)
        placeholderGroups.add(ArmorHelmetMaterial)
        placeholderGroups.add(ArmorChestplateName)
        placeholderGroups.add(ArmorChestplateMaterial)
        placeholderGroups.add(ArmorLeggingsName)
        placeholderGroups.add(ArmorLeggingsMaterial)
        placeholderGroups.add(ArmorBootsName)
        placeholderGroups.add(ArmorBootsMaterial)
    }

}