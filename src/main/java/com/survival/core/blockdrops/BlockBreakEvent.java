package com.survival.core.blockdrops;

import net.minestom.server.coordinate.Point;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.effects.Effects;
import net.minestom.server.entity.ItemEntity;
import net.minestom.server.event.player.PlayerBlockBreakEvent;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.server.play.ParticlePacket;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import net.minestom.server.utils.binary.BinaryWriter;
import net.minestom.server.utils.time.TimeUnit;

import java.nio.ByteBuffer;
import java.util.function.Consumer;

public class BlockBreakEvent {

    public void onBreak(PlayerBlockBreakEvent event) {
        if (event.isCancelled()) { return; }

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

//            System.out.println("HELLO");
//            System.out.println(event.getBlockPosition());
//            System.out.println(event.getBlockPosition().relative(BlockFace.values()[i]));

            if (ChildBlocks.isChildBlock(event.getBlockPosition(), event.getBlockPosition().relative(BlockFace.values()[i]), event.getInstance())) {
                event.getInstance().breakBlock(event.getPlayer(), event.getBlockPosition().relative(BlockFace.values()[i]));


                Block block = event.getInstance().getBlock(event.getBlockPosition().relative(BlockFace.values()[i]));
                int x = event.getBlockPosition().relative(BlockFace.values()[i]).blockX();
                int y = event.getBlockPosition().relative(BlockFace.values()[i]).blockY();
                int z = event.getBlockPosition().relative(BlockFace.values()[i]).blockZ();

                event.getPlayer().playEffect(Effects.BLOCK_BREAK, x, y, z, block.id(), false);

//                event.getInstance().sendGroupedPacket(createParticle(x, y, z, block));
            }

        }
    }

    public static ParticlePacket createParticle(double x, double y, double z, Block block) {

//        ParticlePacket blockBreak = ParticleCreator.createParticlePacket(Particle.BLOCK, false,
//                x, y+1.0, z, 0, 0, 0,
//                0.3f, 10, writer -> writer.writeVarInt(
//                        block.stateId()
//                ));
//        return blockBreak;

        ByteBuffer bb = ByteBuffer.wrap(new byte[1]);
        bb.putInt(block.stateId());

        return new ParticlePacket(
                Particle.BLOCK.id(),
                true,
                x,
                y,
                z,
                0,
                0,
                0,
                0.3f,
                10,
                bb.array()
        );

    }

}
