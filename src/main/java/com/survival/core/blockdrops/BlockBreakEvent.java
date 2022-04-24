package com.survival.core.blockdrops;

import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.utils.time.TimeUnit;

public class BlockBreakEvent {
    public void onBreak(PlayerBlockBreakEvent event) {
        if(event.isCancelled()) { return; }
        ItemEntity item = new ItemEntity(ItemStack.builder(getDrop(event.getBlock())).build());
        item.setPickupDelay(8, TimeUnit.SERVER_TICK);
        item.setInstance(event.getPlayer().getInstance(), event.getBlockPosition().add(0.5, 0.5, 0.5));
    }

    private Material getDrop(Block block) {
        if(block == Block.DIAMOND_BLOCK) { return Material.DIAMOND_BLOCK; }
        if(block == Block.GRASS_BLOCK) { return Material.DIRT; }
        if(block == Block.GLASS) { return Material.GLASS; }
        return Material.AIR;
    }

}
