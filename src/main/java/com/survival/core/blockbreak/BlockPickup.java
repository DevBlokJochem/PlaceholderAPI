package com.survival.core.blockbreak;

import net.minestom.server.entity.Player;
import net.minestom.server.event.item.PickupItemEvent;

public class BlockPickup {

    public void onPickup(PickupItemEvent event) {
        if(event.isCancelled()) { return; }
        if(!(event.getEntity() instanceof Player)) { return; }
        Player player = (Player) event.getEntity();
        player.getInventory().addItemStack(event.getItemStack());
    }

}