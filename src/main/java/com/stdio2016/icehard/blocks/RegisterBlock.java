package com.stdio2016.icehard.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stdio2016 on 2017/6/19.
 */

public class RegisterBlock {
    public static List<Block> blocks = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static MyBlock massPile;
    public static MyBlock cleaner;
    public static MyBlock stopper;
    public static MyBlock waterCleaner;
    public static BlockIceHardGrass GRASS_BLOCK;
    public static MyBlock SAND;
    public static BlockIceHardLog IceHardLog;
    public static BlockIceHardLeaves IceHardLeaves;
    public static BlockIceHardTallGrass IceHardTallGrass;

    public static void preInit(FMLPreInitializationEvent event) {
        BlockIceHard.registerBlocks();
        blocks.addAll(Lists.newArrayList(BlockIceHard.iceHard));
        items.addAll(Lists.newArrayList(BlockIceHard.iceHardItem));
        helpAddBlock(BlockIceHard.packedIceHard);

        massPile = new MyBlock("masspile", Material.ROCK, MapColor.LIGHT_BLUE);
        massPile.setSound(SoundType.GLASS).setHardness(5f).setResistance(16.0f);
        massPile.setHarvestLevel("pickaxe", 1);
        helpAddBlock(massPile);

        cleaner = new BlockCleaner("cleaner", Material.GRASS, MapColor.LIGHT_BLUE);
        cleaner.setSound(SoundType.PLANT).setHardness(0f).setResistance(16.0f);
        cleaner.setHarvestLevel("shovel", 0);
        helpAddBlock(cleaner);

        stopper = new MyBlock("stopper", Material.GROUND, MapColor.RED);
        stopper.setSound(SoundType.GLASS).setHardness(0f).setResistance(16.0f);
        stopper.setHarvestLevel("shovel", 0);
        helpAddBlock(stopper);

        waterCleaner = new BlockWaterCleaner("water_cleaner", Material.GRASS, MapColor.LIGHT_BLUE);
        waterCleaner.setSound(SoundType.PLANT).setHardness(0f).setResistance(16.0f);
        waterCleaner.setHarvestLevel("shovel", 0);
        helpAddBlock(waterCleaner);

        GRASS_BLOCK = new BlockIceHardGrass("icehard_grass_block", Material.GRASS, MapColor.GRASS);
        GRASS_BLOCK.setSound(SoundType.PLANT).setHardness(0.5f).setResistance(6.0f);
        GRASS_BLOCK.setHarvestLevel("shovel", 0);
        GRASS_BLOCK.setTickRandomly(true);
        GRASS_BLOCK.freezesWater = true;
        helpAddBlock(GRASS_BLOCK);

        SAND = new BlockIceHardGrass("icehard_sand", Material.GROUND, MapColor.SAND);
        SAND.setSound(SoundType.SAND).setHardness(0.5f).setResistance(5.0f);
        SAND.setHarvestLevel("shovel", 0);
        SAND.setTickRandomly(true);
        SAND.freezesWater = true;
        helpAddBlock(SAND);

        IceHardLog = new BlockIceHardLog("icehard_log");
        blocks.add(IceHardLog);
        items.add(IceHardLog.item);

        IceHardLeaves = new BlockIceHardLeaves("icehard_leaves");
        blocks.add(IceHardLeaves);
        items.add(IceHardLeaves.item);

        IceHardTallGrass = new BlockIceHardTallGrass("icehard_grass");
        blocks.add(IceHardTallGrass);
        items.add(IceHardTallGrass.item);
    }

    public static void helpAddBlock(MyBlock block) {
        blocks.add(block);
        items.add(block.item);
    }

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> reg = event.getRegistry();
        for (Block b : blocks) {
            reg.register(b);
        }
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> reg = event.getRegistry();
        for (Item i : items) {
            reg.register(i);
        }
    }
}
