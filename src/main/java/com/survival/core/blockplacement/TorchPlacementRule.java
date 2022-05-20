package com.survival.core.blockplacement;

import com.survival.core.groups.BlockMaps;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.instance.block.rule.BlockPlacementRule;
import net.minestom.server.coordinate.Point;
import org.jetbrains.annotations.NotNull;

public class TorchPlacementRule extends BlockPlacementRule {

    public TorchPlacementRule(@NotNull Block block) {
        super(block);
    }

    @Override
    public @NotNull Block blockUpdate(@NotNull Instance instance, @NotNull Point blockPosition, @NotNull Block block) {
        return block;
    }

    @Override
    public Block blockPlace(@NotNull Instance instance,
                            @NotNull Block block, @NotNull BlockFace blockFace, @NotNull Point blockPosition,
                            @NotNull Player pl) {

        if (blockFace != BlockFace.TOP && blockFace != BlockFace.BOTTOM) {
            block = BlockMaps.TORCHES.get(block);
            return block.withProperty("facing", String.valueOf(blockFace).toLowerCase());
        } else {
            return block;
        }
    }
}