package com.survival.core.inventory

import net.minestom.server.coordinate.Point
import net.minestom.server.instance.Instance
import net.minestom.server.instance.block.Block
import net.minestom.server.inventory.Inventory
import net.minestom.server.inventory.InventoryType

class BlockInventory(inventoryType: InventoryType, title: String, inputBlock: Block, inputLocation : Location) : Inventory(inventoryType, title) {
    private var block = inputBlock
    private var location = inputLocation

    fun getBlock() : Block {
        return block
    }

    fun setBlock(block: Block) {
        location.instance.setBlock(location.point, block)
        this.block = block
        println("set new block")
    }

    fun getInstance() : Instance {
        return location.instance
    }

    fun getPoint() : Point {
        return location.point
    }
}

data class Location(
    val instance: Instance,
    val point: Point
)