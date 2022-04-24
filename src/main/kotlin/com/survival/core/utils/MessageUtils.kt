package com.survival.core.utils

import net.minestom.server.MinecraftServer
import net.minestom.server.entity.Player

fun String.getPlaceholders(player: Player? = null, target: Player? = null): String {

    var message = this

    message = message.replace("%server_name%", MinecraftServer.getBrandName())
        .replace("%server_players_online%", MinecraftServer.getConnectionManager().onlinePlayers.size.toString())
        .replace("%server_version%", MinecraftServer.VERSION_NAME)
        .replace("%server_version_protocol%", MinecraftServer.PROTOCOL_VERSION.toString())

    if(player != null) {
        message = message
            .replace("%player_name%", player.username)
            .replace("%player_gamemode%", player.gameMode.toString())
            .replace("%player_level%", player.level.toString())
            .replace("%player_health%", player.health.toString())
            .replace("player_max_health", player.maxHealth.toString())
            .replace("player_location_x", player.position.blockX().toString())
            .replace("player_location_y", player.position.blockY().toString())
            .replace("player_location_z", player.position.blockZ().toString())
            .replace("player_latency", player.latency.toString())
    }

    if(target != null) {
        message = message
            .replace("%target_name%", target.username)
            .replace("%target_gamemode%", target.gameMode.toString())
            .replace("%target_level%", target.level.toString())
            .replace("%target_health%", target.health.toString())
            .replace("target_max_health", target.maxHealth.toString())
            .replace("target_location_x", target.position.blockX().toString())
            .replace("target_location_y", target.position.blockY().toString())
            .replace("target_location_z", target.position.blockZ().toString())
            .replace("target_latency", target.latency.toString())
    }

    return message
}