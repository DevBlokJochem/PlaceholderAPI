package com.survival.core.config

import com.google.gson.GsonBuilder
import net.minestom.server.entity.Player
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

object RegisterPermissionData {

    init {
        val name = "data/permissions.json"

        if(!File(name).exists()) {
            File(name).createNewFile()
        }
    }

}

private fun PermissionConfig.updateData() {
    File("data/permissions.json").writeText(
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this))
}

fun PermissionConfig.addPermission(uuid: UUID, permission: String) {
    val map : HashMap<UUID, List<String>> = if(players.isEmpty()) {
        HashMap()
    }else {
        players as HashMap<UUID, List<String>>
    }

    val list : ArrayList<String> = if(!players.containsKey(uuid)) {
        ArrayList()
    } else ({
        players[uuid]
    }) as ArrayList<String>

    if(!list.contains(permission)) {
        list.add(permission)
    }

    map[uuid] = list

    updateData()
}

fun PermissionConfig.removePermission(uuid: UUID, permission: String) {
    if(players.isEmpty()) { return }
    val map : HashMap<UUID, List<String>> = players as HashMap<UUID, List<String>>

    if(!players.contains(uuid)) { return }
    val list : ArrayList<String> = players[uuid] as ArrayList<String>

    if(list.contains(permission)) {
        list.remove(permission)
    }

    if(list.size == 0) {
        map.remove(uuid)
    }

    updateData()
}

val permissionData = GsonBuilder()
    .setPrettyPrinting()
    .create()!!.fromJson(File("data/whitelist.json").readText(), PermissionConfig::class.java)!!

data class PermissionConfig(
    var players: Map<UUID, List<String>>
)