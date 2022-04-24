package com.survival.core.events

import com.survival.core.config.GameRuleConfig

object NodePower {

    fun onPowerChange(data: GameRuleConfig) {
        if(data.pvp) {
            EventNodes.handler.removeChild(EventNodes.getPvpNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getPvpNode())
        }
        if(data.chat) {
            EventNodes.handler.removeChild(EventNodes.getChatNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getChatNode())
        }
        if(data.break_blocks) {
            EventNodes.handler.removeChild(EventNodes.getBreakNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getBreakNode())
        }
        if(data.place_blocks) {
            EventNodes.handler.removeChild(EventNodes.getPlaceNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getPlaceNode())
        }
        if(data.mob_damage) {
            EventNodes.handler.removeChild(EventNodes.getMobNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getMobNode())
        }
        if(data.item_pickup) {
            EventNodes.handler.removeChild(EventNodes.getPickupNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getPickupNode())
        }
        if(data.item_drop) {
            EventNodes.handler.removeChild(EventNodes.getDropNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getDropNode())
        }
        if(data.hunger) {
            EventNodes.handler.removeChild(EventNodes.getHungerNode())
        } else {
            EventNodes.handler.addChild(EventNodes.getHungerNode())
        }
    }

}