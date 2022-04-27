package com.survival.core.commands

import com.survival.core.config.loadSavedInventory
import com.survival.core.config.messagesConfig
import com.survival.core.config.updateSavedInventory
import com.survival.core.data.StaffMode
import com.survival.core.utils.clearInv
import com.survival.core.utils.msg
import net.minestom.server.command.CommandSender
import net.minestom.server.command.builder.Command
import net.minestom.server.command.builder.CommandContext
import net.minestom.server.entity.Player

class StaffCommand : Command("staffmode", "sm") {

    private fun executeOnPlayer(
        sender: CommandSender,
    ) {
        if (!sender.isPlayer) {
            return sender.sendMessage(messagesConfig.only_player)
        }
        val player = sender as Player
        if(!sender.hasPermission("survivalcore_staffmode")) {
            return sender.msg(messagesConfig.no_permission)
        }

        if(!StaffMode.staffMode.contains(player)) {
            StaffMode.staffMode.add(player)
            player.msg(messagesConfig.staffmode_enter)
            player.updateSavedInventory()
            player.clearInv()
        }else{
            StaffMode.staffMode.remove(player)
            player.msg(messagesConfig.staffmode_leave)
            player.loadSavedInventory()
        }
    }

    init {
        addSyntax({ sender: CommandSender, _: CommandContext -> executeOnPlayer(sender) })
    }
}