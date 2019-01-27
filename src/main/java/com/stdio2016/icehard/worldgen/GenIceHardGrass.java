package com.stdio2016.icehard.worldgen;

import com.stdio2016.icehard.blocks.BlockIceHardTallGrass;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;

import java.util.Random;

/**
 * Created by User on 2019/1/27.
 */
public class GenIceHardGrass extends WorldGenerator {
    IBlockState grassBlock;
    public GenIceHardGrass(IBlockState grass) {
        this.grassBlock = grass;
    }

    @Override
    public boolean generate(World world, Random rnd, BlockPos pos) {
        IBlockState blk = world.getBlockState(pos);
        // get ground
        while (pos.getY() > 0 && (blk.getBlock().isAir(blk, world, pos) || blk.getBlock().isLeaves(blk, world, pos))) {
            pos = pos.down();
            blk = world.getBlockState(pos);
        }

        // random try
        for (int t = 0; t < 100; t++) {
            int x = rnd.nextInt(15) - 7;
            int y = rnd.nextInt(7) - 3;
            int z = rnd.nextInt(15) - 7;
            BlockPos pos2 = pos.add(x, y, z);
            if (world.isAirBlock(pos2)) {
                blk = world.getBlockState(pos2.down());
                if (blk.getBlock().canSustainPlant(blk, world, pos.down(), EnumFacing.UP, RegisterBlock.IceHardTallGrass)) {
                    world.setBlockState(pos2, grassBlock, 2);
                }
            }
        }
        return true;
    }
}
