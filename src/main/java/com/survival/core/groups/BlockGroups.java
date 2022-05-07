package com.survival.core.groups;

import net.minestom.server.instance.block.Block;

import java.util.ArrayList;
import java.util.Arrays;


public interface BlockGroups {

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

    ArrayList<Block> SHULKERS = new ArrayList<Block>() {{
        add(Block.SHULKER_BOX);
        add(Block.RED_SHULKER_BOX);
        add(Block.ORANGE_SHULKER_BOX);
        add(Block.YELLOW_SHULKER_BOX);
        add(Block.GREEN_SHULKER_BOX);
        add(Block.LIME_SHULKER_BOX);
        add(Block.LIGHT_BLUE_SHULKER_BOX);
        add(Block.CYAN_SHULKER_BOX);
        add(Block.BLUE_SHULKER_BOX);
        add(Block.PINK_SHULKER_BOX);
        add(Block.PURPLE_SHULKER_BOX);
        add(Block.MAGENTA_SHULKER_BOX);
        add(Block.WHITE_SHULKER_BOX);
        add(Block.BLACK_SHULKER_BOX);
        add(Block.LIGHT_GRAY_SHULKER_BOX);
        add(Block.GRAY_SHULKER_BOX);
        add(Block.BROWN_SHULKER_BOX);

    }};

    ArrayList<Block> INVENTORY_BLOCKS = new ArrayList<Block>() {{
            add(Block.CRAFTING_TABLE);
            add(Block.FURNACE);
            add(Block.HOPPER);
            add(Block.CHEST);
            add(Block.ANVIL);
            add(Block.BEACON);
            add(Block.BLAST_FURNACE);
            add(Block.BREWING_STAND);
            add(Block.ENCHANTING_TABLE);
            add(Block.GRINDSTONE);
            add(Block.LECTERN);
            add(Block.LOOM);
            add(Block.SHULKER_BOX);
            add(Block.SMITHING_TABLE);
            add(Block.SMOKER);
            add(Block.CARTOGRAPHY_TABLE);
            add(Block.STONECUTTER);
            add(Block.DISPENSER);
            add(Block.DROPPER);
            add(Block.BARREL);
            addAll(SHULKERS);

    }};

    ArrayList<Block> TOP_CHILD_BLOCKS = new ArrayList<Block>() {{
        addAll(TORCHES);
        addAll(FLOWERS);
        addAll(CARPETS);
    }};

    ArrayList<Block> BOTTOM_CHILD_BLOCKS = new ArrayList<Block>() {{

    }};

    ArrayList<Block> SIDE_CHILD_BLOCKS = new ArrayList<Block>() {{
        addAll(TORCHES);

    }};


}
