package com.stdio2016.icehard.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
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

    public static void preInit(FMLPreInitializationEvent event) {
        BlockIceHard.registerBlocks();
        blocks.addAll(Lists.newArrayList(BlockIceHard.iceHard));
        items.addAll(Lists.newArrayList(BlockIceHard.iceHardItem));
        helpAddBlock(BlockIceHard.packedIceHard);

        massPile = new MyBlock("masspile", Material.ROCK, MapColor.LIGHT_BLUE);
        massPile.setSound(SoundType.GLASS).setHardness(5f).setResistance(16.0f);
        massPile.setHarvestLevel("pickaxe", 1);
        helpAddBlock(massPile);
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
