package nl.jochem.placeholderapi.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.CommandSyntax
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType.Literal
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.command.builder.suggestion.SuggestionEntry
import net.minestom.server.entity.Player
import nl.jochem.placeholderapi.api.PlaceholderAPI
import nl.jochem.placeholderapi.api.PlaceholderAPI.getDefaultPlaceholders
import nl.jochem.placeholderapi.configs.addPlaceholder
import nl.jochem.placeholderapi.configs.messagesConfig
import nl.jochem.placeholderapi.configs.placeholdersConfig
import nl.jochem.placeholderapi.configs.removePlaceholder
import nl.jochem.placeholderapi.api.PlaceholderGroup

class PapiCommand : Command("placeholder", "papi") {

    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg(messagesConfig.usage)
    }

    private fun nameCallback(sender: CommandSender, exception: ArgumentSyntaxException) {
        sender.msg(messagesConfig.invalid_name)
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
        if(!sender.hasPermission("placeholderapi.list")) { return sender.msg(messagesConfig.invalid_permission) }
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
        if(!sender.hasPermission("placeholderapi.download")) { return sender.msg(messagesConfig.invalid_permission) }
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
        if(!sender.hasPermission("placeholderapi.remove")) { return sender.msg(messagesConfig.invalid_permission) }
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
        activeGroupsName.setSuggestionCallback { _, _, suggestion ->
            placeholdersConfig.placeholders.forEach {
                 suggestion.addEntry(SuggestionEntry(it))
            }
        }
        val defaultGroupsName = ArgumentString("defaultGroupName")
        defaultGroupsName.setSuggestionCallback { _, _, suggestion ->
            getDefaultPlaceholders().forEach {
                if(!PlaceholderAPI.getPlaceholders().contains(it)) {
                    suggestion.addEntry(SuggestionEntry(it.getPrefix()))
                }
            }
        }

        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            nameCallback(
                sender,
                exception
            )
        }, activeGroupsName)
        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            nameCallback(
                sender,
                exception
            )
        }, defaultGroupsName)

        addSyntax({ sender: CommandSender,_ -> executeOnList(sender) }, list)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnDownload(sender, context, defaultGroupsName) }, download, defaultGroupsName)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnRemove(sender, context, activeGroupsName) }, remove, activeGroupsName)
    }

    private fun CommandSender.msg(inputMessage: String) {
        val newMessage = messagesConfig.prefix + inputMessage
        sendMessage(PlaceholderAPI.translatePlaceholdersToString(player = this as Player, inputString = newMessage))
    }
}