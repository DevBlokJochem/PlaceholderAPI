package nl.jochem.placeholderapi.api

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.configs.messagesConfig
import nl.jochem.placeholderapi.configs.placeholdersConfig
import nl.jochem.placeholderapi.defaultplaceholders.PlayerItemstackPlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.PlayerPlaceholderGroup

object PlaceholderAPI {

    private var placeholderGroups : ArrayList<PlaceholderGroup> = ArrayList()
    private val defaultGroupList : ArrayList<PlaceholderGroup> = ArrayList()

    fun getDefaultPlaceholders(): List<PlaceholderGroup> {
        return defaultGroupList.toList()
    }

    fun setDefaultPlaceholders(placeholder : PlaceholderGroup) {
        //DO NOT USE THIS FUNCTION
        defaultGroupList.add(placeholder)
        if(!placeholdersConfig.placeholders.contains(placeholder.getPrefix())) { return }
        setPlaceholders(placeholder)
    }

    fun getPlaceholders(): ArrayList<PlaceholderGroup> {
        return placeholderGroups
    }

    fun setPlaceholders(placeholderGroup : PlaceholderGroup) {
        if(placeholderGroups.contains(placeholderGroup)) {
            println(translatePlaceholdersToString(inputString = messagesConfig.placeholder_exists))
            return
        }
        placeholderGroups.forEach {
            if(it.getPrefix() == placeholderGroup.getPrefix()) {
                println(translatePlaceholdersToString(inputString = messagesConfig.placeholder_exists))
                return
            }
        }
        placeholderGroups.add(placeholderGroup)
        placeholderGroup.register()
    }

    fun removePlaceholders(placeholderGroup: PlaceholderGroup) {
        if(!placeholderGroups.contains(placeholderGroup)) { return }
        placeholderGroups.remove(placeholderGroup)
    }

    fun translatePlaceholdersToString(player : Player? = null, inputString : String) : String {
        if(placeholderGroups.isEmpty()) { return inputString; }
        var newString = inputString
        placeholderGroups.forEach {
            newString = it.replaceHolderString(player, newString)
        }
        return newString
    }

    fun translatePlaceholdersToComponent(player : Player? = null, inputString: String) : Component {
        if(placeholderGroups.isEmpty()) { return Component.text(inputString); }
        var newComponent : Component = Component.text(inputString)
        placeholderGroups.forEach {
            if(newComponent.contains(Component.text(it.getPrefix()))) {
                newComponent = it.replaceHolderComponent(player, newComponent)
            }
        }
        return newComponent
    }
}