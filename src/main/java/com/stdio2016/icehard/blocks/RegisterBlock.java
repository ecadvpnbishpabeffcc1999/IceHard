package com.stdio2016.icehard.blocks;

import com.google.common.collect.Lists;
import com.stdio2016.icehard.items.ItemEnergyPile;
import com.stdio2016.icehard.items.RegisterItem;
import com.stdio2016.icehard.worldgen.IceHardForestBiome;
import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stdio2016 on 2017/6/19.
 */

public class RegisterBlock {
    public static final EnumPlantType IceHardCropType;

    public static List<Block> blocks = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();
    public static MyBlock massPile;
    public static MyBlock cleaner;
    public static MyBlock stopper;
    public static MyBlock waterCleaner;
    public static BlockIceHardGrass GRASS_BLOCK;
    public static MyBlock SAND;
    public static BlockIceHardLog IceHardIceLog;
    public static BlockIceHardHardLog IceHardHardLog;
    public static BlockIceHardLeaves IceHardIceLeaves;
    public static BlockIceHardLeaves IceHardHardLeaves;
    public static BlockIceHardTallGrass IceHardTallGrass;
    public static BlockIceHardFarmland IceHardFarmland;
    public static BlockIceHardSapling ICE_SAPLING;
    public static BlockIceHardSapling SAPLING;
    public static BlockIceHardFlower FLOWER;
    public static BlockHaicerdCrop HAICERD_CROP;
    public static BlockHaicerdCrop HIRECAD_CROP;

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

        IceHardIceLog = new BlockIceHardLog("icehard_ice_log");
        helpAddBlock(IceHardIceLog);

        IceHardHardLog = new BlockIceHardHardLog("icehard_log");
        helpAddBlock(IceHardHardLog);

        IceHardIceLeaves = new BlockIceHardLeaves("icehard_ice_leaves");
        helpAddBlock(IceHardIceLeaves);

        IceHardHardLeaves = new BlockIceHardLeaves("icehard_leaves");
        helpAddBlock(IceHardHardLeaves);

        IceHardTallGrass = new BlockIceHardTallGrass("icehard_grass");
        helpAddBlock(IceHardTallGrass);

        IceHardFarmland = new BlockIceHardFarmland("icehard_farmland", Material.GROUND, MapColor.DIRT);
        IceHardFarmland.setSound(SoundType.SAND).setHardness(0.5f);
        IceHardFarmland.setHarvestLevel("shovel", 0);
        helpAddBlock(IceHardFarmland);

        ICE_SAPLING = new BlockIceHardSapling("icehard_ice_sapling");
        ICE_SAPLING.setHardness(0.0f);
        IceHardIceLeaves.saplingItem = ICE_SAPLING.item;
        helpAddBlock(ICE_SAPLING);

        SAPLING = new BlockIceHardSapling("icehard_sapling");
        SAPLING.setHardness(0.0f);
        IceHardHardLeaves.saplingItem = SAPLING.item;
        helpAddBlock(SAPLING);

        FLOWER= new BlockIceHardFlower("icehard_flower");
        FLOWER.setHardness(0.0f);
        helpAddBlock(FLOWER);

        HAICERD_CROP = new BlockHaicerdCrop("haicerd_crop");
        helpAddBlock(HAICERD_CROP);

        HIRECAD_CROP = new BlockHaicerdCrop("hirecad_crop");
        helpAddBlock(HIRECAD_CROP);
    }

    public static void helpAddBlock(Block block) {
        blocks.add(block);
        if (block instanceof IBlockIceHard)
            items.add(((IBlockIceHard)block).itemBlock());
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

    public static void init(FMLInitializationEvent ev) {
        // sorry but tree generators are initialized here
        RegisterBlock.ICE_SAPLING.treeGenerator = IceHardForestBiome.IceHardIceTree;
        RegisterBlock.SAPLING.treeGenerator = IceHardForestBiome.IceHardHardTree;

        IceHardIceLeaves.fruitItemStack = null /* TODO:  ice hard cream */;

        HAICERD_CROP.seedItem = RegisterItem.HaicerdSeed;
        HAICERD_CROP.cropItem = RegisterItem.edibleIceHard;
        HIRECAD_CROP.seedItem = RegisterItem.HirecadSeed;
        HIRECAD_CROP.cropItem = massPile.item;
    }

    static {
        IceHardCropType = EnumPlantType.getPlantType("IceHardCrop");
    }
}
