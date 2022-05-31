package nl.jochem.placeholderapi.api

import net.kyori.adventure.text.Component
import net.minestom.server.entity.Player

interface Placeholder {
    fun getName() : String
    fun getPlaceholderString(player : Player? = null) : String
    fun getPlaceholderComponent(player : Player? = null) : Component
}