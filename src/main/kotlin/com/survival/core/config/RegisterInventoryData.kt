package com.survival.core.config

import com.google.gson.GsonBuilder
import net.minestom.server.entity.Player
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import org.jglrxavpok.hephaistos.nbt.NBT
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import org.jglrxavpok.hephaistos.nbt.NBTException
import org.jglrxavpok.hephaistos.nbt.NBTType
import org.jglrxavpok.hephaistos.parser.SNBTParser
import java.io.File
import java.io.StringReader
import java.util.*
import kotlin.collections.HashMap


object RegisterInventoryData {
    fun init() {
        val name = "data/inventory.json"

        if (!File(name).exists()) {
            File(name).createNewFile()
            File("data/inventory.json").writeText(
                GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .toJson(InventoryData(players = HashMap<UUID, String>(), enderchest = HashMap<UUID, String>())))
        }
    }
}

private fun InventoryData.updateData() {
    File("data/inventory.json").writeText(
        GsonBuilder()
            .setPrettyPrinting()
            .create()
            .toJson(this))
}


fun Player.updateSavedInventory() {
    val data = inventoryData

    val map : HashMap<UUID, String> = if(data.players.isEmpty()) {
        HashMap()
    }else {
        data.players as HashMap<UUID, String>
    }

    map[uuid] = NBT.List(NBTType.TAG_Compound, Arrays.stream(inventory.itemStacks)
        .map(ItemStack::toItemNBT)
        .toList()).toSNBT()

    data.players = map
    data.updateData()
}

fun Player.loadSavedInventory() {
    if(inventoryData.players.isEmpty()) { return }
    if(!inventoryData.players.containsKey(uuid)) { return }
    try {
        val list : List<NBTCompound> = SNBTParser(StringReader(inventoryData.players[uuid]!!)).parse().value as List<NBTCompound>
        for (i in list.indices) {
            inventory.setItemStack(i, ItemStack.fromItemNBT(list[i]))
        }
    } catch (e: NBTException) {
        e.printStackTrace()
    }
}

private fun getEmptyEnderchest() : Inventory {
    return Inventory(InventoryType.CHEST_6_ROW, "§5§lEnderChest")
}

fun Player.saveEnderchest(inv : Inventory) {
    val data = inventoryData

    val map : HashMap<UUID, String> = if(data.enderchest.isEmpty()) {
        HashMap()
    }else {
        data.enderchest as HashMap<UUID, String>
    }

    map[uuid] = NBT.List(NBTType.TAG_Compound, Arrays.stream(inv.itemStacks)
        .map(ItemStack::toItemNBT)
        .toList()).toSNBT()

    data.enderchest = map
    data.updateData()
}
fun Player.openEnderchest() {
    if(inventoryData.enderchest.isEmpty()) {
        openInventory(getEmptyEnderchest())
        return
    }
    if(!inventoryData.enderchest.containsKey(uuid)) {
        openInventory(getEmptyEnderchest())
        return
    }
    val inv = getEmptyEnderchest()
    try {
        val list : List<NBTCompound> = SNBTParser(StringReader(inventoryData.enderchest[uuid])).parse().value as List<NBTCompound>
        for (i in list.indices) {
            inv.setItemStack(i, ItemStack.fromItemNBT(list[i]))
        }
    } catch (e: NBTException) {
        e.printStackTrace()
    }
    openInventory(inv)
}

val inventoryData = GsonBuilder()
    .setPrettyPrinting()
    .create()!!.fromJson(File("data/inventory.json").readText(), InventoryData::class.java)!!

data class InventoryData(
    var players: Map<UUID, String>,
    var enderchest: Map<UUID, String>,
)