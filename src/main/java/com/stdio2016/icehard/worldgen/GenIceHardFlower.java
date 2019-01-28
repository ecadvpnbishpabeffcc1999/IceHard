package com.stdio2016.icehard.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by User on 2019/1/28.
 */
public class GenIceHardFlower extends GenIceHardGrass implements IWorldGenerator {
    public GenIceHardFlower(IBlockState block) {
        super(block, 64);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        final int CHUNK_SIZE = 16;
        int x = chunkX * CHUNK_SIZE + random.nextInt(CHUNK_SIZE);
        int y = 255;
        int z = chunkZ * CHUNK_SIZE + random.nextInt(CHUNK_SIZE);
        BlockPos pos = new BlockPos(x, y, z);
        Biome biome = world.getBiome(pos);
        // only generate on mod biome
        if (biome instanceof IceHardPlainsBiome || biome instanceof IceHardForestBiome) {
            super.generate(world, random, pos);
        }
    }
}
