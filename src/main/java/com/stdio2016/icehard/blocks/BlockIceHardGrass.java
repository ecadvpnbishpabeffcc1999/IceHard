package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.Settings;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
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

    }
}
