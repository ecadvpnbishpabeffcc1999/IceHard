package com.stdio2016.icehard.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Created by User on 2019/1/26.
 */
public class BlockIceHardGrass extends MyBlock {
    public BlockIceHardGrass(String name, Material mat, MapColor mapColor) {
        super(name, mat, mapColor);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        super.updateTick(world, pos, state, rnd); // freezes water
        if (!world.isRemote) spreadGrass(world, pos, state, rnd);
    }

    public void spreadGrass(World world, BlockPos pos, IBlockState state, Random rnd) {
        BlockPos up = pos.up();
        if (world.getLightFromNeighbors(up) < 4 && world.getBlockLightOpacity(up) > 2) {
            // grass die
            world.setBlockState(pos, RegisterBlock.SAND.getDefaultState());
        }
        else if (world.getLightFromNeighbors(up) >= 9) {
            for (int i = 0; i < 6; i++) {
                int x = pos.getX() + rnd.nextInt(3) - 1;
                int y = pos.getY() + rnd.nextInt(5) - 2;
                int z = pos.getZ() + rnd.nextInt(3) - 1;
                BlockPos dst = new BlockPos(x, y, z);
                if (grassCanGrowAt(world, dst)) {
                    world.setBlockState(dst, this.getDefaultState());
                }
            }
        }
    }

    public boolean grassCanGrowAt(World world, BlockPos pos) {
        // ice hard grass only grows on ice hard sand
        if (world.getBlockState(pos).getBlock() != RegisterBlock.SAND) return false;
        BlockPos up = pos.up();
        return world.getLightFromNeighbors(up) >= 4 && world.getBlockLightOpacity(up) <= 2;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rnd, int what) {
        return RegisterBlock.SAND.item;
    }
}
