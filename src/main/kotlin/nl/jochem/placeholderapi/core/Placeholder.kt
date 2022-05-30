package nl.jochem.placeholderapi.core

import net.minestom.server.entity.Player

interface Placeholder {
    fun getName() : String
    fun getPlaceholder(player : Player? = null) : String
}