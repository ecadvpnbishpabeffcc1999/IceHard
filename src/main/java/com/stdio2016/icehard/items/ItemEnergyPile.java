package com.stdio2016.icehard.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by User on 2018/1/23.
 */
public class ItemEnergyPile extends MyItem {
    public static Item item = new ItemEnergyPile();
    public static final String name = "energypile";

    protected ItemEnergyPile() {
        super(name);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return 200; // smelt just 1 item
    }
}
