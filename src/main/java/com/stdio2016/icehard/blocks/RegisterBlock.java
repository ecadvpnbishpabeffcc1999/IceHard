package com.stdio2016.icehard.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
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

    public static void preInit(FMLPreInitializationEvent event) {
        BlockIceHard.registerBlocks();
        blocks.addAll(Lists.newArrayList(BlockIceHard.iceHard));
        items.addAll(Lists.newArrayList(BlockIceHard.iceHardItem));
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
