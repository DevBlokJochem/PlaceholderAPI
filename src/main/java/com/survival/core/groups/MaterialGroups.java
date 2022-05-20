package com.survival.core.groups;

import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

import java.util.ArrayList;
import java.util.Arrays;


public interface MaterialGroups {

    ArrayList<Material> FLOWERS = new ArrayList<Material>(Arrays.asList(
        Material.DANDELION,
        Material.BLUE_ORCHID
    ));

    ArrayList<Material> CARPETS = new ArrayList<Material>(Arrays.asList(
        Material.RED_CARPET,
        Material.ORANGE_CARPET
    ));

    ArrayList<Material> TORCHES = new ArrayList<Material>(Arrays.asList(
        Material.TORCH,
        Material.REDSTONE_TORCH,
        Material.SOUL_TORCH
    ));

//    ArrayList<Material> WALL_TORCHES = new ArrayList<Material>(Arrays.asList(
//        Block.WALL_TORCH,
//        Block.REDSTONE_WALL_TORCH,
//        Block.SOUL_WALL_TORCH
//    ));

    ArrayList<Material> SHULKERS = new ArrayList<Material>() {{
        add(Material.SHULKER_BOX);
        add(Material.RED_SHULKER_BOX);
        add(Material.ORANGE_SHULKER_BOX);
        add(Material.YELLOW_SHULKER_BOX);
        add(Material.GREEN_SHULKER_BOX);
        add(Material.LIME_SHULKER_BOX);
        add(Material.LIGHT_BLUE_SHULKER_BOX);
        add(Material.CYAN_SHULKER_BOX);
        add(Material.BLUE_SHULKER_BOX);
        add(Material.PINK_SHULKER_BOX);
        add(Material.PURPLE_SHULKER_BOX);
        add(Material.MAGENTA_SHULKER_BOX);
        add(Material.WHITE_SHULKER_BOX);
        add(Material.BLACK_SHULKER_BOX);
        add(Material.LIGHT_GRAY_SHULKER_BOX);
        add(Material.GRAY_SHULKER_BOX);
        add(Material.BROWN_SHULKER_BOX);

    }};

    ArrayList<Material> INVENTORY_BLOCKS = new ArrayList<Material>() {{
        add(Material.CRAFTING_TABLE);
        add(Material.FURNACE);
        add(Material.HOPPER);
        add(Material.CHEST);
        add(Material.ANVIL);
        add(Material.BEACON);
        add(Material.BLAST_FURNACE);
        add(Material.BREWING_STAND);
        add(Material.ENCHANTING_TABLE);
        add(Material.GRINDSTONE);
        add(Material.LECTERN);
        add(Material.LOOM);
        add(Material.SHULKER_BOX);
        add(Material.SMITHING_TABLE);
        add(Material.SMOKER);
        add(Material.CARTOGRAPHY_TABLE);
        add(Material.STONECUTTER);
        add(Material.DISPENSER);
        add(Material.DROPPER);
        add(Material.BARREL);
        add(Material.ENDER_CHEST);
        addAll(SHULKERS);

    }};

    ArrayList<Material> TOP_CHILD_BLOCKS = new ArrayList<Material>() {{
        addAll(TORCHES);
        addAll(FLOWERS);
        addAll(CARPETS);
    }};

    ArrayList<Material> BOTTOM_CHILD_BLOCKS = new ArrayList<Material>() {{

    }};

    ArrayList<Material> SIDE_CHILD_BLOCKS = new ArrayList<Material>() {{
//        add(Block.VINE);
        addAll(TORCHES);

    }};


}
