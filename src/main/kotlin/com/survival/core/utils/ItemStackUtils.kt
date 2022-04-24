package com.survival.core.utils

import net.kyori.adventure.text.Component
import net.minestom.server.item.ItemStack
import net.minestom.server.item.Material

fun ItemStack(amount: Int? = 1, material: Material, name: String? = null) : ItemStack {

    var inputDisplayName : String? = name

    if(name == null) {
        inputDisplayName = ItemStack.builder(material).build().displayName.toString()
    }

    return ItemStack.builder(material)
        .displayName(Component.text(inputDisplayName!!))
        .amount(amount!!)
        .build()
}