package com.survival.core.blockbreak;

import com.survival.core.groups.MaterialGroups;
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
            String facingVal = "";
            String facingValOpposite = "";
            if (face == BlockFace.NORTH) { facingVal = "north"; facingValOpposite = "south"; }
            if (face == BlockFace.SOUTH) { facingVal = "south"; facingValOpposite = "north"; }
            if (face == BlockFace.EAST) { facingVal = "east"; facingValOpposite = "west"; }
            if (face == BlockFace.WEST) { facingVal = "west"; facingValOpposite = "east"; }

            Block childBlock = event.getInstance().getBlock(event.getBlockPosition().relative(BlockFace.values()[i]));

            if (!childBlock.isAir()) {
                if (face == BlockFace.TOP) {
                    if (MaterialGroups.TOP_CHILD_BLOCKS.contains(childBlock.registry().material())) {
                        if (!childBlock.properties().containsKey("facing")) {
                            event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                        }
                    }

                } else if (face == BlockFace.NORTH || face == BlockFace.SOUTH || face == BlockFace.EAST || face == BlockFace.WEST) {
                    System.out.println(childBlock.registry().material().toString());

                    if (MaterialGroups.SIDE_CHILD_BLOCKS.contains(childBlock.registry().material())) {

                        System.out.println(childBlock.getProperty(facingVal));

                        if (childBlock.properties().containsKey("facing")) {
                            if (childBlock.getProperty("facing").contains(facingVal)) {
                                event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                            }
                        }

                        //                    if (childBlock == Block.VINE && childBlock.getProperty(facingVal).contains("true")) {
                        //                        event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                        //                    } else if (childBlock.getProperty("facing").contains(facingVal)) {
                        //                        event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                        //                    }

                    }

                } else if (BlockFace.values()[i] == BlockFace.BOTTOM) {
                    if (MaterialGroups.BOTTOM_CHILD_BLOCKS.contains(childBlock.registry().material())) {
                        event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));
                    }

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
