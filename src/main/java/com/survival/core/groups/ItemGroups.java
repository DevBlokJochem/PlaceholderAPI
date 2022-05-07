package com.survival.core.groups;

import net.minestom.server.instance.block.Block;

import java.util.ArrayList;
import java.util.Arrays;


public interface ItemGroups {

    ArrayList<Block> FLOWERS = new ArrayList<Block>(Arrays.asList(
            Block.DANDELION,
            Block.BLUE_ORCHID
    ));

    ArrayList<Block> CARPETS = new ArrayList<Block>(Arrays.asList(
            Block.RED_CARPET,
            Block.ORANGE_CARPET
    ));

    ArrayList<Block> TORCHES = new ArrayList<Block>(Arrays.asList(
            Block.TORCH,
            Block.REDSTONE_TORCH,
            Block.SOUL_TORCH
    ));

    ArrayList<Block> CHILD_BLOCKS = new ArrayList<Block>() {{
        addAll(TORCHES);
        addAll(FLOWERS);
        addAll(CARPETS);
    }};



}
