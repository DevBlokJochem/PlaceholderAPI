package com.survival.core.commands

import com.survival.core.config.gameruleConfig
import com.survival.core.config.messagesConfig
import com.survival.core.config.setGamerule
import com.survival.core.utils.msg
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.command.builder.arguments.ArgumentBoolean
import net.minestom.server.command.builder.arguments.ArgumentEnum
import net.minestom.server.command.builder.arguments.ArgumentType
import net.minestom.server.command.builder.exception.ArgumentSyntaxException
import net.minestom.server.entity.Player
import world.cepi.kstom.command.arguments.delegate

class GameRuleCommand : Command("gamerule") {
    private fun usage(sender: CommandSender, context: CommandContext) {
        sender.msg("§cUsage: /gamerule <gamerule> [true/false]")
    }

    private fun executeOnInfo(
        sender: CommandSender,
        context: CommandContext,
    ) {
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_gamerule")) {
            return sender.msg(messagesConfig.no_permission)
        }

        player.msg("§7" +
            "Pvp: ${gameruleConfig.pvp} " +
            "Chat: ${gameruleConfig.chat} " +
            "Break_blocks: ${gameruleConfig.break_blocks}" +
            "Place_blocks: ${gameruleConfig.place_blocks}" +
            "Mob_damage: ${gameruleConfig.mob_damage}" +
            "Item_pickup: ${gameruleConfig.item_pickup}" +
            "Item_drop: ${gameruleConfig.item_pickup}" +
            "Hunger: ${gameruleConfig.hunger}"
        )

    }

    private fun executeOnRule(
        sender: CommandSender,
        context: CommandContext,
        gamerule: ArgumentEnum<GameRule>,
        inputBoolean: ArgumentBoolean,
    ) {
        if (!sender.isPlayer) {
            sender.sendMessage(messagesConfig.only_player)
            return
        }
        if(!sender.hasPermission("survivalcore_gamerule")) {
            return sender.msg(messagesConfig.no_permission)
        }

        gameruleConfig.setGamerule(context[gamerule], context[inputBoolean])
        sender.msg(messagesConfig.gamerule)
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

        val gamerule = ArgumentType.Enum("gamerule", GameRule::class.java)
        val inputBoolean by ArgumentType::Boolean.delegate{}

        setArgumentCallback({ sender: CommandSender, exception: ArgumentSyntaxException ->
            targetCallback(
                sender,
                exception
            )
        }, player)

        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnInfo(sender, context) })
        addSyntax({ sender: CommandSender, context: CommandContext -> executeOnRule(sender, context, gamerule, inputBoolean) }, gamerule, inputBoolean)
    }
}