package com.survival.core.config

import com.google.gson.GsonBuilder
import com.survival.core.commands.GameRule
import java.io.File

object RegisterGameRuleConfig {

    init {
        val name = "configs/gamerules.json"

        if(!File(name).exists()) {
            File(name).createNewFile()
            File(name).writeText(
                GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(GameRuleConfig(
                        pvp = true,
                        chat = true,
                        break_blocks = true,
                        place_blocks = true,
                        mob_damage = true,
                        item_pickup = true,
                        item_drop = true,
                        hunger = true,
                    ))
            )
        }
    }

}

private fun GameRuleConfig.updateData() {
    File("configs/gamerules.json").writeText(
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this))
}

fun GameRuleConfig.setGamerule(rule : GameRule, value : Boolean) {
    when (rule) {
        GameRule.pvp -> pvp = value
        GameRule.chat -> chat = value
        GameRule.break_blocks -> break_blocks = value
        GameRule.place_blocks -> place_blocks = value
        GameRule.mob_damage -> mob_damage = value
        GameRule.item_pickup -> item_pickup = value
        GameRule.item_drop -> item_drop = value
        GameRule.hunger -> hunger = value
    }

    updateData()
}

val gameruleConfig = GsonBuilder()
    .setPrettyPrinting()
    .create()!!.fromJson(File("configs/gamerules.json").readText(), GameRuleConfig::class.java)!!

data class GameRuleConfig(
    var pvp : Boolean,
    var chat : Boolean,
    var break_blocks : Boolean,
    var place_blocks : Boolean,
    var mob_damage : Boolean,
    var item_pickup : Boolean,
    var item_drop : Boolean,
    var hunger : Boolean,
)