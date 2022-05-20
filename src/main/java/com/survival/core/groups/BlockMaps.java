package com.survival.core.groups;

import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface BlockMaps {

    Map<Block, Block> TORCHES = new HashMap<Block, Block>() {{

        put(Block.TORCH, Block.WALL_TORCH);
        put(Block.REDSTONE_TORCH, Block.REDSTONE_WALL_TORCH);
        put(Block.SOUL_TORCH, Block.SOUL_WALL_TORCH);

    }};


}
