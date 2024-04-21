package com.drgnfireyellow.wildermob;

import java.util.ArrayList;
import org.bukkit.entity.EntityType;

import org.bukkit.Material;

public class Snares {
    public static EntityType getMob(ArrayList<Material> snareItems) {
        if (snareItems.contains(Material.WHEAT) && snareItems.contains(Material.MILK_BUCKET)) {
            return EntityType.COW;
        }
        else if (snareItems.contains(Material.COD) && snareItems.contains(Material.WATER_BUCKET)) {
            return EntityType.DOLPHIN;
        }
        else if (snareItems.contains(Material.BONE) && snareItems.contains(Material.SPRUCE_SAPLING)) {
            return EntityType.WOLF;
        }
        else if (snareItems.contains(Material.BLAZE_POWDER) && snareItems.contains(Material.NETHER_BRICK)) {
            return EntityType.BLAZE;
        }
        else {
            return null;
        }
    }
}
