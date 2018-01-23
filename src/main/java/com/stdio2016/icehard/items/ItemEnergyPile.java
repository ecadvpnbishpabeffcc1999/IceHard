package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by User on 2018/1/23.
 */
public class ItemEnergyPile extends Item {
    public static Item item = new ItemEnergyPile();

    protected ItemEnergyPile() {
        final String name = "energypile";
        this.setUnlocalizedName(name).setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
    }

    @Override
    public int getItemBurnTime(ItemStack stack) {
        return 200; // smelt just 1 item
    }
}
