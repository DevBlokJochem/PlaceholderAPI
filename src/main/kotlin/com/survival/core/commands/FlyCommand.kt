package com.survival.core.commands

import com.survival.core.config.messagesConfig
import com.survival.core.utils.msg
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import net.minestom.server.utils.entity.EntityFinder

class FlyCommand : Command("fly") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /fly [player]")
    }

    private fun executeOnSelf(
        sender: CommandSender,
        context: CommandContext,
    ) {
        if (!sender.isPlayer) {
            sender.sendMessage(messagesConfig.only_player)
            return
        }
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_fly_self")) {
            return sender.msg(messagesConfig.no_permission)
        }

        if(player.isAllowFlying) {
            player.isAllowFlying = false
            player.msg(messagesConfig.fly_self_off)
        }else {
            player.isAllowFlying = true
            player.msg(messagesConfig.fly_self_on)
        }
    }

    private fun executeOnOther(
        sender: CommandSender,
        context: CommandContext,
    ) {

        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender)
        if(target == null) {
            sender.msg(messagesConfig.invalid_value)
            return
        }
        if(!sender.hasPermission("survivalcore_fly_other")) {
            return sender.msg(messagesConfig.no_permission, target = target)
        }

        sender.msg(messagesConfig.fly_other)
        if(target.isAllowFlying) {
            target.isAllowFlying = false
            target.msg(messagesConfig.fly_self_off)
        }else {
            target.isAllowFlying = true
            target.msg(messagesConfig.fly_self_on)
        }
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