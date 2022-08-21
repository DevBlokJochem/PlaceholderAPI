package nl.jochem.placeholderapi.api

import com.google.gson.GsonBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.minestom.server.entity.Player
import net.minestom.server.extensions.Extension
import nl.jochem.placeholderapi.configs.*
import nl.jochem.placeholderapi.defaultplaceholders.PlayerItemstackPlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.PlayerPlaceholderGroup
import java.io.File

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
        if(placeholderGroup.getPluginName() != "null" && !File("extensions/${placeholderGroup.getPluginName()}").exists()) {
            println(translatePlaceholdersToString(inputString = messagesConfig.missing_placeholderPlugin.replace("%placeholder_name%", placeholderGroup.getPluginName())))
            return
        }
        placeholderGroups.add(placeholderGroup)
        placeholderGroup.register()
    }

    fun removePlaceholders(placeholderGroup: PlaceholderGroup) {
        if(!placeholderGroups.contains(placeholderGroup)) { return }
        placeholderGroups.remove(placeholderGroup)
    }

    fun translatePlaceholdersToString(player : Player? = null, inputString : String) : String {
        var newString = inputString.replace("&", "§")
        if(placeholderGroups.isEmpty()) { return newString; }
        placeholderGroups.forEach {
            newString = it.replaceHolderString(player, newString)
        }
        return newString
    }

    fun reload() {
        messagesConfig = GsonBuilder()
            .setPrettyPrinting()
            .create()!!.fromJson(File("extensions/placeholderapi/messages.json").readText(), MessagesConfig::class.java)!!
        placeholdersConfig = GsonBuilder()
            .setPrettyPrinting()
            .create()!!.fromJson(File("extensions/placeholderapi/active_placeholders.json").readText(), PlaceholdersConfig::class.java)!!
    }
}