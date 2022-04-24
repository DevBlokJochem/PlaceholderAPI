package com.survival.core.commands

import com.survival.core.config.messagesConfig
import com.survival.core.utils.msg
import net.minestom.server.MinecraftServer
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.number.ArgumentInteger
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.coordinate.Pos
import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.delegate
import world.cepi.kstom.command.arguments.suggest

class TpCommand : Command("tp") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /tp <player> [player]")
    }

    private fun executeOnSelfPlayer(
        sender: CommandSender,
        context: CommandContext,
        players: ArgumentString,
    ) {
        if (!sender.isPlayer) {
            return sender.sendMessage(messagesConfig.only_player)
        }
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_tp_self")) {
            return sender.msg(messagesConfig.no_permission)
        }

        val target = MinecraftServer.getConnectionManager().getPlayer(context[players]) ?: return sender.msg(messagesConfig.invalid_value)

        if(player.instance == target.instance) {
            player.teleport(target.position)
        }else{
            player.setInstance(target.instance!!, target.position)
        }

        player.msg(messagesConfig.tp_self)
    }

    private fun executeOnOtherPlayer(
        sender: CommandSender,
        context: CommandContext,
        player: ArgumentString,
        extraPlayer: ArgumentString,
    ) {

        if(!sender.hasPermission("survivalcore_tp_other")) {
            return sender.msg(messagesConfig.no_permission)
        }

        val player = MinecraftServer.getConnectionManager().getPlayer(context[player]) ?: return sender.msg(messagesConfig.invalid_value)
        val target = MinecraftServer.getConnectionManager().getPlayer(context[extraPlayer]) ?: return sender.msg(messagesConfig.invalid_value)

        if(player.instance == target.instance) {
            player.teleport(target.position)
        }else{
            player.setInstance(target.instance!!, target.position)
        }

        player.msg(messagesConfig.tp_other, target = player, extraPlayer = target)
    }

    private fun executeOnSelfCoords(
        sender: CommandSender,
        context: CommandContext,
        xCoords: ArgumentInteger,
        yCoords: ArgumentInteger,
        zCoords: ArgumentInteger,
    ) {
        if (!sender.isPlayer) {
            return sender.sendMessage(messagesConfig.only_player)
        }
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_tp_self")) {
            return sender.msg(messagesConfig.no_permission)
        }

        player.teleport(Pos(context[xCoords].toDouble(), context[yCoords].toDouble(), context[zCoords].toDouble()))

        player.msg(messagesConfig.tp_self_coords)
    }

    private fun executeOnOtherCoords(
        sender: CommandSender,
        context: CommandContext,
        player: ArgumentString,
        xCoords: ArgumentInteger,
        yCoords: ArgumentInteger,
        zCoords: ArgumentInteger,
    ) {

        if(!sender.hasPermission("survivalcore_tp_other")) {
            return sender.msg(messagesConfig.no_permission)
        }

        val target = MinecraftServer.getConnectionManager().getPlayer(context[player]) ?: return sender.msg(messagesConfig.invalid_value)

        target.teleport(Pos(context[xCoords].toDouble(), context[yCoords].toDouble(), context[zCoords].toDouble()))

        sender.msg(messagesConfig.tp_other_coords, target = target)
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

        val playerList : ArrayList<String> = ArrayList()
        MinecraftServer.getConnectionManager().onlinePlayers.forEach {
            playerList.add(it.username.toLowerCase())
        }
        val players by ArgumentType::String.delegate {
            suggest {
                playerList
            }
        }

        val inputInt by ArgumentType::Integer.delegate{}

        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            targetCallback(
                sender,
                exception
            )
        }, players)

        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnSelfPlayer(sender, context, players) }, players)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnOtherPlayer(sender, context, players, players) }, players, players)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnSelfCoords(sender, context, inputInt, inputInt, inputInt) }, inputInt, inputInt, inputInt)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnOtherCoords(sender, context, players, inputInt, inputInt, inputInt) }, players, inputInt, inputInt, inputInt)
    }
}