package nl.jochem.placeholderapi.defaultplaceholders

import nl.jochem.placeholderapi.api.Placeholder
import nl.jochem.placeholderapi.api.PlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.serverplaceholders.*

object ServerPlaceholderGroup : PlaceholderGroup {
    override fun getPrefix(): String {
        return "server"
    }

    override val placeholderGroups: ArrayList<Placeholder> = ArrayList()
    override fun getPluginName(): String {
        return "null"
    }

    override fun register() {
        placeholderGroups.add(Name)
        placeholderGroups.add(Online)
        placeholderGroups.add(OnlineWorld)
        placeholderGroups.add(Tps)
        placeholderGroups.add(Version)
    }

}