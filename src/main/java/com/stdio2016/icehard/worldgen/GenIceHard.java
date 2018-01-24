package com.stdio2016.icehard.worldgen;

import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by User on 2018/1/24.
 */
public class GenIceHard implements IWorldGenerator {
    protected World world;
    protected Random random;
    protected int chunkX, chunkZ;
    @Override
    public void generate(Random rand, int chunkX, int chunkZ,
            World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        Block iceHard = BlockIceHard.iceHard[0];
        this.world = world;
        this.random = rand;
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.generateOre(iceHard.getDefaultState(), 9, 17, 0, 58);
    }

    protected void generateOre(IBlockState block, int maxVeinSize, int spawnTimes, int minY, int maxY ) {
        final int CHUNK_SIZE = 16;
        for (int i = 0; i < spawnTimes; i++) {
            int x = this.chunkX * CHUNK_SIZE + this.random.nextInt(CHUNK_SIZE);
            int y = minY + this.random.nextInt(maxY - minY);
            int z = this.chunkZ * CHUNK_SIZE + this.random.nextInt(CHUNK_SIZE);
            new WorldGenMinable(block, maxVeinSize).generate(this.world, this.random, new BlockPos(x, y, z));
        }
    }
}
