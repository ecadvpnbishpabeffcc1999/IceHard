package com.stdio2016.icehard.blocks;

import com.stdio2016.icehard.IceHardMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stdio2016 on 2017/6/19.
 */

public class RegisterBlock {
    public static BlockIceHard iceHard[];

    public static List<Block> blocks = new ArrayList<>();
    public static List<Item> items = new ArrayList<>();

    static {
        GameRegistry.addSmelting(Items.STONE_SWORD, new ItemStack(Items.STONE_SWORD), 0.0f);
    }

    public static void preInit(FMLPreInitializationEvent event) {
        iceHard = new BlockIceHard[BlockIceHard.iceHardNames.length];
        // add ice hards
        for (int i = 0; i < iceHard.length; i++) {
            iceHard[i] = new BlockIceHard("icehard_" + BlockIceHard.iceHardNames[i]);
            blocks.add(iceHard[i]);
            items.add(iceHard[i].item);
        }
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
