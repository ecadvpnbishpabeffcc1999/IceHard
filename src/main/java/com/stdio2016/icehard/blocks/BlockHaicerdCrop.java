package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.items.RegisterItem;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.Random;

/**
 * Created by User on 2019/1/29.
 */
public class BlockHaicerdCrop extends BlockCrops implements IBlockIceHard {
    public static final AxisAlignedBB Aabb[] = {
            new AxisAlignedBB(0,0,0,1,0.125,1),
            new AxisAlignedBB(0,0,0,1,0.250,1),
            new AxisAlignedBB(0,0,0,1,0.375,1),
            new AxisAlignedBB(0,0,0,1,0.500,1),
            new AxisAlignedBB(0,0,0,1,0.625,1),
            new AxisAlignedBB(0,0,0,1,0.750,1),
            new AxisAlignedBB(0,0,0,1,0.875,1),
            new AxisAlignedBB(0,0,0,1,1.000,1)
    };

    ItemBlock item;
    public BlockHaicerdCrop(String name) {
        super();
        this.setRegistryName(name).setUnlocalizedName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        item = new ItemBlock(this);
        item.setRegistryName(name).setUnlocalizedName(name);
    }

    @Override
    protected boolean canSustainBush(IBlockState blockState) {
        return blockState.getBlock() instanceof BlockFarmland;
    }

    @Override
    public boolean canBlockStay(World world, BlockPos pos, IBlockState blockState) {
        return super.canBlockStay(world, pos, blockState);
    }

    protected Item getSeed()
    {
        return RegisterBlock.ICE_SAPLING.item;
    }

    protected Item getCrop()
    {
        return RegisterItem.frost;
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return Aabb[((Integer)state.getValue(this.getAgeProperty())).intValue()];
    }

    @Override
    public Item itemBlock() {
        return item;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Crop;
    }
}
