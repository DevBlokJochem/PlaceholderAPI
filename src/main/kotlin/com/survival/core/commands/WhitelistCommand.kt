package com.survival.core.commands

import com.survival.core.config.addUUID
import com.survival.core.config.messagesConfig
import com.survival.core.config.removeUUID
import com.survival.core.config.whitelistData
import com.survival.core.utils.msg
import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.minecraft.ArgumentUUID
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.utils.entity.EntityFinder
import world.cepi.kstom.command.arguments.delegate
import world.cepi.kstom.command.arguments.literal

class WhitelistCommand : Command("fly") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /whitelist add/remove/list [player] [uuid]")
    }

    private fun executeAddPlayer(
        sender: CommandSender,
        context: CommandContext,
    ) {

        if(!sender.hasPermission("survivalcore_whitelist")) {
            return sender.msg(messagesConfig.no_permission)
        }

        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender) ?: return sender.msg(messagesConfig.invalid_value)

        if(whitelistData.players.contains(target.uuid)) {
            return sender.msg(messagesConfig.whitelist_already)
        }

        whitelistData.addUUID(target.uuid)
        sender.msg(messagesConfig.whitelist_add, target = target)

    }

    private fun executeAddUUID(
        sender: CommandSender,
        context: CommandContext,
        inputUUID: ArgumentUUID,
    ) {

        val uuid = context[inputUUID]

        if(!sender.hasPermission("survivalcore_whitelist")) {
            return sender.msg(messagesConfig.no_permission)
        }

        if(whitelistData.players.contains(uuid)) {
            return sender.msg(messagesConfig.whitelist_already)
        }

        whitelistData.addUUID(context[inputUUID])
        sender.msg(messagesConfig.whitelist_add)
    }

    private fun executeRemovePlayer(
        sender: CommandSender,
        context: CommandContext,
    ) {

        if(!sender.hasPermission("survivalcore_whitelist")) {
            return sender.msg(messagesConfig.no_permission)
        }

        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender)
        if(target == null) {
            sender.msg(messagesConfig.invalid_value)
            return
        }

        if(!whitelistData.players.contains(target.uuid)) {
            return sender.msg(messagesConfig.whitelist_dont_have)
        }

        whitelistData.removeUUID(target.uuid)
        sender.msg(messagesConfig.whitelist_remove, target = target)

    }

    private fun executeRemoveUUID(
        sender: CommandSender,
        context: CommandContext,
        inputUUID: ArgumentUUID,
    ) {

        val uuid = context[inputUUID]

        if(!sender.hasPermission("survivalcore_whitelist")) {
            return sender.msg(messagesConfig.no_permission)
        }

        if(!whitelistData.players.contains(uuid)) {
            return sender.msg(messagesConfig.whitelist_dont_have)
        }

        whitelistData.removeUUID(uuid)
        sender.msg(messagesConfig.whitelist_remove)
    }

    private fun executeList(
        sender: CommandSender,
        context: CommandContext,
    ) {

        if(!sender.hasPermission("survivalcore_whitelist")) {
            return sender.msg(messagesConfig.no_permission)
        }

        if(!whitelistData.players.isNullOrEmpty()) {
            return sender.msg(messagesConfig.whitelist_empty)
        }

        var message = "§7"
        whitelistData.players.forEach {
            if(MinecraftServer.getConnectionManager().getPlayer(it) != null) {
                message += MinecraftServer.getConnectionManager().getPlayer(it)
            }else {
                message += "$it "
            }
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

        val inputUUID by ArgumentType::UUID.delegate{}
        val add by literal
        val remove by literal
        val list by literal

        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            targetCallback(
                sender,
                exception
            )
        }, player)

        addSyntax({ sender: CommandSender, context: CommandContext -> executeAddPlayer(sender, context) }, add, player)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeAddUUID(sender, context, inputUUID) }, add, inputUUID)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeRemovePlayer(sender, context) }, remove, player)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeRemoveUUID(sender, context, inputUUID) }, remove, inputUUID)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeList(sender, context) }, list)
    }
}