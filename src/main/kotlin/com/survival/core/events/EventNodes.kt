package com.survival.core.events

import com.survival.core.config.gameruleConfig
import com.survival.core.handler
import net.minestom.server.event.EventFilter
import net.minestom.server.event.EventNode
import net.minestom.server.event.trait.EntityEvent
import net.minestom.server.event.trait.PlayerEvent

object EventNodes {

    fun init() {
        NodePower.onPowerChange(gameruleConfig)
        handler.addChild(getPvpNode())
        handler.addChild(getChatNode())
        handler.addChild(getBreakNode())
        handler.addChild(getPlaceNode())
        handler.addChild(getMobNode())
        handler.addChild(getPickupNode())
        handler.addChild(getDropNode())
        handler.addChild(getHungerNode())
        handler.addChild(getStaffNode())
    }

    private val pvpNode = EventNode.type("pvp-listener", EventFilter.ENTITY)
    private val chatNode = EventNode.type("chat-listener", EventFilter.PLAYER)
    private val breakNode = EventNode.type("break-listener", EventFilter.PLAYER)
    private val placeNode = EventNode.type("place-listener", EventFilter.PLAYER)
    private val mobNode = EventNode.type("mob-listener", EventFilter.ENTITY)
    private val pickupNode = EventNode.type("pickup-listener", EventFilter.ENTITY)
    private val dropNode = EventNode.type("drop-listener", EventFilter.PLAYER)
    private val hungerNode = EventNode.type("hunger-listener", EventFilter.PLAYER)
    private val staffNode = EventNode.type("staff-listener", EventFilter.PLAYER)
    fun getPvpNode(): EventNode<EntityEvent> = pvpNode
    fun getChatNode() : EventNode<PlayerEvent> = chatNode
    fun getBreakNode() : EventNode<PlayerEvent> = breakNode
    fun getPlaceNode() : EventNode<PlayerEvent> = placeNode
    fun getMobNode() : EventNode<EntityEvent> = mobNode
    fun getPickupNode() : EventNode<EntityEvent> = pickupNode
    fun getDropNode() : EventNode<PlayerEvent> = dropNode
    fun getHungerNode() : EventNode<PlayerEvent> = hungerNode
    fun getStaffNode() : EventNode<PlayerEvent> = staffNode
}