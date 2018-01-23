package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.item.Item;

/**
 * Created by User on 2018/1/23.
 */
public class MyItem extends Item {
    public MyItem(String name) {
        this.setCreativeTab(IceHardMod.ourTab);
        this.setUnlocalizedName(name).setRegistryName(name);
    }
}
