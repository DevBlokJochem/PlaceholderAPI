package nl.jochem.placeholderapi.core

import net.minestom.server.entity.Player

interface PlaceholderGroup {
    fun getPrefix() : String
    val placeholderGroups : ArrayList<Placeholder>
    fun register()
    fun replaceHolder(player : Player? = null, inputMessage : String) : String {
        var message = inputMessage
        placeholderGroups.forEach {
            message = message.replace("%${getPrefix()}_${it.getName()}%", it.getPlaceholder(player))
        }
        return message
    }
}