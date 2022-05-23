package com.survival.core.utils

import com.survival.core.config.inventoryData
import com.survival.core.groups.MaterialGroups
import com.survival.core.inventory.BlockInventory
import com.survival.core.inventory.Location
import net.minestom.server.instance.block.Block
import net.minestom.server.inventory.InventoryType
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material
import net.minestom.server.tag.Tag
import java.util.*
import kotlin.collections.HashMap
import kotlin.streams.toList

private val inventories : HashMap<UUID, BlockInventory> = HashMap()

fun Block.hasInventory() : Boolean {
    return MaterialGroups.INVENTORY_BLOCKS.contains(this.registry().material())
}

fun Block.getInventory(loc: Location) : BlockInventory {
    val oldBlock = this
    var uuid = UUID.randomUUID()
    if(!oldBlock.hasTag(Tag.String("uuid"))) {
        inventories[uuid] = BlockInventory(oldBlock.registry().material()!!.getInventoryType(), name(), this, loc)
        val inv = inventories[uuid]
        var newBlock : Block = oldBlock
        newBlock = newBlock.withTag(Tag.String("uuid"), uuid.toString())
        inv!!.setBlock(newBlock)
        println("set tag uuid")
    }else{
        uuid = UUID.fromString(oldBlock.getTag(Tag.String("uuid")).toString())
        println("loaded tag uuid ${oldBlock.getTag(Tag.String("uuid"))}")
    }
    val newBlock = inventories[uuid]!!.getBlock()
    if(newBlock.hasTag(Tag.ItemStack("inventoryData"))) {
        val list : List<ItemStack> = newBlock.getTag(Tag.ItemStack("inventoryData").list())
        for (i in list.indices) {
            inventories[uuid]!!.setItemStack(i, list[i])
        }
    }
    return inventories[uuid]!!
}

fun BlockInventory.cleanData() {
    if(!getBlock().hasTag(Tag.String("uuid"))) {
        println("error cleanData - no tag uuid")
        return
    }
    val uuid = UUID.fromString(getBlock().getTag(Tag.String("uuid")))
    if(!inventories.containsKey(UUID.fromString(getBlock().getTag(Tag.String("uuid"))))) {
        println("error cleanData - cant get tag uuid")
        return
    }
    if(viewers.size != 1) {
        println("vieuwers isnt 0")
        return
    }
    val itemBlock = inventories[uuid]!!.getBlock()
        .withTag(Tag.ItemStack("inventoryData").list(), Arrays.stream(itemStacks).toList())
    inventories[uuid]!!.setBlock(itemBlock)
    val uuidBlock = inventories[uuid]!!.getBlock()
        .withTag(Tag.String("uuid"), null)
    inventories[uuid]!!.setBlock(uuidBlock)
    inventories.remove(uuid)
}

fun Material.getInventoryType() : InventoryType {
    return when(this) {
        Material.CRAFTING_TABLE -> InventoryType.CRAFTING
        Material.FURNACE -> InventoryType.FURNACE
        Material.HOPPER -> InventoryType.HOPPER
        Material.ANVIL -> InventoryType.ANVIL
        Material.BEACON -> InventoryType.BEACON
        Material.BLAST_FURNACE -> InventoryType.BLAST_FURNACE
        Material.BREWING_STAND -> InventoryType.BREWING_STAND
        Material.ENCHANTING_TABLE -> InventoryType.ENCHANTMENT
        Material.GRINDSTONE -> InventoryType.GRINDSTONE
        Material.LECTERN -> InventoryType.LECTERN
        Material.LOOM -> InventoryType.LOOM
        Material.SMITHING_TABLE -> InventoryType.SMITHING
        Material.SMOKER -> InventoryType.SMOKER
        Material.CARTOGRAPHY_TABLE -> InventoryType.CARTOGRAPHY
        Material.STONECUTTER -> InventoryType.STONE_CUTTER
        Material.DISPENSER -> InventoryType.WINDOW_3X3
        Material.DROPPER -> InventoryType.WINDOW_3X3
        Material.BARREL -> InventoryType.WINDOW_3X3
        Material.ENDER_CHEST -> InventoryType.CHEST_6_ROW
        else -> InventoryType.CHEST_3_ROW
    }
}