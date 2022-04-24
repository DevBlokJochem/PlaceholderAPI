package com.survival.core.blockdrops;

import net.minestom.server.event.player.PlayerBlockBreakEvent;

public class BlockBreakEvent {
    public void onBreak(PlayerBlockBreakEvent event) {
        if(event.isCancelled()) { return; }
        event.getPlayer().sendMessage("test");
    }

}
