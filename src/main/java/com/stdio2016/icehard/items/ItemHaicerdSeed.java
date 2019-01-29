package com.stdio2016.icehard.items;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

/**
 * Created by User on 2019/1/29.
 */
public class ItemHaicerdSeed extends ItemSeeds {
    public ItemHaicerdSeed(String name, Block crop) {
        super(crop, Blocks.FARMLAND);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setUnlocalizedName(name).setRegistryName(name);
    }

    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return RegisterBlock.IceHardCropType;
    }
}
