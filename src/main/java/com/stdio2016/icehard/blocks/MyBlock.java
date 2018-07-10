package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * Created by User on 2018/2/15.
 */
public class MyBlock extends Block {
    public ItemBlock item;

    public MyBlock(String name, Material mat, MapColor mapColor) {
        this(name, mat, mapColor, true);
    }

    protected MyBlock(String name, Material mat, MapColor mapColor, boolean register) {
        super(mat, mapColor);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        if (register) {
            this.item = new ItemBlock(this);
            this.item.setRegistryName(name).setUnlocalizedName(name);
        }
    }

    public MyBlock setSound(SoundType s) {
       super.setSoundType(s);
       return this;
    }
}
