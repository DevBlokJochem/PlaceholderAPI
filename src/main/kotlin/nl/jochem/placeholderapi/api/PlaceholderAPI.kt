package nl.jochem.placeholderapi.api

import net.minestom.server.color.Color
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.core.PlaceholderGroup
object PlaceholderAPI {
    private var placeholderGroups : ArrayList<PlaceholderGroup> = ArrayList()


    fun getPlaceholders(): ArrayList<PlaceholderGroup> {
        return placeholderGroups
    }

    fun setPlaceholders(placeholderGroup : PlaceholderGroup) {
        if(placeholderGroups.contains(placeholderGroup)) { return; }
        placeholderGroups.add(placeholderGroup)
        placeholderGroup.register()
    }

    fun translatePlaceholders(player : Player? = null, inputString : String) : String {
        if(placeholderGroups.isEmpty()) { return inputString; }
        var newString = inputString
        placeholderGroups.forEach {
            newString = it.replaceHolder(player, newString)
        }
        return newString
    }
}