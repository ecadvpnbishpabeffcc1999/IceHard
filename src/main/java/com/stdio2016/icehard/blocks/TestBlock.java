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

/**
 * Created by stdio2016 on 2017/6/19.
 */

public class TestBlock {
    public static TestBlock_ block;
    public static Item item;
    static {
        block = new TestBlock_();
        block.setUnlocalizedName("icehard").
                setRegistryName("icehard").
                setCreativeTab(CreativeTabs.TOOLS).
                setHardness(5f).
                setHarvestLevel("pickaxe",0);
        item = new ItemBlock(block);
        item.setUnlocalizedName("icehard");
        item.setRegistryName("icehard");
        GameRegistry.addSmelting(Items.STONE_SWORD, new ItemStack(Items.STONE_SWORD), 0.0f);
    }

    public static void preInit(FMLPreInitializationEvent event) {

    }

    static public class TestBlock_ extends Block {
        public TestBlock_() {
            super(Material.GLASS);
            setSoundType(SoundType.GLASS);
        }
    }

    @Mod.EventBusSubscriber(modid = IceHardMod.MODID)
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            final IForgeRegistry<Block> reg = event.getRegistry();
            reg.register(block);
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> reg = event.getRegistry();
            reg.register(item);
        }
    }
}
