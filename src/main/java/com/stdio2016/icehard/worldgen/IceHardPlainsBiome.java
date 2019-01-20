package com.stdio2016.icehard.worldgen;

import com.stdio2016.icehard.blocks.BlockIceHard;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class IceHardPlainsBiome extends Biome {
    public static IceHardPlainsBiome INSTANCE;

    public static BiomeProperties getThisBiomeProperties() {
        BiomeProperties props = new BiomeProperties("Ice Hard Plains");
        props.setBaseHeight(0.125f).setHeightVariation(0.05f).setTemperature(-0.05f).setRainfall(0.5f).setSnowEnabled();
        return props;
    }

    public IceHardPlainsBiome() {
        super(getThisBiomeProperties());
        topBlock = BlockIceHard.iceHard[1].getDefaultState();
        fillerBlock = BlockIceHard.iceHard[0].getDefaultState();
    }

    public static void register() {
        IceHardPlainsBiome biome = new IceHardPlainsBiome();
        INSTANCE = biome;
        biome.setRegistryName("icehard_plains");
        ForgeRegistries.BIOMES.register(biome);
        BiomeDictionary.addTypes(biome, BiomeDictionary.Type.COLD, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.SNOWY);
        BiomeManager.addBiome(BiomeManager.BiomeType.ICY, new BiomeManager.BiomeEntry(biome, 10));
        BiomeManager.addSpawnBiome(biome);
    }
}
