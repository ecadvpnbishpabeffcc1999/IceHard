package com.stdio2016.icehard.worldgen;

import com.stdio2016.icehard.blocks.BlockIceHard;
import com.stdio2016.icehard.blocks.RegisterBlock;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityLlama;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeForest;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Random;

public class IceHardForestBiome extends BiomeForest {
    public static IceHardForestBiome INSTANCE, INSTANCE2;
    public static WorldGenTrees IceHardHardTree;
    public static WorldGenTrees IceHardIceTree;

    public static BiomeProperties getThisBiomeProperties(String name) {
        BiomeProperties props = new BiomeProperties(name);
        props.setBaseHeight(0.2f).setHeightVariation(0.2f).setTemperature(-0.55f).setRainfall(0.4f).setSnowEnabled();
        return props;
    }

    public IceHardForestBiome(String name) {
        super(Type.NORMAL, getThisBiomeProperties(name));
        topBlock = RegisterBlock.GRASS_BLOCK.getDefaultState();
        fillerBlock = RegisterBlock.SAND.getDefaultState();
        flowers.clear();
        flowers.add(new FlowerEntry(RegisterBlock.FLOWER.getDefaultState(), 20));
        spawnableCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(EntityLlama.class, 5, 4, 6));
    }

    @Override
    public WorldGenAbstractTree getRandomTreeFeature(Random rnd) {
        return rnd.nextInt(3) == 1 ? IceHardIceTree : IceHardHardTree;
    }

    @Override
    public WorldGenerator getRandomWorldGenForGrass(Random rnd) {
        return new GenIceHardGrass();
    }

    private static void createTree() {
        IBlockState trunk = RegisterBlock.IceHardHardLog.getDefaultState();
        IBlockState leave = RegisterBlock.IceHardHardLeaves.getDefaultState();
        IceHardHardTree = new WorldGenTrees(false, 4, trunk, leave, false);

        trunk = RegisterBlock.IceHardIceLog.getDefaultState();
        leave = RegisterBlock.IceHardIceLeaves.getDefaultState();
        IceHardIceTree = new WorldGenTrees(false, 4, trunk, leave, false);
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

        int id1 = Biome.REGISTRY.getIDForObject(biome);
        int id2 = Biome.REGISTRY.getIDForObject(biome2);
        System.out.println("Ice Hard Forest id = "+id1);
        System.out.println("Ice Hard Forest 2 id = "+id2);
    }
}
