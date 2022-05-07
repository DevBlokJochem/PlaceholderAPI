package com.survival.core.blockdrops;

import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;
import net.minestom.server.item.Material;

import java.util.ArrayList;

public class ChildBlocks {

    static ArrayList<Material> ChildBlockList = new ArrayList<>();

    public void init() {

        ChildBlockList.add(Material.RED_CARPET);
        ChildBlockList.add(Material.BLUE_CARPET);
        ChildBlockList.add(Material.TORCH);
        ChildBlockList.add(Material.STONE_BUTTON);

    }

    public static boolean isChildBlock(Point parentPos, Point childPos, Instance instance) {

        if (ChildBlockList.contains(instance.getBlock(childPos).registry().material()) || instance.getBlock(childPos).toString().contains("Carpet")) {
            return true;
        } else {
            return false;
        }

    }

}
