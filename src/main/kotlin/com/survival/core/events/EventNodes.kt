package com.survival.core.events

import com.survival.core.config.gameruleConfig
import net.minestom.server.MinecraftServer
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.trait.EntityEvent
import net.minestom.server.event.trait.PlayerEvent

object EventNodes {

    init {
        NodePower.onPowerChange(gameruleConfig)
    }

    val handler = MinecraftServer.getGlobalEventHandler()

    private val pvpNode = EventNode.type("pvp-listener", EventFilter.ENTITY)
    private val chatNode = EventNode.type("chat-listener", EventFilter.PLAYER)
    private val breakNode = EventNode.type("break-listener", EventFilter.PLAYER)
    private val placeNode = EventNode.type("place-listener", EventFilter.PLAYER)
    private val mobNode = EventNode.type("mob-listener", EventFilter.ENTITY)
    private val pickupNode = EventNode.type("pickup-listener", EventFilter.ENTITY)
    private val dropNode = EventNode.type("drop-listener", EventFilter.PLAYER)
    private val hungerNode = EventNode.type("hunger-listener", EventFilter.PLAYER)

    fun getPvpNode(): EventNode<EntityEvent> {
        return pvpNode
    }

    fun getChatNode() : EventNode<PlayerEvent> {
        return chatNode
    }

    fun getBreakNode() : EventNode<PlayerEvent> {
        return breakNode
    }

    fun getPlaceNode() : EventNode<PlayerEvent> {
        return placeNode
    }

    fun getMobNode() : EventNode<EntityEvent> {
        return mobNode
    }

    fun getPickupNode() : EventNode<EntityEvent> {
        return pickupNode
    }

    fun getDropNode() : EventNode<PlayerEvent> {
        return dropNode
    }

    fun getHungerNode() : EventNode<PlayerEvent> {
        return hungerNode
    }
}