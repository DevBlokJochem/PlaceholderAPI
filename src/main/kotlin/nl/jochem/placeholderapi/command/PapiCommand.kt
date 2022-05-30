package nl.jochem.placeholderapi.command

import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.ArgumentType.Literal
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import net.minestom.server.utils.entity.EntityFinder
import nl.jochem.placeholderapi.api.PlaceholderAPI
import nl.jochem.placeholderapi.configs.messagesConfig
import nl.jochem.placeholderapi.core.PlaceholderGroup
import nl.jochem.placeholderapi.defaultplaceholders.PlayerPlaceholderGroup

class PapiCommand : Command("placeholder", "papi") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg(messagesConfig.usage)
    }

    private fun executeOnList(
        sender: CommandSender
    ) {
        var message = "%player_name%"
        PlaceholderAPI.getPlaceholders().forEach {
            message += it.getPrefix() + " "
        }
        sender.msg(message)
    }

    private fun executeOnDownload(
        sender: CommandSender
    ) {

    }

    private fun executeOnRemove(
        sender: CommandSender
    ) {

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
//        val name;
        addSyntax({ sender: CommandSender, _ -> executeOnList(sender) }, list)
        addSyntax({ sender: CommandSender, _ -> executeOnDownload(sender) }, download)
        addSyntax({ sender: CommandSender, _ -> executeOnRemove(sender) }, remove)
    }

    private fun CommandSender.msg(inputMessage: String) {
        val newMessage = messagesConfig.prefix + inputMessage
        if(this is Player) {
            sendMessage(PlaceholderAPI.translatePlaceholders(player = this, inputString = newMessage))
        }else{
            sendMessage(PlaceholderAPI.translatePlaceholders(player = null, inputString = newMessage))
        }
    }

    private val defaultPlaceholders : List<String> = ArrayList(listOf(PlayerPlaceholderGroup.getPrefix()))
}