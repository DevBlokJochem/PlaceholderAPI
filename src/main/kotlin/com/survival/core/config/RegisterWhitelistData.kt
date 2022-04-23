package com.survival.core.config

import com.google.gson.GsonBuilder
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

object RegisterWhitelistData {

    init {
        val name = "data/whitelist.json"

        if(!File(name).exists()) {
            File(name).createNewFile()
        }
    }

}

private fun WhitelistConfig.updateData() {
    File("data/whitelist.json").writeText(
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this))
}

fun WhitelistConfig.addUUID(uuid: UUID) {

    if(players.isEmpty()) { return }
    val list : ArrayList<UUID> = players as ArrayList<UUID>
    list.add(uuid)
    players = list
    updateData()

}

fun WhitelistConfig.removeUUID(uuid: UUID) {

    if(players.isEmpty()) { return }
    if(players.contains(uuid)) { return }
    val list : ArrayList<UUID> = players as ArrayList<UUID>
    list.remove(uuid)
    players = list
    updateData()
}

val whitelistData = GsonBuilder()
    .setPrettyPrinting()
    .create()!!.fromJson(File("data/whitelist.json").readText(), WhitelistConfig::class.java)!!

data class WhitelistConfig(
    var players: List<UUID>
)