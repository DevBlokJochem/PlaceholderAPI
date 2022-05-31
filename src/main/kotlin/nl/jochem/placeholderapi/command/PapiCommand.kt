package nl.jochem.placeholderapi.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType.Literal
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.PlaceholderAPI
import nl.jochem.placeholderapi.api.PlaceholderAPI.getDefaultPlaceholders
import nl.jochem.placeholderapi.configs.addPlaceholder
import nl.jochem.placeholderapi.configs.messagesConfig
import nl.jochem.placeholderapi.configs.placeholdersConfig
import nl.jochem.placeholderapi.configs.removePlaceholder
import nl.jochem.placeholderapi.core.PlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.PlayerItemstackPlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.PlayerPlaceholderGroup

class PapiCommand : Command("placeholder", "papi") {

    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg(messagesConfig.usage)
    }

    private fun getDefaultPlaceholder(inputGroup: String): PlaceholderGroup? {
        getDefaultPlaceholders().forEach {
            if(it.getPrefix() == inputGroup) {
                return it
            }
        }
        return null
    }

    private fun getActivePlaceholder(inputGroup: String): PlaceholderGroup? {
        getDefaultPlaceholders().forEach {
            if(it.getPrefix() == inputGroup) {
                return it
            }
        }
        return null
    }

    private fun executeOnList(
        sender: CommandSender
    ) {
        var message = ""
        PlaceholderAPI.getPlaceholders().forEach {
            message += it.getPrefix() + " "
        }
        sender.msg(message)
    }

    private fun executeOnDownload(
        sender: CommandSender,
        context: CommandContext,
        defaultGroupsName: ArgumentString
    ) {
        if(getDefaultPlaceholder(context[defaultGroupsName]) == null) { return }
        placeholdersConfig.addPlaceholder(context[defaultGroupsName])
        PlaceholderAPI.setPlaceholders(getDefaultPlaceholder(context[defaultGroupsName])!!)
        sender.msg(messagesConfig.download_placeholder)
    }


    private fun executeOnRemove(
        sender: CommandSender,
        context: CommandContext,
        activeGroupsName: ArgumentString
    ) {
        if(getActivePlaceholder(context[activeGroupsName]) == null) { return }
        placeholdersConfig.removePlaceholder(context[activeGroupsName])
        PlaceholderAPI.removePlaceholders(getActivePlaceholder(context[activeGroupsName])!!)
        sender.msg(messagesConfig.remove_placeholder)
    }

    init {
        setDefaultExecutor { sender: CommandSender, context: CommandContext ->
            usage(
                sender,
                context
            )
        }
        val list = Literal("list")
        val download = Literal("download")
        val remove = Literal("remove")
        val activeGroupsName = ArgumentString("activeGroupName")
        PlaceholderAPI.getPlaceholders().forEach {
            activeGroupsName.setSuggestionCallback { _, _, suggestion -> suggestion.addEntry(SuggestionEntry(it.getPrefix())) }
        }
        val defaultGroupsName = ArgumentString("defaultGroupName")
        getDefaultPlaceholders().forEach {
            if(!PlaceholderAPI.getPlaceholders().contains(it)) {
                defaultGroupsName.setSuggestionCallback { _, _, suggestion -> suggestion.addEntry(SuggestionEntry(it.getPrefix())) }
            }
        }
        addSyntax({ sender: CommandSender,_ -> executeOnList(sender) }, list)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnDownload(sender, context, defaultGroupsName) }, download, defaultGroupsName)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnRemove(sender, context, activeGroupsName) }, remove, activeGroupsName)
    }

    private fun CommandSender.msg(inputMessage: String) {
        val newMessage = messagesConfig.prefix + inputMessage
        if(this is Player) {
            sendMessage(PlaceholderAPI.translatePlaceholdersToComponent(player = this, inputString = newMessage))
        }else{
            sendMessage(PlaceholderAPI.translatePlaceholdersToString(player = null, inputString = newMessage))
        }
    }
}