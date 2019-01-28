package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.Settings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

/**
 * Created by User on 2018/2/15.
 */
public class MyBlock extends Block implements IBlockIceHard {
    public ItemBlock item;
    public boolean freezesWater;

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
        freezesWater = false;
    }

    public MyBlock setSound(SoundType s) {
       super.setSoundType(s);
       return this;
    }

    public Item itemBlock() {
        return item;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        if (freezesWater && !world.isRemote && Settings.IceHardFreezesWater) {
            freezeNearbyWater(world, pos, state, rnd);
        }
    }

    public void freezeNearbyWater(World world, BlockPos pos, IBlockState state, Random rnd) {
        for (int x = -2; x <= 2; x++) {
            for (int y = -2; y <= 1; y++) {
                for (int z = -2; z <= 2; z++) {
                    if (rnd.nextInt(2) == 0) {
                        updateTicks_test(world, pos, new BlockPos(x, y, z), state);
                    }
                }
            }
        }
    }

    public void updateTicks_test(World world, BlockPos pos, BlockPos offset,
                                  IBlockState state) {
        pos = pos.add(offset);
        IBlockState blk = world.getBlockState(pos);
        if (blk.getBlock() == Blocks.LAVA) {
            if (blk.getValue(BlockLiquid.LEVEL) == 0) {
                world.setBlockState(pos, Blocks.OBSIDIAN.getDefaultState());
            }
            else if ((blk.getValue(BlockLiquid.LEVEL) & 7) < 4) {
                world.setBlockState(pos, Blocks.STONE.getDefaultState());
            }
        }
        else if (blk.getBlock() == Blocks.WATER) {
            if ((blk.getValue(BlockLiquid.LEVEL) & 7) == 0) {
                world.setBlockState(pos, Blocks.ICE.getDefaultState());
            }
        }
    }

    @Override
    public boolean canSustainPlant(IBlockState block, IBlockAccess blockAccess, BlockPos pos, EnumFacing facing, IPlantable plant) {
        EnumPlantType plantType = plant.getPlantType(blockAccess, pos);
        switch (plantType) {
            case Plains:
                return this == RegisterBlock.SAND || this == RegisterBlock.GRASS_BLOCK;
            case Crop:
                return this == RegisterBlock.IceHardFarmland;
        }
        return false;
    }
}
