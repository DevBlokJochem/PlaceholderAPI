package com.survival.core.commands

import com.survival.core.config.messagesConfig
import com.survival.core.utils.clearInv
import com.survival.core.utils.msg
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import net.minestom.server.utils.entity.EntityFinder

class ClearCommand : Command("clear") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /clear [player]")
    }

    private fun executeOnSelf(
        sender: CommandSender,
        context: CommandContext
    ) {
        if (!sender.isPlayer) {
            sender.sendMessage(messagesConfig.only_player)
            return
        }
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_clear_self")) {
            return sender.msg(messagesConfig.no_permission)
        }

        player.clearInv()
        sender.msg(messagesConfig.clear_self)
    }

    private fun executeOnOther(
        sender: CommandSender,
        context: CommandContext
    ) {
        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender) ?: return sender.msg(messagesConfig.invalid_value)
        if(!sender.hasPermission("survivalcore_clear_other")) {
            return sender.msg(messagesConfig.no_permission, target = target)
        }

        target.clearInv()

        sender.msg(messagesConfig.gamemode_other, target = target)
    }

    private fun targetCallback(sender: CommandSender, exception: ArgumentSyntaxException) {
        sender.msg(messagesConfig.invalid_value)
    }

    init {
        setDefaultExecutor { sender: CommandSender, context: CommandContext ->
            usage(
                sender,
                context
            )
        }
        val player = ArgumentType.Entity("player")
            .onlyPlayers(true)
            .singleEntity(true)

        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            targetCallback(
                sender,
                exception
            )
        }, player)

        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnSelf(sender, context) })
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnOther(sender, context) }, player)
    }
}