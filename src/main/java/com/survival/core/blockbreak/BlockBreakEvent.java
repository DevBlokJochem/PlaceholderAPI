package com.survival.core.blockbreak;

import com.survival.core.groups.BlockGroups;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.utils.time.TimeUnit;


public class BlockBreakEvent {

    public void onBreak(PlayerBlockBreakEvent event) {
        if (event.isCancelled()) { return; }

        System.out.println("block broken");

        // Drop item
        ItemEntity item = new ItemEntity(ItemStack.builder(getDrop(event.getBlock())).build());
        item.setPickupDelay(8, TimeUnit.SERVER_TICK);
        item.setInstance(event.getPlayer().getInstance(), event.getBlockPosition().add(0.5, 0.5, 0.5));

        // Break child torches, buttons, etc.
        breakChildBlocks(event);
    }

    private Material getDrop(Block block) {
        if (block == Block.DIAMOND_BLOCK) { return Material.DIAMOND_BLOCK; }
        if (block == Block.GRASS_BLOCK) { return Material.DIRT; }
        if (block == Block.GLASS) { return Material.GLASS; }
        return Material.AIR;
    }

    private void breakChildBlocks(PlayerBlockBreakEvent event) {

        for (int i = 0; i < 6; i++) {

            BlockFace face = BlockFace.values()[i];

            if (face == BlockFace.TOP) {
                if (BlockGroups.TOP_CHILD_BLOCKS.contains(event.getBlock())) {
                    event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                }

            } else if (face == BlockFace.NORTH || face == BlockFace.SOUTH || face == BlockFace.EAST || face == BlockFace.WEST) {
                if (BlockGroups.SIDE_CHILD_BLOCKS.contains(event.getBlock())) {
                    if (event.getBlock().possibleStates().contains(i)) {
                        event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                    }
                }

            } else if (BlockFace.values()[i] == BlockFace.BOTTOM) {
                if (BlockGroups.BOTTOM_CHILD_BLOCKS.contains(event.getBlock())) {
                    event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                }

            }

//            if (BlockGroups.CHILD_BLOCKS.contains(event.getInstance().getBlock(event.getBlockPosition().relative(BlockFace.values()[i])))) {
//
//                System.out.println("child broken");
//                event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
//
////                Block block = event.getInstance().getBlock(event.getBlockPosition().relative(BlockFace.values()[i]));
////                int x = event.getBlockPosition().relative(BlockFace.values()[i]).blockX();
////                int y = event.getBlockPosition().relative(BlockFace.values()[i]).blockY();
////                int z = event.getBlockPosition().relative(BlockFace.values()[i]).blockZ();
////
////                event.getPlayer().playEffect(Effects.BLOCK_BREAK, x, y, z, block.id(), false);
//
////                event.getInstance().sendGroupedPacket(createParticle(x, y, z, block));
//            }

        }
    }

//    public static ParticlePacket createParticle(double x, double y, double z, Block block) {
//
////        ParticlePacket blockBreak = ParticleCreator.createParticlePacket(Particle.BLOCK, false,
////                x, y+1.0, z, 0, 0, 0,
////                0.3f, 10, writer -> writer.writeVarInt(
////                        block.stateId()
////                ));
////        return blockBreak;
//
////        ByteBuffer bb = ByteBuffer.wrap(new byte[1]);
////        bb.putInt(block.stateId());
////
////        return new ParticlePacket(
////                Particle.BLOCK.id(),
////                true,
////                x,
////                y,
////                z,
////                0,
////                0,
////                0,
////                0.3f,
////                10,
////                bb.array()
////        );
//
//    }

}
