package com.survival.core.commands

import com.survival.core.config.messagesConfig
import com.survival.core.utils.msg
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentString
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.arguments.number.ArgumentInteger
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.GameMode
import net.minestom.server.entity.Player
import net.minestom.server.event.EventDispatcher
import net.minestom.server.utils.entity.EntityFinder
import world.cepi.kstom.command.arguments.delegate
import world.cepi.kstom.command.arguments.suggest

class GameModeCommand : Command("gamemode", "gm") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /gamemode <gamemode> [player]")
    }

    private fun executeOnSelf(
        sender: CommandSender,
        context: CommandContext,
        gameMode: ArgumentString,
    ) {
        if (!sender.isPlayer) {
            sender.sendMessage(messagesConfig.only_player)
            return
        }
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_gamemode_self")) {
            return sender.msg(messagesConfig.no_permission)
        }
        var gamemode = replaceGamemodes(context[gameMode])
        player.gameMode = gamemode

        sender.msg(messagesConfig.gamemode_self)
    }

    private fun executeOnOther(
        sender: CommandSender,
        context: CommandContext,
        gameMode: ArgumentString,
    ) {
        var gamemode = replaceGamemodes(context[gameMode])

        val targetFinder = context.get<EntityFinder>("player")
        val target = targetFinder.findFirstPlayer(sender)
        if(target == null) {
            sender.msg(messagesConfig.invalid_value)
            return
        }
        if(!sender.hasPermission("survivalcore_gamemode_other")) {
            return sender.msg(messagesConfig.no_permission, target = target)
        }
        target.gameMode = gamemode

        sender.msg(messagesConfig.gamemode_other, target = target)
        target.msg(messagesConfig.gamemode_self)
    }

    private fun targetCallback(sender: CommandSender, exception: ArgumentSyntaxException) {
        sender.msg(messagesConfig.invalid_value)
    }

    private fun gameModeCallback(sender: CommandSender, exception: ArgumentSyntaxException) {
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

        val gamemodesList : ArrayList<String> = ArrayList()

        gamemodesList.add("0")
        gamemodesList.add("1")
        gamemodesList.add("2")
        gamemodesList.add("3")
        gamemodesList.add("c")
        gamemodesList.add("s")
        gamemodesList.add("sp")
        gamemodesList.add("a")
        gamemodesList.add("creative")
        gamemodesList.add("survival")
        gamemodesList.add("spectator")
        gamemodesList.add("adventure")

        val gamemodes by ArgumentType::String.delegate {
            suggest {
                gamemodesList
            }
        }

        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            targetCallback(
                sender,
                exception
            )
        }, player)
        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            gameModeCallback(
                sender,
                exception
            )
        }, gamemodes)

        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnSelf(sender, context, gamemodes) }, gamemodes)
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnOther(sender, context, gamemodes) }, gamemodes, player)
    }

    private fun replaceGamemodes(input: String) : GameMode {
        return when(input) {
            "0" -> GameMode.SURVIVAL
            "1" -> GameMode.CREATIVE
            "2" -> GameMode.ADVENTURE
            "3" -> GameMode.SPECTATOR
            "c" -> GameMode.CREATIVE
            "s" -> GameMode.SURVIVAL
            "sp" -> GameMode.SPECTATOR
            "a" -> GameMode.ADVENTURE
            "creative" -> GameMode.CREATIVE
            "survival" -> GameMode.SURVIVAL
            "spectator" -> GameMode.SPECTATOR
            "adventure" -> GameMode.ADVENTURE
            else -> GameMode.SURVIVAL
        }
    }

}