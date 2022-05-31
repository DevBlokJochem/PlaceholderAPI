package nl.jochem.placeholderapi.core

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player

interface PlaceholderGroup {
    fun getPrefix() : String
    val placeholderGroups : ArrayList<Placeholder>

    fun register()
    fun replaceHolderString(player : Player? = null, inputMessage : String) : String {
        var message = inputMessage
        placeholderGroups.forEach {
            message = message.replace("%${getPrefix()}_${it.getName()}%", it.getPlaceholderString(player))
        }
        return message
    }
    fun replaceHolderComponent(player : Player? = null, inputMessage : Component) : Component {
        var message = inputMessage
        placeholderGroups.forEach {
            message = message.replaceText("%${getPrefix()}_${it.getName()}%", it.getPlaceholderComponent(player))
        }
        return message
    }
}