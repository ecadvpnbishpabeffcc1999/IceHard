package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.Settings;
import com.stdio2016.icehard.items.ItemIceHardBlock;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

/**
 * Created by User on 2018/1/22.
 */
public class BlockIceHard extends MyBlock {
    public static final String iceHardNames[] = {
            "blue", "green", "yellow", "orange",
            "red", "purple", "light"
    };
    public static final MapColor mapColors[] = {
            MapColor.BLUE, MapColor.GREEN, MapColor.YELLOW, MapColor.ADOBE,
            MapColor.RED, MapColor.PURPLE, MapColor.LIGHT_BLUE
    };
    public static final int MaxLevel = iceHardNames.length;
    public static final BlockIceHard iceHard[] = new BlockIceHard[MaxLevel];
    public static final ItemBlock iceHardItem[] = new ItemBlock[MaxLevel];
    public static BlockIceHard packedIceHard;
    public static ItemBlock packedIceHardItem;
    public final int level;

    public static void registerBlocks() {
        for (int i = 0; i < iceHard.length; i++) {
            String name = "icehard_" + BlockIceHard.iceHardNames[i];
            iceHard[i] = new BlockIceHard(name, i);
            // set ice hard abilities here!
            iceHardItem[i] = iceHard[i].item;
            if (i > 0) {
                // smelt to make higher level Ice Hard
                GameRegistry.addSmelting(iceHardItem[i-1], new ItemStack(iceHardItem[i]), 0.1f);
            }
        }
        String name = "packed_icehard_blue";
        packedIceHard = new BlockIceHard(name, 0);
        packedIceHardItem = packedIceHard.item;
    }

    public BlockIceHard(String name, int level) {
        super(name, Material.ROCK, mapColors[level], false);
        this.setSoundType(SoundType.GLASS);
        this.setHardness(3f);
        this.setHarvestLevel("pickaxe",1);
        this.setResistance(10.0f);
        this.level = level;
        this.setTickRandomly(true);
        this.item = new ItemIceHardBlock(this);
        this.item.setRegistryName(name).setUnlocalizedName(name);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rnd) {
        if (Settings.IceHardFreezesWater) {
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
    }

    private void updateTicks_test(World world, BlockPos pos, BlockPos offset,
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
}
