package com.survival.core.config

import com.google.gson.GsonBuilder
import com.survival.core.utils.ItemStack
import net.minestom.server.entity.Player
import net.minestom.server.item.Material
import org.jglrxavpok.hephaistos.nbt.NBTCompound
import java.io.File
import java.util.*

object RegisterInventoryData {

//    fun init() {
//        val name = "data/inventory.json"
//
//        if (!File(name).exists()) {
//            File(name).createNewFile()
//            val testMap : HashMap<UUID, NBTCompound> = HashMap()
//            testMap[UUID.fromString("f8c08275-c8b1-4ab6-b630-177be7126ebf")] = ItemStack.builder(Material.DIAMOND).build().toItemNBT()
//            File("data/inventory.json").writeText(
//                GsonBuilder()
//                    .setPrettyPrinting()
//                    .create()
//                    .toJson(InventoryData(name = "#Player Inventory Data", players = testMap)))
//        }
//    }
}
//
//private fun InventoryData.updateData() {
//    File("data/inventory.json").writeText(
//        GsonBuilder()
//            .setPrettyPrinting()
//            .create()
//            .toJson(this))
//}
//
//
//fun Player.updateSavedInventory() {
//    val data = inventoryData
//
//    val map : HashMap<UUID, NBTCompound> = if(data.players.isEmpty()) {
//        HashMap()
//    }else {
//        data.players as HashMap<UUID, NBTCompound>
//    }
//
//    map[uuid] = inventory.getItemStack(1)
//    data.players = map
//
//    data.updateData()
//}
//
//fun Player.loadSavedInventory() {
//    if(inventoryData.players.isEmpty()) { return }
//    if(!inventoryData.players.containsKey(uuid)) { return }
//    val itemStackData = inventoryData.players[uuid]!![1]!!
//    inventory.setItemStack(1, ItemStack(material =  itemStackData.material, amount = itemStackData.amount, name = itemStackData.name))
//}
//
//val inventoryData = GsonBuilder()
//    .setPrettyPrinting()
//    .create()!!.fromJson(File("data/inventory.json").readText(), InventoryData::class.java)!!
//
//data class InventoryData(
//    var players: Map<UUID, HashMap<Int, ItemStackData>>,
//)
//
//data class ItemStackData(
//    val material: Material,
//    val amount: Int,
//    val name: String?,
//)