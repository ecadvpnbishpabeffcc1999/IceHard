package com.stdio2016.icehard.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.Iterator;
import java.util.Random;

/**
 * Created by User on 2019/1/27.
 */
public class BlockIceHardFarmland extends MyBlock {
    public BlockIceHardFarmland(String name, Material mat, MapColor mapColor) {
        super(name, mat, mapColor);
        this.setTickRandomly(true);
        this.freezesWater = true;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        super.updateTick(world, pos, state, rnd);
        if (!hasIce(world, pos)) {
            if (!hasCrop(world, pos)) turnToSand(world, pos);
        }
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos pos2) {
        super.neighborChanged(state, world, pos, block, pos2);
        if (world.getBlockState(pos.up()).getMaterial().isOpaque()) {
            turnToSand(world, pos);
        }
    }

    public void onBlockAdded(World world, BlockPos pos, IBlockState block) {
        super.onBlockAdded(world, pos, block);
        if (world.getBlockState(pos.up()).getMaterial().isOpaque()) {
            turnToSand(world, pos);
        }
    }

    public void onFallenUpon(World world, BlockPos pos, Entity entity, float fallDistance) {
        super.onFallenUpon(world, pos, entity, fallDistance);
        if (!world.isRemote && entity.canTrample(world, this, pos, fallDistance)) {
            turnToSand(world, pos);
        }
    }

    public void turnToSand(World world, BlockPos pos) {
        world.setBlockState(pos, RegisterBlock.SAND.getDefaultState());
    }

    public Item getItemDropped(IBlockState block, Random rnd, int fortune) {
        return RegisterBlock.SAND.item;
    }

    public boolean hasIce(World world, BlockPos pos) {
        Iterator it = BlockPos.getAllInBoxMutable(pos.add(-4, 0, -4), pos.add(4, 1, 4)).iterator();
        BlockPos pos2;

        for (it = it; it.hasNext(); ) {
            pos2 = (BlockPos) it.next();
            IBlockState block = world.getBlockState(pos2);
            if (block.getBlock() == Blocks.ICE || block.getMaterial() == Material.WATER) {
                return true;
            }
        }
        return false;
    }

    public boolean hasCrop(World world, BlockPos pos) {
        Block block = world.getBlockState(pos.up()).getBlock();
        if (block instanceof IPlantable)
            return this.canSustainPlant(world.getBlockState(pos), world, pos, EnumFacing.UP, (IPlantable)block);
        return false;
    }
}
