package com.survival.core.commands

import com.survival.core.config.addPermission
import com.survival.core.config.messagesConfig
import com.survival.core.config.permissionData
import com.survival.core.config.removePermission
import com.survival.core.utils.msg
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import net.minestom.server.utils.entity.EntityFinder
import world.cepi.kstom.command.arguments.delegate
import world.cepi.kstom.command.arguments.literal

class PermissionCommand : Command("permission") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /permission <player> add/remove/list [permission]")
    }

    private fun executeAddPlayer(
        sender: CommandSender,
        context: CommandContext,
        permission: ArgumentString,
    ) {
        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender)
        if(target == null) {
            sender.msg(messagesConfig.invalid_value)
            return
        }
        if(!sender.hasPermission("survivalcore_permission")) {
            return sender.msg(messagesConfig.no_permission, target = target)
        }

        permissionData.addPermission(target.uuid, context[permission])
    }

    private fun executeRemovePlayer(
        sender: CommandSender,
        context: CommandContext,
        permission: ArgumentString,
    ) {

        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender)
        if(target == null) {
            sender.msg(messagesConfig.invalid_value)
            return
        }
        if(!sender.hasPermission("survivalcore_permission")) {
            return sender.msg(messagesConfig.no_permission, target = target)
        }

        permissionData.removePermission(target.uuid, context[permission])

    }

    private fun executeListPlayer(
        sender: CommandSender,
        context: CommandContext,
    ) {

        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender)
        if(target == null) {
            sender.msg(messagesConfig.invalid_value)
            return
        }
        if(!sender.hasPermission("survivalcore_permission")) {
            return sender.msg(messagesConfig.no_permission, target = target)
        }

        if(!permissionData.players.isNullOrEmpty() || permissionData.players.contains(target.uuid)) {
            return sender.msg(messagesConfig.permission_dont_have, target = target)
        }

        var message = "§7"

        permissionData.players.get(target.uuid)?.forEach {
            message += "$it "
        }

        sender.msg(message)

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

        val add by literal
        val remove by literal
        val list by literal
        val inputString by ArgumentType::String.delegate{}

        addSyntax({ sender: CommandSender, context: CommandContext -> executeAddPlayer(sender, context, inputString) }, player, add, inputString)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeRemovePlayer(sender, context, inputString) }, player, remove, inputString)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeListPlayer(sender, context) }, player, list)
    }
}