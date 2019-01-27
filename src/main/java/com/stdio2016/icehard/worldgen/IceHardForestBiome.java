package com.stdio2016.icehard.worldgen;

import com.stdio2016.icehard.blocks.BlockIceHard;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

public class IceHardForestBiome extends BiomeForest {
    public static IceHardForestBiome INSTANCE, INSTANCE2;
    public static WorldGenTrees IceHardTree;

    public static BiomeProperties getThisBiomeProperties(String name) {
        BiomeProperties props = new BiomeProperties(name);
        props.setBaseHeight(0.2f).setHeightVariation(0.2f).setTemperature(-0.55f).setRainfall(0.4f).setSnowEnabled();
        return props;
    }

    public IceHardForestBiome(String name) {
        super(Type.NORMAL, getThisBiomeProperties(name));
        topBlock = RegisterBlock.GRASS_BLOCK.getDefaultState();
        fillerBlock = RegisterBlock.SAND.getDefaultState();
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random p_getRandomTreeFeature_1_) {
        return IceHardTree;
    }

    private static void createTree() {
        IBlockState trunk = RegisterBlock.IceHardLog.getDefaultState();
        IBlockState leave = RegisterBlock.IceHardLeaves.getDefaultState();
        IceHardTree = new WorldGenTrees(false, 4, trunk, leave, false);
    }

    public static void register() {
        createTree();
        IceHardForestBiome biome = new IceHardForestBiome("Ice Hard Forest");
        INSTANCE = biome;
        biome.setRegistryName("icehard_forest");
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, BiomeDictionary.Type.COLD, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);

        IceHardForestBiome biome2 = new IceHardForestBiome("Ice Hard Forest 2");;
        INSTANCE2 = biome2;
        biome2.setRegistryName("icehard_forest2");
        ForgeRegistries.BIOMES.register(biome2);
        BiomeDictionary.addTypes(biome2, BiomeDictionary.Type.COLD, BiomeDictionary.Type.CONIFEROUS, BiomeDictionary.Type.FOREST, BiomeDictionary.Type.SNOWY);
        BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome2, 5));
        BiomeManager.addSpawnBiome(biome2);
    }
}
