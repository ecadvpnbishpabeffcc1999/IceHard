package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

/**
 * Created by User on 2018/1/22.
 */
public class BlockIceHard extends Block {
    public static final String iceHardNames[] = {
            "blue", "green", "yellow", "orange",
            "red", "purple", "light"
    };
    public final ItemBlock item;

    public BlockIceHard(String name) {
        super(Material.GLASS);
        this.setSoundType(SoundType.GLASS);
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setHardness(5f);
        this.setHarvestLevel("pickaxe",0);
        this.item = new ItemBlock(this);
        this.item.setRegistryName(name).setUnlocalizedName(name);
    }
}
