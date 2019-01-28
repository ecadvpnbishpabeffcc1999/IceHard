package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import com.stdio2016.icehard.worldgen.IceHardForestBiome;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

/**
 * Created by User on 2019/1/27.
 */
// reference:
// https://github.com/jabelar/ExampleMod-1.12/blob/master/src/main/java/com/blogspot/jabelarminecraft/examplemod/blocks/BlockSaplingCloud.java

public class BlockIceHardSapling extends BlockBush implements IGrowable, IBlockIceHard {
    public ItemBlock item;
    public WorldGenerator treeGenerator;
    public static PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);
    public static AxisAlignedBB Aabb = new AxisAlignedBB(0.1, 0, 0.1, 0.9, 0.8, 0.9);
    public BlockIceHardSapling(String name) {
        this.setUnlocalizedName(name);
        this.setRegistryName(name);
        this.setCreativeTab(IceHardMod.ourTab);
        this.setSoundType(SoundType.PLANT);
        this.item = new ItemBlock(this);
        this.item.setRegistryName(name).setUnlocalizedName(name);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
        return Aabb;
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        if (!world.isRemote) {
            super.updateTick(world, pos, state, rnd);
            if (world.getLightFromNeighbors(pos.up()) >= 9 && rnd.nextInt(5) == 4) {
                grow(world, rnd, pos, state);
            }
        }
    }

    public void generateTree(World world, BlockPos pos, IBlockState state, Random rnd) {
        if (!TerrainGen.saplingGrowTree(world, rnd, pos)) return;
        WorldGenerator gen = treeGenerator;
        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
        if (!gen.generate(world, rnd, pos)) {
            world.setBlockState(pos, this.getDefaultState().withProperty(STAGE, 1), 4);
        }
    }

    @Override
    public boolean canGrow(World world, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World world, Random rnd, BlockPos pos, IBlockState state) {
        return world.rand.nextFloat() > 0.55f;
    }

    @Override
    public void grow(World world, Random rnd, BlockPos pos, IBlockState state) {
        if (state.getValue(STAGE) == 0) {
            world.setBlockState(pos, state.cycleProperty(STAGE));
        }
        else {
            generateTree(world, pos, state, rnd);
        }
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, STAGE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(STAGE, (meta&8) >> 3);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(STAGE) << 3;
    }

    public Item itemBlock() {
        return item;
    }
}
