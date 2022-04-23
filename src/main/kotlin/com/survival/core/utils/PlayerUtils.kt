package com.survival.core.utils

import net.minestom.server.command.CommandSender
import net.minestom.server.entity.Player
import net.minestom.server.item.ItemStack

fun CommandSender.msg(message: String, target: Player? = null) {
    if(this is Player) {
        val player = this as Player
        player.sendMessage(message.getPlaceholders(player = player, target = target))
        return
    }
    println(message.getPlaceholders(target = target))
}

fun Player.clearInv() {
    for ( num in 0..inventory.size) {
        inventory.setItemStack(num, ItemStack.AIR)
    }
    inventory.update()
}