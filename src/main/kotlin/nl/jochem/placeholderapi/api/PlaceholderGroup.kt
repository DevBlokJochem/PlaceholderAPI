package nl.jochem.placeholderapi.api

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player

interface PlaceholderGroup {
    fun getPrefix() : String
    val placeholderGroups : ArrayList<Placeholder>
    fun getPluginName() : String
    fun register()
    fun replaceHolderString(player : Player? = null, inputMessage : String) : String {
        if(!inputMessage.contains("%${getPrefix()}")) { return inputMessage }
        var message = inputMessage
        placeholderGroups.forEach {
            message = message.replace("%${getPrefix()}_${it.getName()}%", it.getPlaceholderString(player))
        }
        return message
    }
}