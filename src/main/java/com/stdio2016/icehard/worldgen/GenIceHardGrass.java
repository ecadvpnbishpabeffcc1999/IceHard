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
    int howMany;
    public GenIceHardGrass() {
        this.grassBlock = RegisterBlock.IceHardTallGrass.getDefaultState();
        this.howMany = 128;
    }

    public GenIceHardGrass(IBlockState grass, int howMany) {
        this.grassBlock = grass;
        this.howMany = howMany;
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
        for (int t = 0; t < howMany; t++) {
            int x = rnd.nextInt(8) - rnd.nextInt(8);
            int y = rnd.nextInt(4) - rnd.nextInt(4);
            int z = rnd.nextInt(8) - rnd.nextInt(8);
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
